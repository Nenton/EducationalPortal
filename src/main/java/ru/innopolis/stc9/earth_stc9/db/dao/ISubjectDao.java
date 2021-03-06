package ru.innopolis.stc9.earth_stc9.db.dao;

import ru.innopolis.stc9.earth_stc9.pojo.Subject;

import java.sql.SQLException;
import java.util.List;

public interface ISubjectDao {
    String COLUMN_ID = "id";
    String COLUMN_SUBJECT_NAME = "name";

    /**
     * Insert subject into DB
     */
    boolean addSubject(Subject subject) throws SQLException;

    /**
     * Delete subject from DB
     */
    boolean deleteSubject(Subject subject) throws SQLException;

    /**
     * Get subject by id
     */
    Subject getSubjectById(int id) throws SQLException;

    /**
     * Update subject from DB
     */
    boolean updateSubject(Subject subject) throws SQLException;

    /**
     * Get all subjects from DB
     */
    List<Subject> getSubjects() throws SQLException;

    /**
     * Получает общее количество предметов в системе
     *
     * @return - количество предметов
     * @throws SQLException
     */
    int getCountSubjects() throws SQLException;
}
