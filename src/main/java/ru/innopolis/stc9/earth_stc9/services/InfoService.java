package ru.innopolis.stc9.earth_stc9.services;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.innopolis.stc9.earth_stc9.db.dao.ILessonDao;
import ru.innopolis.stc9.earth_stc9.db.dao.ISubjectDao;
import ru.innopolis.stc9.earth_stc9.db.dao.IUserDao;

@Service
public class InfoService implements IInfoService {
    private static final Logger logger = Logger.getLogger(InfoService.class);
    private final IUserDao userDao;
    private final ISubjectDao subjectDao;
    private final ILessonDao lessonDao;

    @Autowired
    public InfoService(IUserDao userDao, ISubjectDao subjectDao, ILessonDao lessonDao) {
        this.userDao = userDao;
        this.subjectDao = subjectDao;
        this.lessonDao = lessonDao;
    }

    @Override
    public int getStudentsCount() {
        try {
            return userDao.getCountUsersByRoleName("ROLE_STUDENT");
        } catch (Exception e) {
            logger.warn("Ошибка получения количества студентов", e);
            return 0;
        }
    }

    @Override
    public int getTeachersCount() {
        try {
            return userDao.getCountUsersByRoleName("ROLE_TEACHER");
        } catch (Exception e) {
            logger.warn("Ошибка получения количества преподавателей", e);
            return 0;
        }
    }

    @Override
    public int getSubjectsCount() {
        try {
            return subjectDao.getCountSubjects();
        } catch (Exception e) {
            logger.warn("Ошибка получения количества предметов", e);
            return 0;
        }
    }

    @Override
    public int getLessonsCount() {
        try {
            return lessonDao.getCountLessons();
        } catch (Exception e) {
            logger.warn("Ошибка получения количества занятий", e);
            return 0;
        }
    }
}
