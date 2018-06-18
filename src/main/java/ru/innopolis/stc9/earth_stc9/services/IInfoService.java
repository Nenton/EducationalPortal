package ru.innopolis.stc9.earth_stc9.services;

public interface IInfoService {
    /**
     * @return Общее количество студентов в системе
     */
    int getStudentsCount();

    /**
     * @return Общее количество преподавателей в системе
     */
    int getTeachersCount();

    /**
     * @return Общее количество предметов в системе
     */
    int getSubjectsCount();

    /**
     * @return Общее количество пройденных уроков в системе
     */
    int getLessonsCount();
}
