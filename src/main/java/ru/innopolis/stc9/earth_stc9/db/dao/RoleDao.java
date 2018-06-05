package ru.innopolis.stc9.earth_stc9.db.dao;

import org.springframework.stereotype.Component;
import ru.innopolis.stc9.earth_stc9.db.connection.ConnectionManager;
import ru.innopolis.stc9.earth_stc9.db.connection.ConnectionManagerJDBCImpl;
import ru.innopolis.stc9.earth_stc9.pojo.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class RoleDao implements IRoleDao {

    private ConnectionManager conManager = ConnectionManagerJDBCImpl.getInstance();

    @Override
    public boolean addRole(Role role) throws SQLException {
        if (role == null) {
            return false;
        }
        String sql = "insert into roles(name) values (?)";
        try (Connection connection = conManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, role.getName());
            return statement.execute();
        }
    }

    @Override
    public boolean deleteRole(Role role) throws SQLException {
        return deleteRole(role.getId());
    }

    @Override
    public boolean deleteRole(int id) throws SQLException {
        String sql = "delete from roles where id = ?";
        try (Connection connection = conManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            return statement.execute();
        }
    }

    @Override
    public Role getRoleById(int id) throws SQLException {
        String sql = "select * from roles where id = ?";
        try (Connection connection = conManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            return getRoleFromResultSet(statement);
        }
    }

    @Override
    public boolean updateRole(Role role) throws SQLException {
        if (role == null) {
            return false;
        }
        String sql = "update roles set name = ? where id = ?";
        try (Connection connection = conManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, role.getName());
            statement.setInt(2, role.getId());
            statement.executeUpdate();
            return true;
        }
    }

    @Override
    public List<Role> getRoles() throws SQLException {
        String sql = "select * from roles";
        try (Connection connection = conManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            return getRolesFromResultSet(statement);
        }
    }

    private List<Role> getRolesFromResultSet(PreparedStatement statement) throws SQLException {
        List<Role> roles = new ArrayList<>();
        try (ResultSet set = statement.executeQuery()) {
            while (set.next()) {
                roles.add(getRoleFromBd(set));
            }
        }
        return roles;
    }


    @Override
    public Role getRoleByLogin(String login) throws SQLException {
        if (login == null || login.isEmpty()) {
            return null;
        }
        String sql = "select roles.* from roles " +
                "  inner join users u on u.role_id = roles.id where u.login = ?";
        try (Connection connection = conManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, login);
            return getRoleFromResultSet(statement);
        }
    }

    private Role getRoleFromResultSet(PreparedStatement statement) throws SQLException {
        try (ResultSet set = statement.executeQuery()) {
            if (set.next()) {
                return getRoleFromBd(set);
            }
        }
        return null;
    }

    /**
     * Get role from DB
     */
    private Role getRoleFromBd(ResultSet set) throws SQLException {
        return new Role(
                set.getInt(COLUMN_ID),
                set.getString(COLUMN_ROLE));
    }
}
