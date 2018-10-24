package nl.Icaprojecten.ci.Spotitube.dataAccess;

import nl.Icaprojecten.ci.Spotitube.User;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDbController {

    @Inject
    private JdbcConnectionFactory jdbcConnectionFactory;

    public void insertUserToken(User user){
        Connection connection = jdbcConnectionFactory.create();

        try{
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE User SET UUID=? WHERE Username = ? AND Password = ?)");
            preparedStatement.setString(1, user.getUUID().toString());
            preparedStatement.setString(2, user.getUser());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.executeUpdate();
            connection.close();
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    //TODO write user queries
    //TODO make connection with the database
    //TODO make DTO builder service
}
