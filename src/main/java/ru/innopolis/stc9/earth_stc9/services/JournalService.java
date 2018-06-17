package ru.innopolis.stc9.earth_stc9.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.innopolis.stc9.earth_stc9.db.dao.JournalDAO;
import ru.innopolis.stc9.earth_stc9.pojo.*;

import java.util.List;

/**
 * @author Azat Timershin - created 29.05.2018
 */
@Service
public class JournalService implements IJournalService {
    @Autowired
    private JournalDAO journalDAO;

    @Override
    public List<UserForJournal> showJournal(String nameGroup, String nameSubject) {
        return journalDAO.showJournal(nameGroup, nameSubject);
    }

    @Override
    public boolean addJournal(Journal journal) {
        if (journal == null) {
            return false;
        }
        return journalDAO.addJournal(journal);
    }

    @Override
    public boolean updateJournal(Journal journal) {
        if (journal == null)
            return false;
        return journalDAO.updateJournal(journal);
    }

    @Override
    public boolean deleteJournal(int id) {
        if (id == 0)
            return false;
        return journalDAO.deleteJournal(id);
    }

    @Override
    public List<Subject> getSubjectforJournal(String nameGroup) {
        return journalDAO.getSubjectGroup(nameGroup);
    }

    @Override
    public List<User> getStudentsFromGroup(String groupname) {
        return journalDAO.getStudentsFromGroup(groupname);
    }

    @Override
    public List<Lesson> getThemeFromSubject(String subjectname) {
        return journalDAO.getThemeFromSubject(subjectname);
    }

    @Override
    public UserForJournal getEntryFromJournal(int id) {
        return journalDAO.getEntryFromJournal(id);
    }
}
