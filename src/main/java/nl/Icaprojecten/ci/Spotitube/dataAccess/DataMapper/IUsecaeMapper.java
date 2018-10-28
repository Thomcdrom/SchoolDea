package nl.Icaprojecten.ci.Spotitube.dataAccess.DataMapper;

import nl.Icaprojecten.ci.Spotitube.DTO.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface IUsecaeMapper {
    public User create(ResultSet rs) throws SQLException;
}
