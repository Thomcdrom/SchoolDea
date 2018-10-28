package nl.Icaprojecten.ci.Spotitube.dataAccess.Repositories;

import nl.Icaprojecten.ci.Spotitube.DTO.User;
import nl.Icaprojecten.ci.Spotitube.dataAccess.DataMapper.IUsecaeMapper;
import nl.Icaprojecten.ci.Spotitube.dataAccess.Exeptions.TokenNotFoundExeption;
import nl.Icaprojecten.ci.Spotitube.dataAccess.Exeptions.UserNotUpdatedExeption;
import nl.Icaprojecten.ci.Spotitube.dataAccess.IJdbcConnection;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UserRepository implements IUserRepository {

    @Inject
    private IJdbcConnection jdbcConnectionFactory;

    @Inject
    private IUsecaeMapper userMapper;

    public boolean validateUser(String token) throws TokenNotFoundExeption{
        Connection connection = jdbcConnectionFactory.create();

        try {
            int count = 0;
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT 1 FROM User WHERE UUID = ?;" );
            preparedStatement.setString(1, token);

            ResultSet res = preparedStatement.executeQuery();

            while (res.next()){
                count = res.getInt(1);
            }
            connection.close();
            if (count <= 0){
                throw new TokenNotFoundExeption(token);
            }
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return true;
    }

    public User checkLoginDetails(User user) throws UserNotUpdatedExeption{
        Connection connection = jdbcConnectionFactory.create();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM User WHERE Username = ? and Password = ?");
            preparedStatement.setString(1, user.getUser());
            preparedStatement.setString(2, user.getPassword());

            ResultSet rs = preparedStatement.executeQuery();
            rs.first();
            user = userMapper.create(rs);
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
            preparedStatement.setString(1, user.getToken().toString());
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
}
