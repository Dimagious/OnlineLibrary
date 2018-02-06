package db.connections;

import db.exceptions.DAOException;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionManager {
    Connection getConnection();
    void putback(Connection connection);
}
