package nl.Icaprojecten.ci.Spotitube.dataAccess;

import nl.Icaprojecten.ci.Spotitube.DTO.Playlist;
import nl.Icaprojecten.ci.Spotitube.DTO.Track;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TrackDbController {

    @Inject
    private JdbcConnectionFactory jdbcConnectionFactory;

    public Playlist getTracksFromPlaylist(Playlist playlist){
        ArrayList<Track> tracks = new ArrayList<>();
        Connection connection = jdbcConnectionFactory.create();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT idTracks, Title, Preformer, Album, Playcount, PublicationDate, Offlineplay FROM Tracks INNER JOIN Playlist_has_Tracks ON Tracks.idTracks = Playlist_has_Tracks.Tracks_idTracks Where Playlist_idPlaylist  = ?");
            preparedStatement.setInt(1,playlist.getId());

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                tracks.add(trackBuilder(rs));
            }
            playlist.setTracks(tracks);
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return playlist;
    }

    public ArrayList<Track> getAvalibleTracks(int playlistID){
        ArrayList<Track> tracks = new ArrayList<>();
        Connection connection = jdbcConnectionFactory.create();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT idTracks, Title, Preformer, Album, Playcount, PublicationDate, Offlineplay FROM Tracks  inner JOIN Playlist_has_Tracks ON Tracks.idTracks = Playlist_has_Tracks.Tracks_idTracks Where Playlist_idPlaylist  != ?;");
            preparedStatement.setInt(1, playlistID);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                tracks.add(trackBuilder(rs));
            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return tracks;
    }

    private Track trackBuilder(ResultSet rs) throws SQLException {
        //TODO fix database and replace the 1 on the end
            return new Track(rs.getInt("idTracks"),rs.getString("Title"),rs.getString("Preformer"),rs.getString("Album"),rs.getInt("Playcount"),rs.getString("PublicationDate"),rs.getString("Offlineplay"), 1);
    }
}
