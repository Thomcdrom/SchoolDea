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
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO items VALUES (NULL, ? , ? , ?)");
            preparedStatement.setString(1, entity.getSku());
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
