package ru.innopolis.stc9.earth_stc9.controllers.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.innopolis.stc9.earth_stc9.pojo.User;
import ru.innopolis.stc9.earth_stc9.services.IUsersService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class TeachersController {
    @Autowired
    private IUsersService service;

    @RequestMapping(value = "/teachers", method = RequestMethod.GET)
    public String getUsers(HttpServletRequest request, Model model) {
        List<User> teachers = service.getUsers(Roles.TEACHER_ROLE_ID);

        if (teachers != null) {
            model.addAttribute("teachers", teachers);
        }
        model.addAttribute("createRole", Roles.TEACHER_ROLE_ID);
        model.addAttribute("role", request.getSession().getAttribute("role"));
        return "teachers";
    }

    @RequestMapping(value = "/teachers", method = RequestMethod.POST)
    public String addUserOrDelete(HttpServletRequest request, @RequestAttribute String delete, @RequestAttribute String userId,
                                  @RequestAttribute String addTeacher, @RequestAttribute String nameTeacher,
                                  @RequestAttribute String loginTeacher, @RequestAttribute String passwordTeacher,
                                  @RequestAttribute String roleTeacher,
                                  Model model) {
        if (addTeacher != null) {
            User user = new User(nameTeacher, loginTeacher, passwordTeacher, Integer.parseInt(roleTeacher));
            service.createUser(user);
        } else if (delete != null) {
            int idUser = Integer.parseInt(userId);
            service.deleteUserById(idUser);
        }
        return getUsers(request, model);
    }
}
