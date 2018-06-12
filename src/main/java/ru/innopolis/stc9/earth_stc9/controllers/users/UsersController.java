package ru.innopolis.stc9.earth_stc9.controllers.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.innopolis.stc9.earth_stc9.pojo.Role;
import ru.innopolis.stc9.earth_stc9.pojo.User;
import ru.innopolis.stc9.earth_stc9.services.IUsersService;

import java.util.List;

@Controller
public class UsersController {
    private final IUsersService service;

    @Autowired
    public UsersController(IUsersService service) {
        this.service = service;
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String showUsers(Model model) {
        return getUsers(model);
    }

    private String getUsers(Model model) {
        List<User> users = service.getUsers();
        if (users != null) {
            model.addAttribute("users", users);
        }
        return "users";
    }

    @RequestMapping(value = "/userCreate", method = RequestMethod.GET)
    public String addUserShowBlock(Model model) {
        model.addAttribute("create", "Создать");
        List<Role> roles = service.getRoles();
        if (roles != null) {
            model.addAttribute("roles", roles);
        }
        return getUsers(model);
    }

    @RequestMapping(value = "/userCreate", method = RequestMethod.POST)
    public String addUser(@RequestAttribute String nameUser, @RequestAttribute String loginUser,
                          @RequestAttribute String passwordUser, @RequestAttribute String role,
                          Model model) {
        User user = new User(nameUser, loginUser, passwordUser, Integer.parseInt(role));
        service.createUser(user);
        return getUsers(model);
    }

    @RequestMapping(value = "/userEdit/{id}", method = RequestMethod.GET)
    public String editUserShowBlock(@PathVariable(value = "id") int id, Model model) {
        User userById = service.getUserById(id);
        model.addAttribute("user", userById);
        model.addAttribute("update", "Изменить");
        List<Role> roles = service.getRoles();
        if (roles != null) {
            model.addAttribute("roles", roles);
        }
        return getUsers(model);
    }

    @RequestMapping(value = "/userEdit/{id}", method = RequestMethod.POST)
    public String editUser(@PathVariable(value = "id") int id, @RequestAttribute String nameUser,
                           @RequestAttribute String loginUser, @RequestAttribute String passwordUser,
                           @RequestAttribute String role, Model model) {
        User user = new User(id, loginUser, passwordUser, Integer.parseInt(role), nameUser);
        service.updateUser(user);
        return getUsers(model);
    }

    @RequestMapping(value = "/userDelete/{id}", method = RequestMethod.POST)
    public String deleteUser(@PathVariable(value = "id") int id, Model model) {
        service.deleteUserById(id);
        return getUsers(model);
    }
}
