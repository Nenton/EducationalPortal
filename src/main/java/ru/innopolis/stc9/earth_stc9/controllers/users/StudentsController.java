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
public class StudentsController {
    @Autowired
    private IUsersService service;

    @RequestMapping(value = "/students", method = RequestMethod.GET)
    public String getUsers(HttpServletRequest request, Model model) {
        List<User> students = service.getUsers(Roles.STUDENT_ROLE_ID);

        if (students != null) {
            model.addAttribute("students", students);
        }
        model.addAttribute("createRole", Roles.STUDENT_ROLE_ID);
        model.addAttribute("role", request.getSession().getAttribute("role"));
        return "students";
    }

    @RequestMapping(value = "/students", method = RequestMethod.POST)
    public String addUserOrDelete(HttpServletRequest request, @RequestAttribute String delete, @RequestAttribute String userId,
                                  @RequestAttribute String createStudent, @RequestAttribute String nameStudent,
                                  @RequestAttribute String loginStudent, @RequestAttribute String passwordStudent,
                                  @RequestAttribute String roleStudent,
                                  Model model) {
        if (createStudent != null) {
            User user = new User(nameStudent, loginStudent, passwordStudent, Integer.parseInt(roleStudent));
            service.createUser(user);
        } else if (delete != null) {
            int idUser = Integer.parseInt(userId);
            service.deleteUserById(idUser);
        }
        return getUsers(request, model);
    }
}
