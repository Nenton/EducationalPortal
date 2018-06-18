package ru.innopolis.stc9.earth_stc9.db.dao;

import org.apache.log4j.Logger;
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
import java.util.List;

@Component
public class GroupsStudentsDAO implements IGroupsStudentsDAO {
    private ConnectionManager conManager = ConnectionManagerJDBCImpl.getInstance();
    private Logger logger = Logger.getLogger(GroupsStudentsDAO.class);

    @Override
    public List<User> getGroupsStudents(int groupname) {
        List<User> result = new ArrayList<>();
        try {
            try (Connection connection = conManager.getConnection()) {
                try (PreparedStatement statement = connection.prepareStatement("SELECT users.id, users.fullName," +
                        " student.group_id ,g.name, g.descr\n" +
                        "FROM users INNER JOIN group_students student " +
                        "on users.id = student.student_id\n" +
                        "  INNER JOIN groups g on student.group_id = g.id WHERE g.id=?")) {
                    statement.setInt(1, groupname);
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
                }


            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return result;
    }


    @Override
    public boolean addStudentGroup(GroupStudents groupStudents) {
        try (Connection connection = conManager.getConnection()) {
                try (PreparedStatement statement = connection.prepareStatement("INSERT INTO group_students " +
                        "(student_id, group_id ) VALUES ( ?, ?)")) {
                    statement.setInt(1, groupStudents.getStudentIdGS());
                    statement.setInt(2, groupStudents.getGroupIdGS());
                    logger.info("Add student in group" + groupStudents.toString());
                    return statement.execute();
                }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            return false;
        }
    }

    @Override
    public GroupStudents getUserById(int id) {
        try (Connection connection = conManager.getConnection()) {
            GroupStudents groupStudents;
            try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE id=?")) {
                preparedStatement.setInt(1, id);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    groupStudents = null;
                    if (resultSet.next()) {
                        groupStudents = new GroupStudents(resultSet.getInt("id"), resultSet.getInt("student_id"),
                                resultSet.getInt("group_id"));
                    }
                }
            }
            return groupStudents;
        } catch (SQLException e) {
            logger.error(e.getMessage());
            return null;
        }

    }


    @Override
    public boolean deleteStudentGroup(int id) {
        try (Connection connection = conManager.getConnection()) {
                try (PreparedStatement statement = connection.prepareStatement
                        ("DELETE FROM group_students s WHERE s.student_id=?")) {
                    statement.setInt(1, id);
                    logger.info("Deleted student from group" + id);
                    return statement.execute();
                }

        } catch (SQLException e) {
            logger.error(e.getMessage());
            return false;
        }

    }

    @Override
    public boolean updateStudentGroup(GroupStudents groupStudents) {
        try (Connection connection = conManager.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("UPDATE group_students " +
                    "set id = ?, group_id = ?  WHERE student_id = ?")) {
                statement.setInt(1, groupStudents.getIdGS());
                statement.setInt(2, groupStudents.getGroupIdGS());
                statement.setInt(3, groupStudents.getStudentIdGS());
                logger.info("Update student from group" + groupStudents.toString());
                statement.executeUpdate();
            }
            return true;
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }

        return false;
    }

    @Override
    public List<User> getStudentNotGroup() {
        Connection connection = conManager.getConnection();
        List<User> result = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement("SELECT users.id, users.fullname\n" +
                "FROM users\n" +
                "  LEFT JOIN group_students ON (users.id=group_students.student_id)\n" +
                "WHERE group_students.student_id IS NULL and users.role_id=2")) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    User user = new User(resultSet.getInt(1), resultSet.getString(2));
                    result.add(user);
                }
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return result;
    }


}

