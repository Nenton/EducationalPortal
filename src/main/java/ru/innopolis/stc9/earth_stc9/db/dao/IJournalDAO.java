package ru.innopolis.stc9.earth_stc9.db.dao;

import ru.innopolis.stc9.earth_stc9.pojo.Journal;
import ru.innopolis.stc9.earth_stc9.pojo.Subject;
import ru.innopolis.stc9.earth_stc9.pojo.UserForJournal;

import java.util.List;

/**
 * @author Azat Timershin - created 03.06.2018
 */
public interface IJournalDAO {
    List<UserForJournal> showJournal(String nameGroup, String nameSubject);

    boolean addJournal(Journal journal);

    boolean updateJournal(Journal journal);

    boolean deleteJournal(int id);

    List<Subject> getSubjectGroup(String nameGroup);


}
