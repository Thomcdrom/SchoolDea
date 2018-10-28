package nl.Icaprojecten.ci.Spotitube.dataAccess.DataMapper;

import nl.Icaprojecten.ci.Spotitube.DTO.IDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface Imapper {
    public IDTO create(ResultSet rs) throws SQLException;
}
