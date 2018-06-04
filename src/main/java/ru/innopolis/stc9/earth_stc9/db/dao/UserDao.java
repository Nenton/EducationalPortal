package ru.innopolis.stc9.earth_stc9.db.dao;

import org.springframework.stereotype.Component;
import ru.innopolis.stc9.earth_stc9.db.connection.ConnectionManager;
import ru.innopolis.stc9.earth_stc9.db.connection.ConnectionManagerJDBCImpl;
import ru.innopolis.stc9.earth_stc9.pojo.Role;
import ru.innopolis.stc9.earth_stc9.pojo.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class UserDao implements IUserDao {
    private ConnectionManager conManager = ConnectionManagerJDBCImpl.getInstance();

    @Override
    public User getUserByLogin(String login) throws SQLException {
        if (login == null || login.isEmpty()) {
            return null;
        }
        String sql = "SELECT * FROM users WHERE login=?";
        try (Connection connection = conManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            statement.setString(1, login);
            if (resultSet.next()) {
                IRoleDao roleDao = new RoleDao();
                Role role = roleDao.getRoleById(resultSet.getInt(COLUMN_ROLE));
                return getUserFromDb(resultSet, role);
            }
            return null;
        }
    }

    @Override
    public User getUserById(int id) throws SQLException {
        if (id == 0) {
            return null;
        }
        String sql = "SELECT * FROM users WHERE id=?";
        try (Connection connection = conManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            statement.setInt(1, id);
            if (resultSet.next()) {
                IRoleDao roleDao = new RoleDao();
                Role role = roleDao.getRoleById(resultSet.getInt(COLUMN_ROLE));
                return getUserFromDb(resultSet, role);
            }
            return null;
        }
    }

    @Override
    public boolean addUser(User user) throws SQLException {
        if (user == null) {
            return false;
        }
        String sql = "insert into users(login, password, role_id, fullName) values (?, ?, ?, ?)";
        try (Connection connection = conManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            setParamsIntoStatement(statement, user);
            return statement.execute();
        }
    }


    @Override
    public boolean deleteUser(User user) throws SQLException {
        if (user == null) {
            return false;
        }
        String sql = "delete from users where id = ?";
        try (Connection connection = conManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, user.getId());
            return statement.execute();
        }
    }

    @Override
    public boolean updateUser(User user) throws SQLException {
        if (user == null) {
            return false;
        }
        String sql = "update users set login = ?, password = ?, role_id = ?, fullName = ? where id = ?";
        try (Connection connection = conManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            setParamsIntoStatement(statement, user);
            statement.setInt(5, user.getId());
            statement.executeUpdate();
            return true;
        }
    }


    @Override
    public boolean deleteUserById(int idUser) throws SQLException {
        if (idUser == 0) {
            return false;
        }
        String sql = "delete from users where id = ?";
        try (Connection connection = conManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idUser);
            return statement.execute();
        }
    }

    @Override
    public List<User> getUsers(int roleId) throws SQLException {
        if (roleId == 0) {
            return Collections.emptyList();
        }
        String sql = "select * from users where users.role_id = " + roleId + ";";
        return getUserFromDb(sql);
    }

    @Override
    public List<User> getUsers() throws SQLException {
        String sql = "select * from users;";
        return getUserFromDb(sql);
    }

    private List<User> getUserFromDb(String sql) throws SQLException {
        try (Connection connection = conManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            List<User> result = new ArrayList<>();
            while (resultSet.next()) {
                IRoleDao roleDao = new RoleDao();
                Role role = roleDao.getRoleById(resultSet.getInt(COLUMN_ROLE));
                result.add(getUserFromDb(resultSet, role));
            }
            return result;
        }
    }

    /**
     * Get user from DB
     */
    private User getUserFromDb(ResultSet resultSet, Role role) throws SQLException {
        return new User(resultSet.getInt(COLUMN_ID), resultSet.getString(COLUMN_LOGIN),
                resultSet.getString(COLUMN_PASSWORD), role, resultSet.getString(COLUMN_FULL_NAME));
    }

    /**
     * Set params from user into statement
     */
    private void setParamsIntoStatement(PreparedStatement statement, User user) throws SQLException {
        statement.setString(1, user.getLogin());
        statement.setString(2, user.getPasswordHash());
        statement.setInt(3, user.getRole().getId());
        statement.setString(4, user.getFullName());
    }
}