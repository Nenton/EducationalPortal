package ru.innopolis.stc9.earth_stc9.db.dao;

import ru.innopolis.stc9.earth_stc9.pojo.User;

import java.sql.SQLException;
import java.util.List;

public interface IUserDao {
    String COLUMN_ID = "id";
    String COLUMN_FULL_NAME = "fullName";
    String COLUMN_LOGIN = "login";
    String COLUMN_PASSWORD = "password";
    String COLUMN_ROLE_ID = "role_id";
    String COLUMN_DATE_REG = "date_reg";
    String COLUMN_DATE_LAST = "date_last";
    String COLUMN_IS_ENABLED = "enabled";

    /**
     * Get user from DB by user login
     */
    User getUserByLogin(String login) throws SQLException;

    /**
     * Get user from DB by user id
     */
    User getUserById(int id) throws SQLException;

    /**
     * Insert user into DB
     */
    boolean addUser(User user) throws SQLException;

    /**
     * Delete user from DB
     */
    boolean deleteUser(User user) throws SQLException;

    /**
     * Update user from DB
     */
    boolean updateUser(User user) throws SQLException;

    /**
     * Delete user from DB by user id
     */
    boolean deleteUserById(int idUser) throws SQLException;

    /**
     * Get users from DB by role id
     */
    List<User> getUsers(int roleId) throws SQLException;

    /**
     * Get all users from DB
     */
    List<User> getUsers() throws SQLException;

    int getCountUsersByRoleName(String roleName) throws SQLException;
}
