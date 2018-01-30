package db.connections;

//@Component
//public class ConnectionManagerPostgeImpl implements ConnectionManager {
//    private static ConnectionManager connectionManager;
//    private static String URL;
//    private static String USER;
//    private static String PASSWORD;
//
//    public static ConnectionManager getInstance() {
//        if (connectionManager == null) {
//            connectionManager = new ConnectionManagerPostgeImpl();
//        }
//        return connectionManager;
//    }
//
//    private ConnectionManagerPostgeImpl() {
//            URL = "jdbc:postgresql://localhost:5433/onlinelibrary";
//            USER = "postgres";
//            PASSWORD = "postgres";
//    }
//
//    @Override
//    public Connection getConnection() {
//        Connection connection = null;
//        try {
//            Class.forName("org.postgresql.Driver");
//            connection = DriverManager.getConnection(URL, USER, PASSWORD);
//        } catch (ClassNotFoundException | SQLException e) {
//            e.printStackTrace();
//        }
//        return connection;
//    }
//}

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionManagerImpl implements ConnectionManager {

    private ConnectionManagerImpl() {
    }

    private static ConnectionManager instance = null;

    public static ConnectionManager getInstance() {
        if (instance == null) {
            instance = new ConnectionManagerImpl();
        }
        return instance;
    }

    @Override
    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        Connection connection = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5433/onlinelibrary",
                "postgres",
                "postgres"
        );
        return connection;
    }
}