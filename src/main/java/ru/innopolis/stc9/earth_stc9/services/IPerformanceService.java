package ru.innopolis.stc9.earth_stc9.services;

import ru.innopolis.stc9.earth_stc9.pojo.Performance;

import java.util.List;

public interface IPerformanceService {
    List<Performance> getPerformance(String studentLogin, int subjectId);
}
