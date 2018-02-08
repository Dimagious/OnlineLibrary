package db.connections;

import java.sql.Connection;

public interface ConnectionManager {
    Connection getConnection();
    void putback(Connection connection);
}