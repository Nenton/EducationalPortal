package ru.innopolis.stc9.earth_stc9.controllers.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.innopolis.stc9.earth_stc9.pojo.Role;
import ru.innopolis.stc9.earth_stc9.pojo.User;
import ru.innopolis.stc9.earth_stc9.services.IUsersService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class UsersController {
    @Autowired
    private IUsersService service;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String getUsers(HttpServletRequest request, Model model) {
        List<User> users = service.getUsers();
        if (users != null) {
            model.addAttribute("usersList", users);
        }
        if (request.getParameter("create") != null) {
            model.addAttribute("create", "Создать");
            List<Role> roles = service.getRoles();
            if (roles != null) {
                model.addAttribute("roles", roles);
            }
        }
        if (request.getParameter("update") != null) {
            model.addAttribute("user", service.getUserById(Integer.parseInt(request.getParameter("userId"))));
            model.addAttribute("update", "Изменить");
            List<Role> roles = service.getRoles();
            if (roles != null) {
                model.addAttribute("roles", roles);
            }
        }
        model.addAttribute("role", request.getSession().getAttribute("role"));
        return "users";
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public String addUserOrDelete(HttpServletRequest request, @RequestAttribute String delete,
                                  @RequestAttribute String editUser, @RequestAttribute String userId,
                                  @RequestAttribute String createUser, @RequestAttribute String nameUser,
                                  @RequestAttribute String loginUser, @RequestAttribute String passwordUser,
                                  @RequestAttribute String role, Model model) {

        if (createUser != null) {
            User user = new User(nameUser, loginUser, passwordUser, Integer.parseInt(role));
            service.createUser(user);
        } else if (editUser != null) {
              User user = new User(Integer.parseInt(userId), loginUser, passwordUser, Integer.parseInt(role), nameUser);
              service.updateUser(user);
        } else if (delete != null) {
            int idUser = Integer.parseInt(userId);
            service.deleteUserById(idUser);
        }
        return getUsers(request, model);
    }
}
