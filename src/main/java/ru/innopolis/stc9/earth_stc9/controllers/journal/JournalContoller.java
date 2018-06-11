package ru.innopolis.stc9.earth_stc9.controllers.journal;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.innopolis.stc9.earth_stc9.pojo.Journal;
import ru.innopolis.stc9.earth_stc9.pojo.Subject;
import ru.innopolis.stc9.earth_stc9.pojo.UserForJournal;
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

   /* @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getindex(Model model) {
        return "index";
    }*/


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
        model.addAttribute("message", "Пожалуйста, введите значения параметров");
        return "showsubjectgroup";
    }


    /*
     * Добавление записи в журнал
     *
     */


    @RequestMapping(value = "/journal/add", method = RequestMethod.POST)
    public String getFirstPage(@RequestParam String studentId, @RequestParam String lessonId,
                               @RequestParam String markDate, @RequestParam String mark, @RequestParam
                                       String attendance, Model model) {
        if (studentId == null && lessonId == null) {
            model.addAttribute("message", "Пожалуйста, введите значения параметров");
            return "showjournal";
        }
        try {
            int studentid = Integer.parseInt(studentId);
            int lessonid = Integer.parseInt(lessonId);
            Date markdate = Date.valueOf(markDate);
            int Mark = Integer.parseInt(mark);
            int Attendance = Integer.parseInt(mark);
            if (serviceJournal.addJournal(new Journal(studentid, lessonid, markdate, Mark, Attendance))) {
                logger.info("Successful add in DB" + studentId + lessonId + markDate + mark);
                // model.addAttribute("message", "Запись успешно добавлена в журнал");
                return "redirect:/journal/showjournal";
            } else {
                model.addAttribute("message", "Запись не добавлена в журнал");
                logger.info("Do not Successful add in DB" + studentId + lessonId + markDate + mark);
                return "journal";
            }
        } catch (NumberFormatException e) {
            model.addAttribute("message", "Пожалуйста, введите корретное значение");
            return "journal";
        }

    }

    /*
     * Получение журнала по группе и предмету
     *
     */
    @RequestMapping(value = "/journal/showjournal", method = RequestMethod.POST)
    public String getJournal(@RequestParam String namegroup, String namesubject, Model model) {
        List<UserForJournal> result = serviceJournal.showJournal(namegroup, namesubject);
        logger.info("Successful get in DB" + namegroup + namesubject);
        model.addAttribute("studentjournal", result);
        return "showjournal";
    }

    @RequestMapping(value = "/journal/delete", method = RequestMethod.POST)
    public String deleteStudentGroup(@RequestParam String id, @RequestParam String subject,
                                     @RequestParam String group, Model model) {
        serviceJournal.deleteJournal(Integer.parseInt(id));
        logger.info("Successful deleted from the DB" + id);
        return getJournal(group, subject, model);

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





