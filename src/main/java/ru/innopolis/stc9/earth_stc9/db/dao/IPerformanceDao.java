package ru.innopolis.stc9.earth_stc9.db.dao;

import ru.innopolis.stc9.earth_stc9.pojo.Performance;

import java.util.List;

/**
 * @author Aleksandr Tikhonov - created 23.06.2018, modified 23.06.2018
 */

public interface IPerformanceDao {
    List<Performance> getPerformanceByStudenIdSubjectId(String studentLogin, int subjectId);
}
