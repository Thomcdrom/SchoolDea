package nl.Icaprojecten.ci.Spotitube.dataAccess.DataMapper;

import nl.Icaprojecten.ci.Spotitube.DTO.Playlist;
import nl.Icaprojecten.ci.Spotitube.DTO.Track;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PlaylistMapper implements IPlaylistMapper{

    public Playlist create(ResultSet rs) throws SQLException {
        Playlist playlist = new Playlist();

        playlist.setId(rs.getInt("idPlaylist"));
        playlist.setName(rs.getString("Name"));
        playlist.setOwner(rs.getBoolean("Owner"));
        playlist.setTracks(new ArrayList<Track>());

        return playlist;
    }
}
