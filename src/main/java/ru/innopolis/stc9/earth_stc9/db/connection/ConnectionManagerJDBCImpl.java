package ru.innopolis.stc9.earth_stc9.db.connection;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Connection for PostgreSQL
 */
public class ConnectionManagerJDBCImpl implements ConnectionManager {
    private static Logger logger = Logger.getLogger(ConnectionManagerJDBCImpl.class);

    private static ConnectionManager connectionManager;

    private ConnectionManagerJDBCImpl() {

    }

    public static ConnectionManager getInstance() {
        if (connectionManager == null) {
            connectionManager = new ConnectionManagerJDBCImpl();
        }
        return connectionManager;
    }

    @Override
    public Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.
                    getConnection("jdbc:postgresql://185.87.48.128:5432/postgres",
                            "postgres",
                            "sd456jk+L");
        } catch (SQLException | ClassNotFoundException e) {
            logger.warn("Ошибка при подключении к БД", e);
        }
        return connection;
    }
}
