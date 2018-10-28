package nl.Icaprojecten.ci.Spotitube.dataAccess.DataMapper;

import nl.Icaprojecten.ci.Spotitube.DTO.Track;


import java.sql.ResultSet;
import java.sql.SQLException;

public interface ITrackMapper {
    public Track create(ResultSet rs) throws SQLException;
}
