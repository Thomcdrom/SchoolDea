package nl.Icaprojecten.ci.Spotitube.dataAccess;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Logger;

public class JdbcConnectionFactory implements IJdbcConnection {
    private Properties properties;

    public JdbcConnectionFactory()
    {
        this(new Properties());
    }

    public JdbcConnectionFactory(Properties properties)
    {
        try {
            this.properties = properties;
            this.properties.load(getClass().getClassLoader().getResourceAsStream("database.properties"));
            Class.forName(properties.getProperty("driver"));
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    public Connection create()
    {
        try {
            return DriverManager.getConnection(properties.getProperty("databaseurl") + "?user=" + properties.getProperty("user") + "&password=" + properties.getProperty("password")+"&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}