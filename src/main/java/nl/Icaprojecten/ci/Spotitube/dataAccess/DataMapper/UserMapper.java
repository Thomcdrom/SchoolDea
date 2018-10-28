package nl.Icaprojecten.ci.Spotitube.dataAccess.DataMapper;

import nl.Icaprojecten.ci.Spotitube.DTO.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class UserMapper implements Imapper {

    public User create(ResultSet rs) throws SQLException {
        User user = new User();

        user.setPassword(rs.getString("Password"));
        user.setUser(rs.getString("Username"));
        user.setName(rs.getString("Name"));
        user.setToken(UUID.fromString(rs.getString("UUID")));

        return user;
    }
}
