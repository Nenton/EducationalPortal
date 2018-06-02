package ru.innopolis.stc9.earth_stc9.db.dao;

import org.springframework.stereotype.Component;
import ru.innopolis.stc9.earth_stc9.db.connection.ConnectionManager;
import ru.innopolis.stc9.earth_stc9.db.connection.ConnectionManagerJDBCImpl;
import ru.innopolis.stc9.earth_stc9.pojo.GroupStudents;
import ru.innopolis.stc9.earth_stc9.pojo.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Component
public class GroupsStudentsDAO implements IGroupsStudentsDAO {
    private ConnectionManager conManager = ConnectionManagerJDBCImpl.getInstance();

    @Override
    public ArrayList<User> getGroupsStudents(int groupId) throws SQLException {
        ArrayList<User> result = new ArrayList<>();
        if (groupId == 0) {
            return result;
        }
        Connection connection = conManager.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT users.id, users.fullName," +
                " student.group_id ,g.name, g.descr\n" +
                "FROM users INNER JOIN group_students student " +
                "on users.id = student.student_id\n" +
                "  INNER JOIN groups g on student.group_id = g.id WHERE group_id=?");
        statement.setInt(1, groupId);
        try (ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                User user = new User(resultSet.getInt("id"),
                        resultSet.getString("fullName"),
                        resultSet.getInt("group_id"),
                        resultSet.getString("name"),
                        resultSet.getString("descr"));
                result.add(user);
            }
        }
        connection.close();
        return result;
    }


    @Override
    public boolean addStudentGroup(GroupStudents groupStudents) throws SQLException {
        if (groupStudents == null) {
            return false;
        }
        try (Connection connection = conManager.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO group_students " +
                    "(id, student_id, group_id ) VALUES (?, ?, ?)");
            statement.setInt(1, groupStudents.getIdGS());
            statement.setInt(2, groupStudents.getStudentIdGS());
            statement.setInt(3, groupStudents.getGroupIdGS());
            return statement.execute();
        }

    }

    @Override
    public GroupStudents getUserById(int id) throws SQLException {
        if (id == 0) {
            return null;
        }
        try (Connection connection = conManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE id=?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            GroupStudents groupStudents = null;
            if (resultSet.next()) {
                groupStudents = new GroupStudents(resultSet.getInt("id"), resultSet.getInt("student_id"),
                        resultSet.getInt("group_id"));
            }
            return groupStudents;
        }
    }


    @Override
    public boolean deleteStudentGroup(int id) throws SQLException {
        if (id == 0) {
            return false;
        }
        try (Connection connection = conManager.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM group_students WHERE id=?");
            statement.setInt(1, id);
            return statement.execute();
        }

    }

    @Override
    public boolean updateStudentGroup(GroupStudents groupStudents) throws SQLException {
        if (groupStudents == null) {
            return false;
        }
        try (Connection connection = conManager.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("UPDATE group_students " +
                    "set id = ?, group_id = ?  WHERE student_id = ?");
            statement.setInt(1, groupStudents.getIdGS());
            statement.setInt(2, groupStudents.getGroupIdGS());
            statement.setInt(3, groupStudents.getStudentIdGS());
            statement.executeUpdate();
            return true;
        }
    }


}

