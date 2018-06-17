package ru.innopolis.stc9.earth_stc9.db.dao;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import ru.innopolis.stc9.earth_stc9.db.connection.ConnectionManager;
import ru.innopolis.stc9.earth_stc9.db.connection.ConnectionManagerJDBCImpl;
import ru.innopolis.stc9.earth_stc9.pojo.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Azat Timershin - created 03.06.2018
 */
@Component
public class JournalDAO implements IJournalDAO {
    private ConnectionManager connectionManager = ConnectionManagerJDBCImpl.getInstance();
    private Logger logger = Logger.getLogger(JournalDAO.class);



    @Override
    public List<UserForJournal> showJournal(String nameGroup, String nameSubject) {
        ArrayList<UserForJournal> result = new ArrayList<>();
        try (Connection connection = connectionManager.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("SELECT j.id, users.fullname, s2.name, l.theme, j.mark_date, j.mark, j.attendance, g.name\n" +
                    "FROM users\n" +
                    "INNER JOIN group_students student on users.id = student.student_id\n" +
                    "INNER JOIN journal j on users.id = j.student_id\n" +
                    "INNER JOIN lessons l on j.lesson_id = l.id\n" +
                    "INNER JOIN groups g on student.group_id = g.id\n" +
                    "INNER JOIN subjects s2 on l.subject_id = s2.id\n" +
                    "WHERE g.name=? AND s2.name=?")) {
                statement.setString(1, nameGroup);
                statement.setString(2, nameSubject);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        UserForJournal user = new UserForJournal(resultSet.getInt("id"),
                                resultSet.getString("fullname"),
                                resultSet.getString("name"),
                                resultSet.getString("theme"),
                                resultSet.getDate("mark_date"),
                                resultSet.getInt("mark"),
                                resultSet.getInt("attendance"),
                                resultSet.getString(8));
                        result.add(user);
                        logger.info("Show Jornal" + nameGroup + nameSubject);
                    }
                }
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return result;
    }



    @Override
    public boolean addJournal(Journal journal) {
        if (journal == null) {
            return false;
        }
        try (Connection connection = connectionManager.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("INSERT INTO " +
                    "journal(student_id,lesson_id,mark_date,mark,attendance)" +
                    " VALUES (?,?,?,?,?)")) {
                statement.setInt(1, journal.getStudentId());
                statement.setInt(2, journal.getLessonId());
                statement.setDate(3, journal.getDate());
                statement.setInt(4, journal.getMark());
                statement.setInt(5, journal.getAttendance());
                logger.info("Add in Journal" + journal.getId());
                statement.execute();
            }
            return true;
        } catch (SQLException e) {
            logger.error(e.getMessage());
            return false;
        }

    }


    @Override
    public boolean updateJournal(Journal journal) {
        if (journal == null) {
            return false;
        }
        try (Connection connection = connectionManager.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("UPDATE journal " +
                    "set student_id=?, lesson_id=?, mark_date=?, mark=?, attendance=? WHERE id  = ?")) {
                statement.setInt(1, journal.getStudentId());
                statement.setInt(2, journal.getLessonId());
                statement.setDate(3, journal.getDate());
                statement.setInt(4, journal.getMark());
                statement.setInt(5, journal.getAttendance());
                statement.setInt(6, journal.getId());
                logger.info("Update in Jornal" + journal.getId());
                statement.executeUpdate();

                return true;
            }

        } catch (SQLException e) {
            logger.error(e.getMessage());
            return false;
        }
    }


    @Override
    public boolean deleteJournal(int id) {
        if (id == 0) {
            return false;
        }
        try (Connection connection = connectionManager.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("DELETE FROM journal WHERE id=?")) {
                statement.setInt(1, id);
                logger.info("Delete in Journal" + id);
                return statement.execute();
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            return false;
        }

    }


    @Override
    public List<Subject> getSubjectGroup(String nameGroup) {
        ArrayList<Subject> result = new ArrayList<>();
        try (Connection connection = connectionManager.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("SELECT Distinct g.name, subjects.name FROM subjects\n" +
                    "JOIN lessons l on subjects.id = l.subject_id\n" +
                    "JOIN groups g on l.group_id = g.id\n" +
                    "WHERE  g.name=?")) {
                statement.setString(1, nameGroup);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        result.add(new Subject(resultSet.getString(2), resultSet.getString(1)));
                        logger.info("Get subject for group" + nameGroup);
                        return result;
                    }
                }
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return result;
    }

    @Override
    public List<User> getStudentsFromGroup(String groupname) {
        List<User> result = new ArrayList<>();
        Connection connection = connectionManager.getConnection();
        try (PreparedStatement statement = connection.prepareStatement("SELECT users.id, users.fullName," +
                " student.group_id ,g.name, g.descr" +
                "" +
                "\n" +
                "FROM users INNER JOIN group_students student " +
                "on users.id = student.student_id\n" +
                "  INNER JOIN groups g on student.group_id = g.id WHERE g.name=?")) {
            statement.setString(1, groupname);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    User user = new User(resultSet.getInt("id"),

                            resultSet.getString("fullName"), resultSet.getString("name"));
                    result.add(user);
                }
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());

        }
        return result;
    }

    @Override
    public List<Lesson> getThemeFromSubject(String subjectname) {
        List<Lesson> result = new ArrayList<>();
        Connection connection = connectionManager.getConnection();
        try (PreparedStatement statement = connection.prepareStatement("SELECT lessons.id, lessons.theme, s2.name FROM lessons\n" +
                "INNER JOIN subjects s2 on lessons.subject_id = s2.id\n" +
                "WHERE s2.name=?")) {
            statement.setString(1, subjectname);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Lesson lesson = new Lesson(resultSet.getInt(1), resultSet.getString(2),
                            resultSet.getString(3));
                    result.add(lesson);
                }
            }

        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return result;
    }

    @Override
    public UserForJournal getEntryFromJournal(int idJournal) {
        UserForJournal userForJournal = null;
        Connection connection = connectionManager.getConnection();
        try {
            try (PreparedStatement statement = connection.prepareStatement("SELECT j.id, users.fullname, users.id, s2.name, l.theme, " +
                    "l.id, j.mark_date, j.mark, j.attendance, g.name\n" +
                    "                    FROM users\n" +
                    "                    INNER JOIN group_students student on users.id = student.student_id\n" +
                    "                    INNER JOIN journal j on users.id = j.student_id\n" +
                    "                    INNER JOIN lessons l on j.lesson_id = l.id\n" +
                    "                    INNER JOIN groups g on student.group_id = g.id\n" +
                    "                    INNER JOIN subjects s2 on l.subject_id = s2.id\n" +
                    "                    WHERE j.id=?")) {
                statement.setInt(1, idJournal);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        userForJournal = new UserForJournal(resultSet.getInt(1), resultSet.getString(2),
                                resultSet.getInt(3), resultSet.getString(4), resultSet.getString(5),
                                resultSet.getInt(6), resultSet.getDate(7), resultSet.getInt(8),
                                resultSet.getInt(9), resultSet.getString(10));

                    }
                }
            }

        } catch (SQLException e) {
            logger.error(e.getMessage());
        }

        return userForJournal;
    }


}

