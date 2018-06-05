package ru.innopolis.stc9.earth_stc9.controllers;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.innopolis.stc9.earth_stc9.controllers.users.Roles;
import ru.innopolis.stc9.earth_stc9.pojo.Lesson;
import ru.innopolis.stc9.earth_stc9.pojo.User;
import ru.innopolis.stc9.earth_stc9.services.ILessonService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Controller for all need compressed information for user
 */
@Controller
public class DashboardController {
    private static final Logger logger = Logger.getLogger(DashboardController.class);
    @Autowired
    private ILessonService service;

    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public String doGet(HttpServletRequest req, Model model) {
        logger.info("doGet" + this.getClass().getName());
        String login = ((String) req.getSession().getAttribute("login"));
        List<Lesson> lessons = null;
        if (login != null && !login.isEmpty()) {
            User user = service.getUserByLogin(login);
            switch (user.getRole().getId()) {
                case Roles.STUDENT_ROLE_ID:
                    lessons = service.getLessonsByStudentId(user.getId(), 10);
                    break;
                case Roles.TEACHER_ROLE_ID:
                    lessons = service.getLessonsByTeacherId(user.getId(), 10);
                    break;
                case Roles.ADMIN_ROLE_ID:
                    lessons = service.getLessonsLast(10);
                    break;
                default:
                    return "redirect:" + "/login?errorMsg=noAccess";
            }
        }
        if (lessons != null) {
            model.addAttribute("lessons", lessons);
        }
        return "dashboard";
    }
}
