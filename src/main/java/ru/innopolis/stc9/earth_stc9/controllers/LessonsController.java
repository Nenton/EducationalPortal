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

import javax.servlet.http.HttpServletRequest;
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

    @RequestMapping(value = "/lessons", method = RequestMethod.GET)
    public String doGet(HttpServletRequest req, Model model) {
        String login = ((String) req.getSession().getAttribute("login"));
        setLessons(login, model);
        setFields(model);
        return "lessons";
    }

    private void setLessons(String login, Model model) {
        List<Lesson> lessons = null;
        if (login != null && !login.isEmpty()) {
            User userByLogin = serviceLesson.getUserByLogin(login);
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

    private void setFields(Model model) {

        List<User> teachers = serviceLesson.getTeachers();
        List<Subject> subjects = serviceLesson.getSubjects();
        List<Group> groups = serviceLesson.getGroups();

        model.addAttribute("subjects", subjects);
        model.addAttribute("teachers", teachers);
        model.addAttribute("groups", groups);
    }

    @RequestMapping(value = "/lessonAdd", method = RequestMethod.POST)
    public String addLesson(HttpServletRequest req, Model model,
                            @RequestAttribute String subject, @RequestAttribute String teacher,
                            @RequestAttribute String theme, @RequestAttribute String group) {
        try {
            Lesson lesson = null;
            int subjectId = Integer.parseInt(subject);
            int groupId = Integer.parseInt(group);

            if (teacher == null || teacher.isEmpty()) {
                String login = (String) req.getSession().getAttribute("login");
                User userByLogin = serviceLesson.getUserByLogin(login);
                lesson = new Lesson(theme, new Date(), subjectId, userByLogin.getId(), groupId);
            } else {
                int teacherId = Integer.parseInt(teacher);
                lesson = new Lesson(theme, new Date(), subjectId, teacherId, groupId);
            }
            serviceLesson.createLesson(lesson);
        } catch (Exception e) {
            logger.warn("Add lesson", e);
        }
        return doGet(req, model);
    }

    @RequestMapping(value = "/lessonDelete", method = RequestMethod.POST)
    public String deleteLesson(HttpServletRequest req, Model model, @RequestAttribute String lessonId) {
        try {
            int idLesson = Integer.parseInt(lessonId);
            serviceLesson.deleteLessonById(idLesson);
        } catch (Exception e) {
            logger.warn("Delete lesson", e);
        }
        return doGet(req, model);
    }

}
