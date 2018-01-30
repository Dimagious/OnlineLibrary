package utils;

import db.connections.ConnectionManager;
import db.connections.ConnectionManagerImpl;

import java.sql.Connection;
import java.sql.SQLException;

public class Connector {
    public static <R> R executeQuery(FunctionSQL<Connection, R> func) throws SQLException {
        R obj = null;
        Connection connection = null;
        try {
            ConnectionManager manager = ConnectionManagerImpl.getInstance();
            connection = manager.getConnection();
            obj = func.apply(connection);
            connection.close();
        } catch (SQLException ex) {
            throw ex; /*NOP*/
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return obj;
    }
}

