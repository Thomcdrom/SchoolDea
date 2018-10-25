package nl.Icaprojecten.ci.Spotitube.dataAccess;

import nl.Icaprojecten.ci.Spotitube.DTO.Playlist;
import nl.Icaprojecten.ci.Spotitube.DTO.Track;
import nl.Icaprojecten.ci.Spotitube.dataAccess.Exeptions.PlaylistNotDeletedExpetion;

import javax.inject.Inject;
import java.sql.*;
import java.util.ArrayList;

public class PlaylistDbController {

    @Inject
    private JdbcConnectionFactory jdbcConnectionFactory;

    public ArrayList<Playlist> getPlaylists(String token){
        ArrayList<Playlist> playlists = new ArrayList<>();
        Connection connection = jdbcConnectionFactory.create();

        try{
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT idPlaylist, Name, User_has_playlist.User_idUser FROM Playlist INNER JOIN User_has_playlist ON Playlist.idPlaylist = User_has_playlist.Playlist_idPlaylist Where User_has_playlist.User_idUser = (Select idUser FROM user where UUID = ?);");
            preparedStatement.setString(1, token);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                playlists.add(playlistBuilder(rs));
            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return playlists;
    }

    public void deletePlaylist( int playlistID, String token) throws PlaylistNotDeletedExpetion{
        Connection connection = jdbcConnectionFactory.create();

        try{
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM User_has_Playlist where Playlist_idPlaylist = ? and User_idUser = (Select idUser FROM user where UUID = ?) ");
            preparedStatement.setInt(1, playlistID);
            preparedStatement.setString(2, token);

            int count = preparedStatement.executeUpdate();

            if(count <= 0){
                throw new PlaylistNotDeletedExpetion();
            }
            connection.close();

            if(isAlone(playlistID)) {
                preparedStatement = connection.prepareStatement("DELETE FROM Playlist where idPlaylist = ? ");
                preparedStatement.setInt(1, playlistID);
                preparedStatement.executeUpdate();

                connection.close();
            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private boolean isAlone(int playlistID){
        Connection connection = jdbcConnectionFactory.create();

        try{
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT 1 from User_has_Playlist where Playlist_idPlaylist = ?");
            preparedStatement.setInt(1, playlistID);
            int count = preparedStatement.executeUpdate();

            if(count <= 0){
                return true;
            }
            connection.close();
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    private Playlist playlistBuilder(ResultSet rs) throws SQLException {
        return new Playlist(rs.getInt("idPlaylist"), rs.getString("Name"), rs.getInt("User_has_playlist.User_idUser"), new ArrayList<Track>());
    }
}
