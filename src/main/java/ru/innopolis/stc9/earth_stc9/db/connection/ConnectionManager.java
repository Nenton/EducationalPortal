package ru.innopolis.stc9.earth_stc9.db.connection;

import java.sql.Connection;

/**
 * Connection for DB
 */
public interface ConnectionManager {
    Connection getConnection();
}
