package nl.Icaprojecten.ci.Spotitube.dataAccess;

import nl.Icaprojecten.ci.Spotitube.DTO.Playlist;
import nl.Icaprojecten.ci.Spotitube.DTO.Track;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TackDbController {

    @Inject
    private JdbcConnectionFactory jdbcConnectionFactory;

    public Playlist getTracksFromPlaylist(Playlist playlist){

        ArrayList<Track> tracks = new ArrayList<>();
        Connection connection = jdbcConnectionFactory.create();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM User WHERE Username = ? and Password = ?");
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return playlist;
    }

    private Track trackBuilder(ResultSet rs) throws SQLException {
        
    }
}
