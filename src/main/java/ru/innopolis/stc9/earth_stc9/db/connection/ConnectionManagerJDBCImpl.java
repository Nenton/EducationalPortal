package ru.innopolis.stc9.earth_stc9.db.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Connection for PostgreSQL
 */
public class ConnectionManagerJDBCImpl implements ConnectionManager {

    private static ConnectionManager connectionManager;

    public static ConnectionManager getInstance() {
        if (connectionManager == null) {
            connectionManager = new ConnectionManagerJDBCImpl();
        }
        return connectionManager;
    }

    private ConnectionManagerJDBCImpl() {

    }

    @Override
    public Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.
                    getConnection("jdbc:postgresql://horton.elephantsql.com:5432/iexdwlpf",
                            "iexdwlpf",
                            "6hhVh_goakxJINwhnNRjKvVkj1wRjQ7f");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

//    @Override
//    public Connection getConnection() {
//        Connection connection = null;
//        try {
//            Class.forName("org.postgresql.Driver");
//            connection = DriverManager.
//                    getConnection("jdbc:postgresql://elmer.db.elephantsql.com:5432/ffxddqtq",
//                            "ffxddqtq",
//                            "BFzYLUJag5M0spBYLI7nu2Hf09SD9fLR");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        return connection;
//    }
}
