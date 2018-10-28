package nl.Icaprojecten.ci.Spotitube.dataAccess.DataMapper;

import nl.Icaprojecten.ci.Spotitube.DTO.Track;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TrackMapper implements ITrackMapper{

    public Track create(ResultSet rs) throws SQLException {
        Track track = new Track();

        track.setIdTrack(rs.getInt("idTracks"));
        track.setTitle(rs.getString("Title"));
        track.setPerformer(rs.getString("Performer"));
        track.setAlbum(rs.getString("Album"));
        track.setPlaycount(rs.getInt("Playcount"));
        track.setLenght(rs.getInt("Duration"));
        track.setPublicationDate(rs.getDate("PublicationDate"));
        track.setOfflineplay(rs.getBoolean("Offlineplay"));

        return track;
    }
}
