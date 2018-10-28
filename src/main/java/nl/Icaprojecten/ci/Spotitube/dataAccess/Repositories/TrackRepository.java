package nl.Icaprojecten.ci.Spotitube.dataAccess.Repositories;

import nl.Icaprojecten.ci.Spotitube.DTO.Playlist;
import nl.Icaprojecten.ci.Spotitube.DTO.Track;
import nl.Icaprojecten.ci.Spotitube.dataAccess.DataMapper.ITrackMapper;
import nl.Icaprojecten.ci.Spotitube.dataAccess.Exeptions.TrackNotCoupledExeption;
import nl.Icaprojecten.ci.Spotitube.dataAccess.Exeptions.TrackNotFoundExeption;
import nl.Icaprojecten.ci.Spotitube.dataAccess.Exeptions.TrackNotRemovedExeption;
import nl.Icaprojecten.ci.Spotitube.dataAccess.JdbcConnectionFactory;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TrackRepository {

    @Inject
    private JdbcConnectionFactory jdbcConnectionFactory;

    @Inject
    private ITrackMapper trackMapper;

    public Playlist getTracksFromPlaylist(int playlistID){
        Playlist playlist = new Playlist();
        playlist.setId(playlistID);
        return this.getTracksFromPlaylist(playlist);
    }

    public Playlist getTracksFromPlaylist(Playlist playlist){
        ArrayList<Track> tracks = new ArrayList<>();
        Connection connection = jdbcConnectionFactory.create();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT idTracks, Title, Preformer, Album, Playcount, PublicationDate, Offlineplay FROM Tracks INNER JOIN Playlist_has_Tracks ON Tracks.idTracks = Playlist_has_Tracks.Tracks_idTracks Where Playlist_idPlaylist  = ?");
            preparedStatement.setInt(1,playlist.getId());

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                tracks.add(trackMapper.create(rs));
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
                tracks.add(trackMapper.create(rs));
            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return tracks;
    }

    public void deleteTrackFromPlaylist(int trackID, int playlistID) throws TrackNotRemovedExeption {
        Connection connection = jdbcConnectionFactory.create();

        try{
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Playlist_has_Tracks WHERE Tracks_idTracks = ? AND Playlist_idPlaylist = ? ");
            preparedStatement.setInt(1,trackID);
            preparedStatement.setInt(2,playlistID);

            int count = preparedStatement.executeUpdate();

            if(count <=0){
                throw new TrackNotRemovedExeption();
            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void insertTrackIntoPlaylist(Track track, int playlistID) throws TrackNotFoundExeption, TrackNotCoupledExeption{
        Connection connection = jdbcConnectionFactory.create();

        try{
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT 1 FROM Track WHERE idTrack = ?");
            preparedStatement.setInt(1,track.getIdTrack());

            int count = preparedStatement.executeUpdate();

            if(count <=0){
                throw new TrackNotFoundExeption();
            }

            preparedStatement = connection.prepareStatement("INSERT INTO Playlist_has_Tracks (Playlist_idPlaylist, Tracks_idTracks, Offlineplay) VALUES(?,?,?);");
            preparedStatement.setInt(1,track.getIdTrack());
            preparedStatement.setInt(2,playlistID);
            preparedStatement.setBoolean(3,track.getOfflineplay());

            count = preparedStatement.executeUpdate();

            if(count <=0){
                throw new TrackNotCoupledExeption();
            }

            connection.close();
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
