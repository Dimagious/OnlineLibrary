package db.connections;

import db.exceptions.DAOException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;

public class CustomConnectionManager implements ConnectionManager {
    private static final Logger logger = Logger.getLogger(CustomConnectionManager.class);
    private static ConnectionManager connectionManager;
    private static LinkedList<Connection> freeConnections = new LinkedList<>();
    private static LinkedList<Connection> busyConnections = new LinkedList<>();
    private static final String URL = "jdbc:postgresql://localhost:5433/onlinelibrary";
    private static final String USER = "admin";
    private static final String PASSWORD = "admin";

    /**
     * Создаёт и возвращает единственный экземпляр класса {@link ConnectionManager}
     * @throws DAOException
     */
    public static ConnectionManager getInstance() throws DAOException {
        ConnectionManager local = connectionManager;
        if (local == null) {
            synchronized (CustomConnectionManager.class) {
                local = connectionManager;
                if (local == null) {
                    local = connectionManager = new CustomConnectionManager();
                }
            }
        }
        return local;
    }

    /**
     * Конструктор {@link CustomConnectionManager}
     * Создает пять соединений с БД и кладет их в LinkedList freeConnections
     * @throws DAOException
     */
    private CustomConnectionManager() throws DAOException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            logger.error(e.getMessage());
        }
        for (int i = 0; i < 5 ; i++) {
            freeConnections.addLast(createConnection());
        }
    }

    /**
     * Создаёт соединение с БД
     * @return connection
     * @throws DAOException
     */
    private synchronized Connection createConnection() throws DAOException {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL,USER,PASSWORD);
        } catch (SQLException e) {
            logger.error("createConnection()" + e);
            throw new DAOException("createConnection()" + e);
        }
        return connection;
    }


    /**
     * Достаёт connection. Если freeConnections пуст, то connection создаётся,
     * иначе из freeConnections достётся первый connection и удалется из freeConnections.
     * Затем в busyConnections добавляется текущий connection
     * @return connection
     * @throws DAOException
     */
    public synchronized Connection getConnection() {
        Connection connection = null;
        if (freeConnections.size() == 0){
            try {
                connection = createConnection();
            } catch (DAOException e) {
                logger.error("getConnection()" + e);
            }
        }else{
            connection = freeConnections.getFirst();
            freeConnections.remove(connection);
        }
        busyConnections.addLast(connection);
        return connection;
    }

    /**
     * Удаляет существующий connection из busyConnections и
     * добавляет его в freeConnections
     * @param connection
     */
    public synchronized void putback(Connection connection) {
        if (connection != null){
            if (busyConnections.remove(connection)){
                freeConnections.addLast(connection);
            }else{
                throw new NullPointerException();
            }
        }
    }
}