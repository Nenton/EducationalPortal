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
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, login);
            return getUserFromDb(statement);
        }
    }

    @Override
    public User getUserById(int id) throws SQLException {
        if (id == 0) {
            return null;
        }
        String sql = "SELECT * FROM users WHERE id=?";
        try (Connection connection = conManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            return getUserFromDb(statement);
        }
    }

    private User getUserFromDb(PreparedStatement statement) throws SQLException {
        try (ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                IRoleDao roleDao = new RoleDao();
                Role role = roleDao.getRoleById(resultSet.getInt(COLUMN_ROLE_ID));
                return getUserFromResultSet(resultSet, role);
            }
        }
        return null;
    }

    @Override
    public boolean addUser(User user) throws SQLException {
        if (user == null) {
            return false;
        }
        String sql = "insert into users(login, password, role_id, fullName, enabled) values (?, ?, ?, ?, ?)";
        try (Connection connection = conManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            setParamsIntoStatement(statement, user);
            return (statement.executeUpdate() > 0 ? true : false);
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
        String sql = "update users set login = ?, password = ?, role_id = ?, fullName = ?, enabled = ? where id = ?";
        try (Connection connection = conManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            setParamsIntoStatement(statement, user);
            statement.setInt(6, user.getId());
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
        return getUserFromResultSet(sql);
    }

    @Override
    public List<User> getUsers() throws SQLException {
        String sql = "select * from users;";
        return getUserFromResultSet(sql);
    }

    private List<User> getUserFromResultSet(String sql) throws SQLException {
        try (Connection connection = conManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            return getUsersFromDb(statement);
        }
    }

    private List<User> getUsersFromDb(PreparedStatement statement) throws SQLException {
        List<User> result = new ArrayList<>();
        try (ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                IRoleDao roleDao = new RoleDao();
                Role role = roleDao.getRoleById(resultSet.getInt(COLUMN_ROLE_ID));
                result.add(getUserFromResultSet(resultSet, role));
            }
        }
        return result;
    }

    /**
     * Get user from DB
     */
    private User getUserFromResultSet(ResultSet resultSet, Role role) throws SQLException {
        return new User(resultSet.getInt(COLUMN_ID), resultSet.getString(COLUMN_LOGIN),
                resultSet.getString(COLUMN_PASSWORD), role, resultSet.getString(COLUMN_FULL_NAME), resultSet.getInt(COLUMN_IS_ENABLED));
    }

    /**
     * Set params from user into statement
     */
    private void setParamsIntoStatement(PreparedStatement statement, User user) throws SQLException {
        statement.setString(1, user.getLogin());
        statement.setString(2, user.getPasswordHash());
        statement.setInt(3, user.getRole().getId());
        statement.setString(4, user.getFullName());
        statement.setInt(5, user.getEnabled());
    }

    @Override
    public int getCountUsersByRoleName(String roleName) throws SQLException {
        if (roleName == null || roleName.isEmpty()) {
            return 0;
        }
        String sql = "select count(*) from users inner join roles r on users.role_id = r.id where r.name = ?";
        try (Connection connection = conManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, roleName);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("count");
                } else {
                    return 0;
                }
            }
        }
    }
}