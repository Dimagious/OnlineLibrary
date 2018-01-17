package for_db;

import connections.ConnectionManager;
import connections.ConnectionManagerPostgeImpl;

import java.sql.Connection;
import java.sql.SQLException;

public class Connector {
    public static <R> R executeQuery(FunctionSQL<Connection, R> func) throws SQLException {
        R obj = null;
        Connection connection = null;
        try {
            ConnectionManager manager = ConnectionManagerPostgeImpl.getInstance();
            connection = manager.getConnection();
            obj = func.apply(connection);
            connection.close();
        } catch (SQLException ex) {
            throw ex; /*NOP*/
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return obj;
    }
}

