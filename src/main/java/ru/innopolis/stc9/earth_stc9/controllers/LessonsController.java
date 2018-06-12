package ru.innopolis.stc9.earth_stc9.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.innopolis.stc9.earth_stc9.controllers.users.Roles;
import ru.innopolis.stc9.earth_stc9.pojo.Group;
import ru.innopolis.stc9.earth_stc9.pojo.Lesson;
import ru.innopolis.stc9.earth_stc9.pojo.Subject;
import ru.innopolis.stc9.earth_stc9.pojo.User;
import ru.innopolis.stc9.earth_stc9.services.ILessonService;
import ru.innopolis.stc9.earth_stc9.services.IUsersService;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Controller for show lessons for student or teacher
 */
@Controller
public class LessonsController {
    private static final Logger logger = Logger.getLogger(LessonsController.class);
    @Autowired
    private ILessonService serviceLesson;
    @Autowired
    private IUsersService serviceUser;

    @RequestMapping(value = "/lessons", method = RequestMethod.GET)
    public String doGet(HttpServletRequest req, Model model) {
        String login = ((String) req.getSession().getAttribute("login"));
        setLessons(login, model);
        setFields(login, model);
        return "lessons";
    }

    private void setLessons(String login, Model model) {
        List<Lesson> lessons = null;
        if (login != null && !login.isEmpty()) {
            User userByLogin = serviceUser.getUserByLogin(login);
            switch (userByLogin.getRole().getId()) {
                case Roles.ADMIN_ROLE_ID:
                    lessons = serviceLesson.getLessonsLast(100);
                    break;
                case Roles.STUDENT_ROLE_ID:
                    lessons = serviceLesson.getLessonsByStudentId(userByLogin.getId(), 100);
                    break;
                case Roles.TEACHER_ROLE_ID:
                    lessons = serviceLesson.getLessonsByTeacherId(userByLogin.getId(), 100);
                    break;
                default:
                    break;
            }
        }
        model.addAttribute("lessons", lessons);
    }

    private void setFields(String login, Model model) {

        List<User> teachers = new ArrayList<>();
        List<Subject> subjects = serviceLesson.getSubjects();
        List<Group> groups = serviceLesson.getGroups();

        if (login != null && !login.isEmpty()) {
            User userByLogin = serviceUser.getUserByLogin(login);
            switch (userByLogin.getRole().getId()) {
                case Roles.ADMIN_ROLE_ID:
                    teachers = serviceLesson.getTeachers();
                    break;
                case Roles.TEACHER_ROLE_ID:
                    teachers.add(serviceUser.getUserByLogin(login));
                    break;
                default:
                    break;
            }
        }

        model.addAttribute("subjects", subjects);
        model.addAttribute("teachers", teachers);
        model.addAttribute("groups", groups);
    }

    @RequestMapping(value = "/lessons/add", method = RequestMethod.POST)
    public String addLesson(HttpServletRequest req, Model model,
                            @RequestAttribute String subject, @RequestAttribute String teacher,
                            @RequestAttribute String theme, @RequestAttribute String group,
                            @RequestAttribute String lessDate) {
        try {
            Lesson lesson = null;
            int subjectId = Integer.parseInt(subject);
            int groupId = Integer.parseInt(group);
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date parsedDate = format.parse(lessDate);

            if (teacher == null || teacher.isEmpty()) {
                String login = (String) req.getSession().getAttribute("login");
                User userByLogin = serviceUser.getUserByLogin(login);
                lesson = new Lesson(theme, parsedDate, subjectId, userByLogin.getId(), groupId);
            } else {
                int teacherId = Integer.parseInt(teacher);
                lesson = new Lesson(theme, parsedDate, subjectId, teacherId, groupId);
            }
            serviceLesson.createLesson(lesson);
        } catch (Exception e) {
            logger.warn("Add lesson", e);
        }
        return doGet(req, model);
    }

    @RequestMapping(value = "/lessons/save", method = RequestMethod.POST)
    public String saveLesson(HttpServletRequest req, Model model,
                             @RequestAttribute String subject, @RequestAttribute String teacher,
                             @RequestAttribute String theme, @RequestAttribute String group,
                             @RequestAttribute String lessDate, @RequestAttribute String editLessonId) {
        try {
            int subjectId = Integer.parseInt(subject);
            int groupId = Integer.parseInt(group);
            int teacherId = Integer.parseInt(teacher);
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date parsedDate = format.parse(lessDate);

            Lesson lesson = new Lesson(Integer.parseInt(editLessonId), theme, parsedDate, subjectId, teacherId, groupId);
            serviceLesson.changeLesson(lesson);
        } catch (Exception e) {
            logger.warn("Changed lesson saving", e);
        }
        return doGet(req, model);
    }

    @RequestMapping(value = "/lessons/delete", method = RequestMethod.POST)
    public String deleteLesson(HttpServletRequest req, Model model, @RequestAttribute String lessonId) {
        try {
            int idLesson = Integer.parseInt(lessonId);
            if (serviceLesson.existsInJournal(idLesson)) {
                String message = "Невозможно удаление сведений о занятии, в журнале есть оценка";
                model.addAttribute("message", message);
            } else {
                serviceLesson.deleteLessonById(idLesson);
            }
        } catch (Exception e) {
            logger.warn("Delete lesson", e);
        }
        return doGet(req, model);
    }

    @RequestMapping(value = "/lessons/change", method = RequestMethod.POST)
    public String changeLesson(HttpServletRequest req, Model model,
                               String lessonId) {
        try {
            Lesson editLesson = null;
            editLesson = serviceLesson.getById(Integer.parseInt(lessonId));
            if (serviceLesson.existsInJournal(Integer.parseInt(lessonId))) {
                String message = "Невозможно редактирование сведений о занятии, в журнале есть оценка";
                model.addAttribute("message", message);
            } else {
                model.addAttribute("editLesson", editLesson);
            }
        } catch (Exception e) {
            logger.warn("Change lesson begin", e);
        }
        return doGet(req, model);
    }
}
