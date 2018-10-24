package nl.Icaprojecten.ci.Spotitube.dataAccess;

import nl.Icaprojecten.ci.Spotitube.User;
import nl.Icaprojecten.ci.Spotitube.dataAccess.Exeptions.UserNotUpdatedExeption;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UserDbController {

    @Inject
    private JdbcConnectionFactory jdbcConnectionFactory;

    public User checkLoginDetails(User user) throws UserNotUpdatedExeption{
        Connection connection = jdbcConnectionFactory.create();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM User WHERE Username = ? and Password = ?");
            preparedStatement.setString(1, user.getUser());
            preparedStatement.setString(2, user.getPassword());

            ResultSet rs = preparedStatement.executeQuery();
            user = userBuilder(rs);
            connection.close();

            insertUserToken(user);

        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return user;
    }

    private void insertUserToken(User user) throws UserNotUpdatedExeption{
        Connection connection = jdbcConnectionFactory.create();

        try{
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE User SET UUID=? WHERE Username = ? AND Password = ?");
            preparedStatement.setString(1, user.getUUID().toString());
            preparedStatement.setString(2, user.getUser());
            preparedStatement.setString(3, user.getPassword());
            int count = preparedStatement.executeUpdate();
            connection.close();

            if (count <= 0){
                throw new UserNotUpdatedExeption(user);
            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    private User userBuilder(ResultSet rs) throws SQLException {
            rs.first();
            return new User(rs.getString("Password"),rs.getString("Username"),rs.getString("Name"));
    }

    //TODO write user queries
    //TODO make connection with the database
    //TODO make DTO builder service
}
