package ru.innopolis.stc9.earth_stc9.db.dao;

import ru.innopolis.stc9.earth_stc9.pojo.Lesson;

import java.sql.SQLException;
import java.util.List;

public interface ILessonDao {
    String COLUMN_ID = "id";
    String COLUMN_THEME = "theme";
    String COLUMN_LESS_DATE = "less_date";
    String COLUMN_SUBJECT = "subject_id";
    String COLUMN_TEACHER = "teacher_id";
    String COLUMN_GROUP = "group_id";

    /**
     * Insert lesson into DB
     */
    boolean addLesson(Lesson lesson) throws SQLException;

    /**
     * Delete lesson from DB
     */
    boolean deleteLesson(Lesson lesson) throws SQLException;

    /**
     * Get lesson from DB by lesson id
     */
    Lesson getLessonById(int id) throws SQLException;

    /**
     * Update lesson from DB
     */
    boolean updateLesson(Lesson lesson) throws SQLException;

    /**
     * Delete lesson from DB by lesson id
     */
    boolean deleteLessonById(int idLesson) throws SQLException;

    /**
     * Get last lesson from DB
     *
     * @param count Count lessons in array
     */
    List<Lesson> getLessons(int count) throws SQLException;

    /**
     * Get lessons from DB by subjectId
     *
     * @param count Count lessons in array
     */
    List<Lesson> getLessonsBySubject(int id, int count) throws SQLException;

    /**
     * Get lessons from DB by teacher id
     *
     * @param count Count lessons in array
     */
    List<Lesson> getLessonsByTeacher(int id, int count) throws SQLException;

    /**
     * Get lessons from DB by student id
     *
     * @param count Count lessons in array
     */
    List<Lesson> getLessonsByStudent(int id, int count) throws SQLException;

    /**
     * Проверка использования записи о занятии в журнале
     *
     * @param id - занятие
     * @return true при использовании записи в журнале, иначе false
     */
    boolean existsInJournal(int id) throws SQLException;

    /**
     * Получение общего числа занятий в системе
     *
     * @return - количество занятий
     * @throws SQLException
     */
    int getCountLessons() throws SQLException;
}
