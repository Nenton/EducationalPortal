package ru.innopolis.stc9.earth_stc9.controllers.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.innopolis.stc9.earth_stc9.pojo.Role;
import ru.innopolis.stc9.earth_stc9.pojo.User;
import ru.innopolis.stc9.earth_stc9.services.IUsersService;

import java.util.List;

@Controller
public class UsersController {
    @Autowired
    private IUsersService service;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String getStudents(Model model) {
        List<User> users = service.getUsers();
        List<Role> roles = service.getRoles();
        if (users != null) {
            model.addAttribute("users", users);
        }
        if (roles != null) {
            model.addAttribute("roles", roles);
        }
        model.addAttribute("path", "/users");
        return "users";
    }
}
