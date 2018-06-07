package ru.innopolis.stc9.earth_stc9.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.innopolis.stc9.earth_stc9.controllers.users.Roles;
import ru.innopolis.stc9.earth_stc9.pojo.Role;
import ru.innopolis.stc9.earth_stc9.services.AuthService;
import ru.innopolis.stc9.earth_stc9.services.IAuthService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Controller for login operations
 */
@Controller
public class LoginController extends AbstractController {
    @Autowired
    private IAuthService authService = new AuthService();

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String doGet(HttpSession httpSession, Model model, @RequestAttribute(name = "exit") String exit, @RequestAttribute(name = "login") String login, @RequestAttribute(name = "password") String password) {
        if (exit != null) {
            httpSession.invalidate();
            return "index";
        } else {
            if (authService.checkAuth(login, password)) {
                model.addAttribute("login", login);
                httpSession.setAttribute("login", login);
                Role role = authService.getRoleByUserLogin(login);
                switch (role.getId()) {
                    case Roles.ADMIN_ROLE_ID:
                        model.addAttribute("role", Integer.toString(Roles.ADMIN_ROLE_ID));
                        httpSession.setAttribute("role", Roles.ADMIN_ROLE_ID);
                        return "dashboard";
                    case Roles.STUDENT_ROLE_ID:
                        model.addAttribute("role", Integer.toString(Roles.STUDENT_ROLE_ID));
                        httpSession.setAttribute("role", Roles.STUDENT_ROLE_ID);
                        return "dashboard";
                    case Roles.TEACHER_ROLE_ID:
                        model.addAttribute("role", Integer.toString(Roles.TEACHER_ROLE_ID));
                        httpSession.setAttribute("role", Roles.TEACHER_ROLE_ID);
                        return "dashboard";
                    default:
                        model.addAttribute("errorMsg", "roleNull");
                        return "login";
                }
            } else {
                model.addAttribute("errorMsg", "authErr");
                return "login";
            }
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getPage(Model model, @RequestAttribute (name = "errorMsg") String errorMsg) {
        if (errorMsg != null) {
            switch (errorMsg) {
                case "noAccess":
                    model.addAttribute("message", "У Вас нет доступа к этой странице.");
                case "authErr":
                    model.addAttribute("message", "Попробуйте ввести данные снова");
                case "roleNull":
                    model.addAttribute("message", "Недостаточно прав доступа");
            }
        }
        return "login";
    }
}