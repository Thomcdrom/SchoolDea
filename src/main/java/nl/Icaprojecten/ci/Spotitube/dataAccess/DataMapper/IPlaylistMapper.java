package nl.Icaprojecten.ci.Spotitube.dataAccess.DataMapper;

import nl.Icaprojecten.ci.Spotitube.DTO.Playlist;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface IPlaylistMapper  {
     Playlist create(ResultSet rs) throws SQLException;
}
