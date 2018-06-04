package ru.innopolis.stc9.earth_stc9.db.dao;

import ru.innopolis.stc9.earth_stc9.db.connection.ConnectionManager;
import ru.innopolis.stc9.earth_stc9.db.connection.ConnectionManagerJDBCImpl;
import ru.innopolis.stc9.earth_stc9.pojo.Lesson;
import ru.innopolis.stc9.earth_stc9.pojo.Subject;
import ru.innopolis.stc9.earth_stc9.pojo.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Dao class for Lesson entity
 */
public class LessonDao implements ILessonDao {

    private ConnectionManager conManager = ConnectionManagerJDBCImpl.getInstance();

    @Override
    public boolean addLesson(Lesson lesson) throws SQLException {
        if (lesson == null) {
            return false;
        }
        try (Connection connection = conManager.getConnection()) {
            PreparedStatement statement = null;
            statement = connection.prepareStatement("insert into lessons(subject, student, teacher, mark, attendance)" +
                    " values (?, ?, ?, ?, ?)");
            setParamsIntoStatement(statement, lesson);
            return statement.execute();
        }
    }

    @Override
    public boolean deleteLesson(Lesson lesson) throws SQLException {
        if (lesson == null) {
            return false;
        }
        return deleteLessonById(lesson.getId());
    }

    @Override
    public Lesson getLessonById(int id) throws SQLException {
        if (id == 0) {
            return null;
        }
        try (Connection connection = conManager.getConnection()) {
            String sql = "select * from lessons where id = ?";
            try (final PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, id);
                try (ResultSet set = statement.executeQuery()) {
                    if (set.next()) {
                        return getLessonFromDb(set);
                    }
                }
            }
        }
        return null;
    }

    @Override
    public boolean updateLesson(Lesson lesson) throws SQLException {
        if (lesson == null) {
            return false;
        }
        String sql = "update lessons " +
                "set subject = ?, student = ?, teacher = ?, mark = ?, attendance = ? where id = ?";
        try (Connection connection = conManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            setParamsIntoStatement(statement, lesson);
            statement.setInt(6, lesson.getId());
            statement.executeUpdate();
            return true;
        }
    }

    public List<Lesson> getLessonsBySubject(int id, int count) throws SQLException {
        if (id == 0) {
            return Collections.emptyList();
        }
        String sql = "select lessons.* from lessons where lessons.subject = ? order by lessons.id desc limit ?";
        return getLessonsFromDb(sql, id, count);
    }

    @Override
    public boolean deleteLessonById(int idLesson) throws SQLException {
        String sql = "delete from lessons where id = ?";
        try (Connection connection = conManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idLesson);
            return statement.execute();
        }
    }

    public List<Lesson> getLessonsByTeacher(int id, int count) throws SQLException {
        if (id == 0 || count == 0) {
            return Collections.emptyList();
        }
        String sql = "select lessons.* from lessons\n" +
                "where lessons.teacher = ? order by lessons.id desc limit ?";
        return getLessonsFromDb(sql, id, count);
    }

    public List<Lesson> getLessonsByStudent(int id, int count) throws SQLException {
        if (id == 0 || count == 0) {
            return Collections.emptyList();
        }
        String sql = "select lessons.*\n" +
                "from lessons where lessons.student = ? order by lessons.id desc limit ?";
        return getLessonsFromDb(sql, id, count);
    }

    private List<Lesson> getLessonsFromDb(String sql, int id, int count) throws SQLException {
        try (Connection connection = conManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.setInt(2, count);
            ResultSet resultSet = statement.executeQuery();
            return parseResultSet(resultSet);
        }
    }

    @Override
    public List<Lesson> getLessons(int count) throws SQLException {
        if (count == 0) {
            return Collections.emptyList();
        }
        String sql = "select * from lessons order by lessons.id desc limit ?;";
        try (Connection connection = conManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, count);
            ResultSet resultSet = statement.executeQuery();
            return parseResultSet(resultSet);
        }
    }

    /**
     * Parse results from ResultSer into List
     */
    private List<Lesson> parseResultSet(ResultSet resultSet) throws SQLException {
        List<Lesson> lessons = new ArrayList<>();
        while (resultSet.next()) {
            int idLesson = resultSet.getInt(COLUMN_ID);
            int idStudent = resultSet.getInt(COLUMN_STUDENT);
            int idTeacher = resultSet.getInt(COLUMN_TEACHER);
            int idSubject = resultSet.getInt(COLUMN_SUBJECT);
            int mark = resultSet.getInt(COLUMN_MARK);
            boolean attendance = resultSet.getBoolean(COLUMN_ATTENDANCE);

            IUserDao userDao = new UserDao();
            ISubjectDao subjectDao = new SubjectDao();
            User student = userDao.getUserById(idStudent);
            User teacher = userDao.getUserById(idTeacher);
            Subject subject = subjectDao.getSubjectById(idSubject);

            lessons.add(new Lesson(idLesson,
                    subject, subject.getId(),
                    student, student.getId(),
                    teacher, teacher.getId(),
                    mark,
                    attendance));
        }
        return lessons;
    }

    /**
     * Mapping statement from lesson entity
     */
    private void setParamsIntoStatement(PreparedStatement statement, Lesson lesson) throws SQLException {
        if (statement == null || lesson == null) {
            return;
        }
        statement.setInt(1, lesson.getSubjectId());
        statement.setInt(2, lesson.getStudentId());
        statement.setInt(3, lesson.getTeacherId());
        statement.setInt(4, lesson.getMark());
        statement.setBoolean(5, lesson.isAttendance());
    }

    /**
     * Parse results from ResultSet into Lesson
     */
    private Lesson getLessonFromDb(ResultSet set) throws SQLException {
        return new Lesson(
                set.getInt(COLUMN_ID),
                set.getInt(COLUMN_SUBJECT),
                set.getInt(COLUMN_STUDENT),
                set.getInt(COLUMN_TEACHER),
                set.getInt(COLUMN_MARK),
                set.getBoolean(COLUMN_ATTENDANCE)
        );
    }
}
