package ru.innopolis.stc9.earth_stc9.services;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import ru.innopolis.stc9.earth_stc9.db.dao.*;
import ru.innopolis.stc9.earth_stc9.pojo.Lesson;
import ru.innopolis.stc9.earth_stc9.pojo.Subject;
import ru.innopolis.stc9.earth_stc9.pojo.User;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

@Service
public class LessonService implements ILessonService {
    private static Logger logger = Logger.getLogger(LessonService.class);
    private ILessonDao lessonDao = new LessonDao();
    private ISubjectDao subjectDao = new SubjectDao();
    private IUserDao userDao = new UserDao();

    @Override
    public User getUserByLogin(String login) {
        if (login == null || login.isEmpty()) {
            return null;
        }
        try {
            return userDao.getUserByLogin(login);
        } catch (SQLException e) {
            logger.warn("Ошибка получения пользователя", e);
        }
        return null;
    }

    @Override
    public List<Subject> getSubjects() {
        try {
            return subjectDao.getSubjects();
        } catch (SQLException e) {
            logger.warn("Ошибка получения предметов", e);
        }
        return Collections.emptyList();
    }

    @Override
    public List<User> getStudents() {
        try {
            return userDao.getUsers(3);
        } catch (SQLException e) {
            logger.warn("Ошибка получения студентов", e);
        }
        return Collections.emptyList();
    }

    @Override
    public List<User> getTeachers() {
        try {
            return userDao.getUsers(4);
        } catch (SQLException e) {
            logger.warn("Ошибка получения учителей", e);
        }
        return Collections.emptyList();
    }

    @Override
    public List<Lesson> getLessonsBySubject(int id, int count) {
        if (id == 0 || count == 0) {
            return Collections.emptyList();
        }
        try {
            return lessonDao.getLessonsBySubject(id, count);
        } catch (SQLException e) {
            logger.warn("Ошибка получения занятий предмета с id - " + id, e);
        }
        return Collections.emptyList();
    }

    @Override
    public List<Lesson> getLessonsByTeacherId(int id, int count) {
        if (id == 0 || count == 0) {
            return Collections.emptyList();
        }
        try {
            return lessonDao.getLessonsByTeacher(id, count);
        } catch (SQLException e) {
            logger.warn("Ошибка получения занятий учителя с id - " + id, e);
        }
        return Collections.emptyList();
    }

    @Override
    public List<Lesson> getLessonsByStudentId(int id, int count) {
        if (id == 0 || count == 0) {
            return Collections.emptyList();
        }
        try {
            return lessonDao.getLessonsByStudent(id, count);
        } catch (SQLException e) {
            logger.warn("Ошибка получения занятий студента с id - " + id, e);
        }
        return Collections.emptyList();
    }

    @Override
    public List<Lesson> getLessonsLast(int count) {
        if (count == 0) {
            return Collections.emptyList();
        }
        try {
            return lessonDao.getLessons(count);
        } catch (SQLException e) {
            logger.warn("Ошибка получения последних созданных занятий", e);
        }
        return Collections.emptyList();
    }

    @Override
    public boolean createLesson(Lesson lesson) {
        if (lesson == null) {
            return false;
        }
        try {
            return lessonDao.addLesson(lesson);
        } catch (SQLException e) {
            logger.warn("Ошибка создания занятия", e);
        }
        return false;
    }

    @Override
    public boolean deleteLessonById(int idLesson) {
        if (idLesson == 0) {
            return false;
        }
        try {
            return lessonDao.deleteLessonById(idLesson);
        } catch (SQLException e) {
            logger.warn("Ошибка удаления занятия с id - " + idLesson, e);
        }
        return false;
    }

    public void setLessonDao(ILessonDao lessonDao) {
        this.lessonDao = lessonDao;
    }

    public void setSubjectDao(ISubjectDao subjectDao) {
        this.subjectDao = subjectDao;
    }

    public void setUserDao(IUserDao userDao) {
        this.userDao = userDao;
    }
}
