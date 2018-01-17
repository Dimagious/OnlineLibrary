package connections;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionManagerPostgeImpl implements ConnectionManager {
    private static Properties properties;

    private static ConnectionManager connectionManager;
    private static String URL;
    private static String USER;
    private static String PASSWORD;

    public static ConnectionManager getInstance() {
        if (connectionManager == null) {
            connectionManager = new ConnectionManagerPostgeImpl();
        }
        return connectionManager;
    }

    private ConnectionManagerPostgeImpl() {
        try (FileInputStream fin = new FileInputStream
                (new File("src/main/resources/config_connection.property"))) {
            properties = new Properties();
            properties.load(fin);
            URL = properties.getProperty("Connection_JDBC_URL");
            USER = properties.getProperty("Connection_JDBC_User");
            PASSWORD = properties.getProperty("Connection_JDBC_Password");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}