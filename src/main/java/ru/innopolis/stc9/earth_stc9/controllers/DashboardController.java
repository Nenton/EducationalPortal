package ru.innopolis.stc9.earth_stc9.controllers;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.innopolis.stc9.earth_stc9.controllers.users.Roles;
import ru.innopolis.stc9.earth_stc9.services.ILessonService;
import javax.servlet.http.HttpSession;

/**
 * Controller for all need compressed information for user
 */
@Controller
public class DashboardController {
    private static final Logger logger = Logger.getLogger(DashboardController.class);
    @Autowired
    private ILessonService service;

    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public String doGet(HttpSession httpSession, Model model) {
        logger.info("doGet" + this.getClass().getName());

        if (httpSession.getAttribute("role") != null) {
            switch (Integer.parseInt(httpSession.getAttribute("role").toString())) {
                case Roles.STUDENT_ROLE_ID:
                    model.addAttribute("lessons", service.getLessonsByStudentId(Roles.STUDENT_ROLE_ID, 10));
                    return "dashboard";
                case Roles.TEACHER_ROLE_ID:
                    model.addAttribute("lessons", service.getLessonsByTeacherId(Roles.STUDENT_ROLE_ID, 10));
                    return "dashboard";
                case Roles.ADMIN_ROLE_ID:
                    model.addAttribute("lessons", service.getLessonsLast(10));
                    return "dashboard";
                default:
                    model.addAttribute("errorMsg", "noAccess");
                    return "login";
            }
        } else {
            model.addAttribute("errorMsg", "noAccess");
            return "login";
        }
    }
}
