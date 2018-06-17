package ru.innopolis.stc9.earth_stc9.controllers.journal;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.innopolis.stc9.earth_stc9.pojo.*;
import ru.innopolis.stc9.earth_stc9.services.IGroupService;
import ru.innopolis.stc9.earth_stc9.services.IJournalService;

import java.sql.Date;
import java.util.List;

@Controller
public class JournalContoller {
    private static final Logger logger = Logger.getLogger(JournalContoller.class);
    @Autowired
    public IJournalService serviceJournal;
    @Autowired
    private IGroupService service;

    /*
     * Получение списка групп
     *
     */
    @RequestMapping(value = "/journal", method = RequestMethod.GET)
    public String getGroups(Model model) {
        logger.info("Method \"getGroups()\" from GroupController done " + this.getClass().getName());
        model.addAttribute("groups", service.getGroups());
        return "journal";
    }

    /*
     * Получение списка предметов одной группы
     *
     */

    @RequestMapping(value = "/journal/{groupname}", method = RequestMethod.GET)
    public String get1SubjectforJournalany(@PathVariable("groupname") String groupname, Model model) {
        logger.info("Method getSubjectforJournal" + this.getClass().getName());
        List<Subject> result = serviceJournal.getSubjectforJournal(groupname);
        model.addAttribute("subject", result);
        return "showsubjectgroup";
    }


    /*
     * Добавление записи в журнал
     *
     */

    @RequestMapping(value = "/journal/add", method = RequestMethod.POST)
    public String getFirstPage(@RequestParam String student, @RequestParam String lesson,
                               @RequestParam String markDate, @RequestParam String mark, @RequestParam
                                       String attendance, @RequestParam String namesubject, @RequestParam String namegroup,
                               Model model) {
        int studentid = Integer.parseInt(student);
        int lessonid = Integer.parseInt(lesson);
        Date markdate = Date.valueOf(markDate);
        Integer markstudent = Integer.parseInt(mark);
        Integer attendancestudent = Integer.parseInt(attendance);
        serviceJournal.addJournal(new Journal(studentid, lessonid, markdate, markstudent, attendancestudent));
        logger.info("Successful add in DB" + student + lesson + markDate + mark);
        return getJournal(namegroup, namesubject, model);

    }

    /*
     * Получение журнала по группе и предмету
     *
     */

    @RequestMapping(value = "/journal/showjournal", method = RequestMethod.POST)
    public String getJournal(@RequestParam String namegroup, String namesubject, Model model) {
        List<UserForJournal> result = serviceJournal.showJournal(namegroup, namesubject);
        List<Lesson> subject = serviceJournal.getThemeFromSubject(namesubject);
        List<User> group = serviceJournal.getStudentsFromGroup(namegroup);
        logger.info("Successful get in DB" + namegroup + namesubject);
        model.addAttribute("studentjournal", result);
        model.addAttribute("subject", subject);
        model.addAttribute("group", group);
        return "showjournal";
    }

    /*
     *  Получение конкретной записи для редактирования
     */
    @RequestMapping(value = "/journal/edit", method = RequestMethod.GET)
    public String getEntryForIdit(@RequestParam String id, Model model) {
        UserForJournal userforjournal = serviceJournal.getEntryFromJournal(Integer.valueOf(id));
        model.addAttribute("userforjournal", userforjournal);
        return "editentryfromjournal";
    }

    /*
     * Обновление записи в журнале
     *
     */
    @RequestMapping(value = "/journal/update", method = RequestMethod.POST)
    public String updateJournal(@RequestParam String journalid, @RequestParam String studentid, @RequestParam String lessonid,
                                @RequestParam String datemark, @RequestParam String mark,
                                @RequestParam String attendance, @RequestParam String groupname, @RequestParam String subjectname, Model model) {
        serviceJournal.updateJournal(new Journal(Integer.parseInt(journalid), Integer.parseInt(studentid),
                Integer.parseInt(lessonid), Date.valueOf(datemark), Integer.parseInt(mark),
                Integer.parseInt(attendance)));
        logger.info("Successful update in the DB" + journalid);
        return getJournal(groupname, subjectname, model);

    }
    /*
     * Удаление из журнала
     *
     */
    @RequestMapping(value = "/journal/delete", method = RequestMethod.POST)
    public String updateStudentGroup(@RequestParam String id, @RequestParam String subject,
                                     @RequestParam String group, Model model) {
        serviceJournal.deleteJournal(Integer.parseInt(id));
        logger.info("Successful deleted from the DB" + id);
        return getJournal(group, subject, model);

    }
}




