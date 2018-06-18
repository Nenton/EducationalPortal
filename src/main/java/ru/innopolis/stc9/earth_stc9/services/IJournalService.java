package ru.innopolis.stc9.earth_stc9.services;

import ru.innopolis.stc9.earth_stc9.pojo.*;

import java.util.List;

/**
 * @author Azat Timershin - created 29.05.2018
 */
public interface IJournalService {

    List<UserForJournal> showJournal(String nameGroup, String nameSubject);

    boolean addJournal(Journal journal);

    boolean updateJournal(Journal journal);

    boolean deleteJournal(int id);

    List<Subject> getSubjectforJournal(String nameGroup);

    public List<User> getStudentsFromGroup(String groupname);

    public List<Lesson> getThemeFromSubject(String subjectname);

    public UserForJournal getEntryFromJournal(int id);
}
