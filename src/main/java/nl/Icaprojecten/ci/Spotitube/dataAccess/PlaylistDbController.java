package nl.Icaprojecten.ci.Spotitube.dataAccess;

import nl.Icaprojecten.ci.Spotitube.DTO.Playlist;
import nl.Icaprojecten.ci.Spotitube.DTO.User;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlaylistDbController {

    @Inject
    private JdbcConnectionFactory jdbcConnectionFactory;

    public List<Playlist> getPlaylists(User user){
        Connection connection = jdbcConnectionFactory.create();

        try{
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM User WHERE Username = ? and Password = ?");
        }
        catch (SQLException e) {

        }

        return new ArrayList<Playlist>();
    }
}
