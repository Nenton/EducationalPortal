package ru.innopolis.stc9.earth_stc9.db.dao;

import org.springframework.stereotype.Component;
import ru.innopolis.stc9.earth_stc9.db.connection.ConnectionManager;
import ru.innopolis.stc9.earth_stc9.db.connection.ConnectionManagerJDBCImpl;
import ru.innopolis.stc9.earth_stc9.pojo.Subject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class SubjectDao implements ISubjectDao {
    private ConnectionManager conManager = ConnectionManagerJDBCImpl.getInstance();

    @Override
    public boolean addSubject(Subject subject) throws SQLException {
        if (subject == null) {
            return false;
        }
        String sql = "insert into subjects(subject_name) values (?)";
        try (Connection connection = conManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, subject.getName());
            return statement.execute();
        }
    }

    @Override
    public boolean deleteSubject(Subject subject) throws SQLException {
        if (subject == null) {
            return false;
        }
        String sql = "delete from subjects where id = ?";
        try (Connection connection = conManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, subject.getId());
            return statement.execute();
        }
    }

    @Override
    public Subject getSubjectById(int id) throws SQLException {
        String sql = "SELECT * FROM subjects WHERE id=?";
        try (Connection connection = conManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            return getSubjectFromResultSet(statement);
        }
    }

    private Subject getSubjectFromResultSet(PreparedStatement statement) throws SQLException {
        try (ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                return getSubjectFromResultSet(resultSet);
            }
        }
        return null;
    }

    @Override
    public List<Subject> getSubjects() throws SQLException {
        String sql = "SELECT * FROM subjects";
        try (Connection connection = conManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            return getSubjectsFromResultSet(statement);
        }
    }

    private List<Subject> getSubjectsFromResultSet(PreparedStatement statement) throws SQLException {
        List<Subject> subjects = new ArrayList<>();

        try (ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                subjects.add(getSubjectFromResultSet(resultSet));
            }
            return subjects;
        }
    }

    @Override
    public boolean updateSubject(Subject subject) throws SQLException {
        if (subject == null) {
            return false;
        }
        String sql = "update subjects set subject_name = ? where id = ?";
        try (Connection connection = conManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, subject.getName());
            statement.setInt(2, subject.getId());
            statement.executeUpdate();
            return true;
        }
    }

    /**
     * Get subject from DB
     */
    private Subject getSubjectFromResultSet(ResultSet set) throws SQLException {
        return new Subject(set.getInt(COLUMN_ID),
                set.getString(COLUMN_SUBJECT_NAME));
    }
}
