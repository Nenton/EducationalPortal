package ru.innopolis.stc9.earth_stc9.db.dao;

import ru.innopolis.stc9.earth_stc9.pojo.*;

import java.util.List;

/**
 * @author Azat Timershin - created 03.06.2018
 */
public interface IJournalDAO {
    /*
     *  Журнал студентов определенной группы по выбраному предмету
     */
    List<UserForJournal> showJournal(String nameGroup, String nameSubject);

    /*
     * Добавление оценок в журнал
     */

    boolean addJournal(Journal journal);

    /*
     * Обновление оценок в журнале
     */
    boolean updateJournal(Journal journal);

    /*
     * Удаление оценок в журнале
     */
    boolean deleteJournal(int id);

    /*
     * Получение списка предметов определнной группы
     */
    List<Subject> getSubjectGroup(String nameGroup);

    /*
     * Получение списка студентов определнной группы
     */
    public List<User> getStudentsFromGroup(String groupname);

    /*
     * Получение списка тем конкретного предмета
     */
    public List<Lesson> getThemeFromSubject(String subjectname);

    /*
     * Получение списка тем конкретного предмета
     */
    public UserForJournal getEntryFromJournal(int idJournal);


}
