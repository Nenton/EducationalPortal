package ru.innopolis.stc9.earth_stc9.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * Controller for login operations
 */
@Controller
public class LoginController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getPage(Model model,
                          @RequestParam(name = "errorMsg", required = false) String errorMsg,
                          @RequestParam(value = "logout", required = false) String logout) {
        if (errorMsg != null && "authFail".equals(errorMsg)) {
            model.addAttribute("message", "Ошибка авторизации");
        }
        if (logout != null) {
            model.addAttribute("message", "Вы успешно вышли из системы");
        }
        return "login";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout() {
        return "login";
    }
}