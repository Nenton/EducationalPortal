package ru.innopolis.stc9.earth_stc9.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.innopolis.stc9.earth_stc9.pojo.User;
import ru.innopolis.stc9.earth_stc9.services.RoleService;
import ru.innopolis.stc9.earth_stc9.services.UsersService;

@Controller
public class RegistrationController {
    @Autowired
    private RoleService roleService;
    @Autowired
    private UsersService usersService;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String getRolesList(Model model) {
        model.addAttribute("roles", roleService.getRoles());
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(Model model, @RequestParam String fullName, @RequestParam String login, @RequestParam String password, @RequestParam String confirmpassword, @RequestParam String uroles) {
        User user = new User(fullName, login, password, Integer.parseInt(uroles));

        if (!password.equals(confirmpassword)) {
            model.addAttribute("message", "Пароль и подтверждение не совпадают!");
            model.addAttribute("roles", roleService.getRoles());
            return "registration";
        }

        if (usersService.getUserByLogin(login) != null) {
            model.addAttribute("message", "Пользователь с логином " + login + " уже зарегистрирован!");
            model.addAttribute("roles", roleService.getRoles());
            return "registration";
        }

        if (usersService.createUser(user)) {
            model.addAttribute("message", "Пользователь успешно зарегистрирован! Ведите логин и пароль!");
            return "login";
        }

        return "login";
    }
}
