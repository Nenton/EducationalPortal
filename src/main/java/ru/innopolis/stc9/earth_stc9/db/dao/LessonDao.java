package ru.innopolis.stc9.earth_stc9.db.dao;

import org.springframework.stereotype.Component;
import ru.innopolis.stc9.earth_stc9.db.connection.ConnectionManager;
import ru.innopolis.stc9.earth_stc9.db.connection.ConnectionManagerJDBCImpl;
import ru.innopolis.stc9.earth_stc9.pojo.Group;
import ru.innopolis.stc9.earth_stc9.pojo.Lesson;
import ru.innopolis.stc9.earth_stc9.pojo.Subject;
import ru.innopolis.stc9.earth_stc9.pojo.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Dao class for Lesson entity
 */
@Component
public class LessonDao implements ILessonDao {

    private ConnectionManager conManager = ConnectionManagerJDBCImpl.getInstance();

    @Override
    public boolean addLesson(Lesson lesson) throws SQLException {
        if (lesson == null) {
            return false;
        }
        try (Connection connection = conManager.getConnection()) {
            PreparedStatement statement = null;
            statement = connection.prepareStatement("insert into lessons(theme, less_date, subject_id, teacher_id, group_id)" +
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
        String sql = "UPDATE \n" +
                "  public.lessons \n" +
                "SET \n" +
                "  theme = ?,\n" +
                "  less_date = ?,\n" +
                "  subject_id = ?,\n" +
                "  teacher_id = ?,\n" +
                "  group_id = ?\n" +
                "WHERE \n" +
                "  id = ? ;\n";
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
        String sql = "SELECT \n" +
                "  L.*\n" +
                "FROM LESSONS L,\n" +
                "     GROUPS G,\n" +
                "     SUBJECTS S,\n" +
                "     USERS U\n" +
                "WHERE L.GROUP_ID = G.ID AND\n" +
                "      L.SUBJECT_ID = S.ID AND\n" +
                "      L.TEACHER_ID = U.ID AND\n" +
                "      L.TEACHER_ID = ?      \n" +
                "ORDER BY L.LESS_DATE\n" +
                "LIMIT ?";
        return getLessonsFromDb(sql, id, count);
    }

    public List<Lesson> getLessonsByStudent(int id, int count) throws SQLException {
        if (id == 0 || count == 0) {
            return Collections.emptyList();
        }
        String sql = "SELECT \n" +
                "  L.*\n" +
                "FROM LESSONS L,\n" +
                "     GROUPS G,\n" +
                "     SUBJECTS S,\n" +
                "     USERS U,\n" +
                "     GROUP_STUDENTS GS\n" +
                "WHERE L.GROUP_ID = G.ID AND\n" +
                "      L.SUBJECT_ID = S.ID AND\n" +
                "      L.TEACHER_ID = U.ID AND\n" +
                "      L.GROUP_ID = GS.GROUP_ID AND \n" +
                "      GS.STUDENT_ID = ?\n" +
                "ORDER BY L.LESS_DATE\n" +
                "LIMIT ?";
        return getLessonsFromDb(sql, id, count);
    }

    @Override
    public boolean existsInJournal(int id) throws SQLException {
        if (id == 0) {
            return false;
        }
        try (Connection connection = conManager.getConnection()) {
            String sql = "select * from journal j where j.lesson_id = ?";
            try (final PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, id);
                try (ResultSet set = statement.executeQuery()) {
                    return set.next();
                }
            }
        }
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
            String theme = resultSet.getString(COLUMN_THEME);
            Date date = resultSet.getDate(COLUMN_LESS_DATE);
            int idTeacher = resultSet.getInt(COLUMN_TEACHER);
            int idSubject = resultSet.getInt(COLUMN_SUBJECT);
            int idGroup = resultSet.getInt(COLUMN_GROUP);

            IUserDao userDao = new UserDao();
            ISubjectDao subjectDao = new SubjectDao();
            IGroupDao groupDao = new GroupDao();
            User teacher = userDao.getUserById(idTeacher);
            Subject subject = subjectDao.getSubjectById(idSubject);
            Group group = groupDao.getGroupById(idGroup);

            lessons.add(new Lesson(idLesson,
                    theme, date,
                    subject, idSubject,
                    teacher, idTeacher,
                    group, idGroup));
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
        statement.setString(1, lesson.getTheme());
        statement.setDate(2, new java.sql.Date(lesson.getDate().getTime()));
        statement.setInt(3, lesson.getSubjectId());
        statement.setInt(4, lesson.getTeacherId());
        statement.setInt(5, lesson.getGroupId());
    }

    /**
     * Parse results from ResultSet into Lesson
     */
    private Lesson getLessonFromDb(ResultSet set) throws SQLException {
        return new Lesson(
                set.getInt(COLUMN_ID),
                set.getString(COLUMN_THEME),
                set.getDate(COLUMN_LESS_DATE),
                set.getInt(COLUMN_SUBJECT),
                set.getInt(COLUMN_TEACHER),
                set.getInt(COLUMN_GROUP)
        );
    }
}
