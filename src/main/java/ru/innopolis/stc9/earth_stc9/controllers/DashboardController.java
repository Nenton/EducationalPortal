package ru.innopolis.stc9.earth_stc9.controllers;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.innopolis.stc9.earth_stc9.controllers.users.Roles;
import ru.innopolis.stc9.earth_stc9.services.ILessonService;

/**
 * Controller for all need compressed information for user
 */
@Controller
public class DashboardController {
    private static final Logger logger = Logger.getLogger(DashboardController.class);
    @Autowired
    private ILessonService service;

    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public String doGet(Authentication authentication, Model model) {
        logger.info("doGet" + this.getClass().getName());

        authentication.getAuthorities().stream().forEach(r -> {
            switch (r.getAuthority()) {
                case "ROLE_STUDENT":
                    model.addAttribute("lessons", service.getLessonsByStudentId(Roles.STUDENT_ROLE_ID, 10));
                    break;
                case "ROLE_TEACHER":
                    model.addAttribute("lessons", service.getLessonsByTeacherId(Roles.TEACHER_ROLE_ID, 10));
                    break;
                case "ROLE_ADMIN":
                    model.addAttribute("lessons", service.getLessonsLast(10));
                    break;
                default:
                    break;
            }
        });
        return "dashboard";
    }
}