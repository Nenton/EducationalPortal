package ru.innopolis.stc9.earth_stc9.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.innopolis.stc9.earth_stc9.controllers.users.Roles;
import ru.innopolis.stc9.earth_stc9.pojo.User;
import ru.innopolis.stc9.earth_stc9.services.UsersService;

@Controller
public class RegistrationController {
    @Autowired
    private UsersService usersService;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String goRegistration() {
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(Model model, @RequestParam String fullName, @RequestParam String login, @RequestParam String password, @RequestParam String confirmpassword) {
        User user = new User(fullName, login, password, Roles.STUDENT_ROLE_ID);

        if (!password.equals(confirmpassword)) {
            model.addAttribute("message", "Пароль и подтверждение не совпадают!");
        }

        if (usersService.getUserByLogin(login) != null) {
            model.addAttribute("message", "Пользователь с логином " + login + " уже зарегистрирован!");
        }

        if (usersService.createUser(user)) {
            model.addAttribute("message", "Пользователь успешно зарегистрирован! Ведите логин и пароль!");
        }

        return "registration";
    }
}
