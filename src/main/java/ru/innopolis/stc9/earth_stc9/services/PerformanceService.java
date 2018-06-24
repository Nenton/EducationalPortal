package ru.innopolis.stc9.earth_stc9.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.innopolis.stc9.earth_stc9.db.dao.IPerformanceDao;
import ru.innopolis.stc9.earth_stc9.pojo.Performance;

import java.util.Collections;
import java.util.List;

@Service
public class PerformanceService implements IPerformanceService {

    @Autowired
    private IPerformanceDao performanceDao;

    @Override
    public List<Performance> getPerformance(String studentLogin, int subjectId) {
        if (studentLogin != null && subjectId != 0) {
            return performanceDao.getPerformanceByStudenIdSubjectId(studentLogin, subjectId);
        }
        return Collections.emptyList();
    }
}
