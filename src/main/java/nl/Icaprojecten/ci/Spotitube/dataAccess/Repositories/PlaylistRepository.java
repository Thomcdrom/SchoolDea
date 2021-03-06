package nl.Icaprojecten.ci.Spotitube.dataAccess.Repositories;

import nl.Icaprojecten.ci.Spotitube.DTO.Playlist;
import nl.Icaprojecten.ci.Spotitube.DTO.Track;
import nl.Icaprojecten.ci.Spotitube.dataAccess.DataMapper.IPlaylistMapper;
import nl.Icaprojecten.ci.Spotitube.dataAccess.Exeptions.PlaylistNotCreatedExpetion;
import nl.Icaprojecten.ci.Spotitube.dataAccess.Exeptions.PlaylistNotDeletedExpetion;
import nl.Icaprojecten.ci.Spotitube.dataAccess.Exeptions.PlaylistNotUpdatedExeption;
import nl.Icaprojecten.ci.Spotitube.dataAccess.Exeptions.TrackNotCoupledExeption;
import nl.Icaprojecten.ci.Spotitube.dataAccess.IJdbcConnection;

import javax.inject.Inject;
import java.sql.*;
import java.util.ArrayList;

public class PlaylistRepository implements IPlaylistRepository {

    @Inject
    private IJdbcConnection jdbcConnectionFactory;

    @Inject
    private IPlaylistMapper playlistMapper;

    public ArrayList<Playlist> getPlaylists(String token){
        ArrayList<Playlist> playlists = new ArrayList<>();
        Connection connection = jdbcConnectionFactory.create();

        try{
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT idPlaylist, Name, User_has_playlist.User_idUser FROM Playlist INNER JOIN User_has_playlist ON Playlist.idPlaylist = User_has_playlist.Playlist_idPlaylist Where User_has_playlist.User_idUser = (Select idUser FROM user where UUID = ?);");
            preparedStatement.setString(1, token);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                playlists.add(playlistMapper.create(rs));
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

            if(isAlone(playlistID) && isOwner(playlistID, token)) {
                preparedStatement = connection.prepareStatement("DELETE FROM Playlist_has_Tracks where Playlist_idPlaylist = ? ");
                preparedStatement.setInt(1, playlistID);
                preparedStatement.executeUpdate();

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

    public void createPlaylist(Playlist playlist, String token) throws PlaylistNotCreatedExpetion {
        Connection connection = jdbcConnectionFactory.create();
        try{
            if(isOwner(playlist, token)) {
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Playlist (idPlaylist,name) Value (?, ?); ");
                preparedStatement.setInt(1, playlist.getId());
                preparedStatement.setString(2, playlist.getName());

                int count = preparedStatement.executeUpdate();

                preparedStatement = connection.prepareStatement("INSERT INTO User_has_Playlist (User_idUser, Playlist_idPlaylist) VALUES ((Select idUser FROM user where UUID = ?),?);");
                preparedStatement.setString(1, token);
                preparedStatement.setInt(2, playlist.getId());

                count += preparedStatement.executeUpdate();

                if (count <= 1) {
                    throw new PlaylistNotCreatedExpetion();
                }

                count = 0;

            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updatePlaylist(Playlist playlist, String token) throws PlaylistNotUpdatedExeption, TrackNotCoupledExeption{
        Connection connection = jdbcConnectionFactory.create();
        try {
            if(isOwner(playlist, token)) {
                PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Playlist SET Name = ? WHERE idPlaylist = ?");
                preparedStatement.setString(1, playlist.getName());
                preparedStatement.setInt(2, playlist.getId());

                int count = preparedStatement.executeUpdate();

                if (count <= 1) {
                    throw new PlaylistNotUpdatedExeption();
                }

                if (playlist.getTracks().size() > 0) {
                    preparedStatement = connection.prepareStatement("DELETE FROM  Playlist_has_Tracks where Playlist_idPlaylist = ?;");
                    preparedStatement.setInt(1, playlist.getId());
                    preparedStatement.executeUpdate();

                    insertTracks(playlist);
                }
            }
            connection.close();
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }

    private boolean isAlone(int playlistID){
        Connection connection = jdbcConnectionFactory.create();

        try{
            int count = 0;

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT 1 from User_has_Playlist where Playlist_idPlaylist = ?");
            preparedStatement.setInt(1, playlistID);
            ResultSet res = preparedStatement.executeQuery();

            while (res.next()){
                count = res.getInt(1);
            }

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

    private boolean isOwner(Playlist playlist, String token){
        return this.isOwner(playlist.getId(), token);
    }

    private boolean isOwner(int playlistID, String token){
        Connection connection = jdbcConnectionFactory.create();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT Owner FROM User_has_Playlist WHERE Playlist_idPlaylist = ? AND User_idUser = (Select idUser FROM user where UUID = ?)");
            preparedStatement.setInt(1, playlistID);
            preparedStatement.setString(2,token);
            ResultSet res = preparedStatement.executeQuery();

            return res.getBoolean("Owner");
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    private void insertTracks(Playlist playlist) throws TrackNotCoupledExeption{
        Connection connection = jdbcConnectionFactory.create();
        try {
            int count = 0;
            for (Track track : playlist.getTracks()) {
                PreparedStatement preparedStatement = connection.prepareStatement("insert into Playlist_has_Tracks (Playlist_idPlaylist, Tracks_idTracks) Values (?,?)");
                preparedStatement.setInt(1, playlist.getId());
                preparedStatement.setInt(2, track.getIdTrack());

                count += preparedStatement.executeUpdate();
            }

            if (count <= 0) {
                throw new TrackNotCoupledExeption();
            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
