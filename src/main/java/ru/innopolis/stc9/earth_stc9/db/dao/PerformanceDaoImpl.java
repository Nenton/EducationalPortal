package ru.innopolis.stc9.earth_stc9.db.dao;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import ru.innopolis.stc9.earth_stc9.db.connection.ConnectionManager;
import ru.innopolis.stc9.earth_stc9.db.connection.ConnectionManagerJDBCImpl;
import ru.innopolis.stc9.earth_stc9.pojo.Performance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Aleksandr Tikhonov - created 23.06.2018, modified 23.06.2018
 */

@Component
public class PerformanceDaoImpl implements IPerformanceDao {
    private static final Logger logger = Logger.getLogger(PerformanceDaoImpl.class);
    private static final String COLUMN_LESSON_ID = "lessonid";
    private static final String COLUMN_LESSON_THEME = "lessontheme";
    private static final String COLUMN_LESSON_DATE = "lessondate";
    private static final String COLUMN_MARK = "mark";
    private static final String COLUMN_MARK_DATE = "markdate";
    private static final String COLUMN_ATTENDANCE = "attendance";
    private static final String COLUMN_SUBJECT_NAME = "subjectname";
    private static final ConnectionManager conManager = ConnectionManagerJDBCImpl.getInstance();

    @Override
    public List<Performance> getPerformanceByStudenIdSubjectId(String studentLogin, int subjectId) {
        String sql = "SELECT j.mark_date AS markdate, j.mark AS mark, j.attendance AS attendance, " +
                "l.id AS lessonid, l.theme AS lessontheme, l.less_date AS lessondate, s.name AS subjectname FROM users u " +
                "INNER JOIN journal j on u.id = j.student_id " +
                "INNER JOIN lessons l ON j.lesson_id = l.id " +
                "INNER JOIN subjects s on l.subject_id = s.id WHERE u.login = ? AND s.id = ?;";
        List<Performance> listPerformance = new ArrayList<>();
        if (studentLogin != null && subjectId != 0) {
            try (Connection connection = conManager.getConnection()) {
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setString(1, studentLogin);
                    statement.setInt(2, subjectId);
                    try (ResultSet resultSet = statement.executeQuery()) {
                        while (resultSet.next()) {
                            listPerformance.add(getPerformFromDB(resultSet));
                        }
                    }
                }
            } catch (SQLException e) {
                logger.info("SQLException is occurred in PerformanceDaoImpl.getPerformanceByStudenIdSubjectId() " + e);
                return Collections.emptyList();
            }
        }
        return listPerformance;
    }

    private Performance getPerformFromDB(ResultSet resultSet) throws SQLException {
        return new Performance(
                resultSet.getInt(COLUMN_LESSON_ID),
                resultSet.getString(COLUMN_LESSON_THEME),
                resultSet.getDate(COLUMN_LESSON_DATE),
                resultSet.getInt(COLUMN_MARK),
                resultSet.getDate(COLUMN_MARK_DATE),
                resultSet.getInt(COLUMN_ATTENDANCE),
                resultSet.getString(COLUMN_SUBJECT_NAME));

    }
}
