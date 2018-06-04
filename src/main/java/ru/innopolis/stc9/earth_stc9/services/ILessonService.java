package ru.innopolis.stc9.earth_stc9.services;

import ru.innopolis.stc9.earth_stc9.pojo.Group;
import ru.innopolis.stc9.earth_stc9.pojo.Lesson;
import ru.innopolis.stc9.earth_stc9.pojo.Subject;
import ru.innopolis.stc9.earth_stc9.pojo.User;

import java.util.List;

public interface ILessonService {

    /**
     * Get user by login
     */
    User getUserByLogin(String login);

    /**
     * Get all subjects
     */
    List<Subject> getSubjects();

    /**
     * Get users with role student
     */
    List<User> getStudents();

    /**
     * Get users with role teacher
     */
    List<User> getTeachers();

    /**
     * Get lessons by subject id. Count is size list
     */
    List<Lesson> getLessonsBySubject(int id, int count);

    /**
     * Get lessons by teacher id. Count is size list
     */
    List<Lesson> getLessonsByTeacherId(int id, int count);

    /**
     * Get lessons by student id. Count is size list
     */
    List<Lesson> getLessonsByStudentId(int id, int count);

    /**
     * Get last created lessons. Count is size list
     */
    List<Lesson> getLessonsLast(int count);

    /**
     * Create lesson entity
     */
    boolean createLesson(Lesson lesson);

    /**
     * Delete lesson entity
     */
    boolean deleteLessonById(int idLesson);

    /**
     * Get all created groups
     */
    List<Group> getGroups();
}
