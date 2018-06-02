package ru.innopolis.stc9.earth_stc9.db.dao;

import org.springframework.stereotype.Component;
import ru.innopolis.stc9.earth_stc9.db.connection.ConnectionManager;
import ru.innopolis.stc9.earth_stc9.db.connection.ConnectionManagerJDBCImpl;
import ru.innopolis.stc9.earth_stc9.pojo.Group;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Aleksandr Tikhonov - created 29.05.2018
 */

@Component
public class GroupDao implements IGroupDao {
    private ConnectionManager conManager = ConnectionManagerJDBCImpl.getInstance();

    @Override
    public boolean addGroup(Group group) throws SQLException {
        if (group == null) {
            return false;
        }
        try (Connection connection = conManager.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("INSERT INTO groups(name, descr) VALUES (?, ?)")) {
                statement.setString(1, group.getGroupName());
                statement.setString(2, group.getGroupDesc());
                return statement.execute();
            }
        }
    }

    @Override
    public boolean updateGroup(Group group) throws SQLException {
        if (group == null) {
            return false;
        }
        try (Connection connection = conManager.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("UPDATE groups SET name = ?, descr = ? WHERE id = ?")) {
                statement.setString(1, group.getGroupName());
                statement.setString(2, group.getGroupDesc());
                statement.setInt(3, group.getGroupId());
                statement.executeUpdate();
            }
            return true;
        }
    }

    @Override
    public boolean deleteGroup(int id) throws SQLException {
        if (id == 0) {
            return false;
        }
        try (Connection connection = conManager.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("DELETE FROM groups WHERE id = ?")) {
                statement.setInt(1, id);
                return statement.execute();
            }
        }
    }

    @Override
    public Group getGroupById(int id) throws SQLException {
        try (Connection connection = conManager.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM groups WHERE id = ?")) {
                preparedStatement.setInt(1, id);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        return getGroupsFromDb(resultSet);
                    }
                }
            }
            return null;
        }
    }

    @Override
    public List<Group> getGroups() throws SQLException {
        try (Connection connection = conManager.getConnection()) {
            List<Group> groups = new ArrayList<>();
            try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM groups")) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        groups.add(getGroupsFromDb(resultSet));
                    }
                }
            }
            return groups;
        }
    }

    private Group getGroupsFromDb(ResultSet resultSet) throws SQLException {
        return new Group(
                resultSet.getInt(COLUMN_GROUP_ID),
                resultSet.getString(COLUMN_GROUP_NAME),
                resultSet.getString(COLUMN_GROUP_DESCRIPTION));
    }
}
