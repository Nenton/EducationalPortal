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

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class TeachersController {
    private final IUsersService service;

    @Autowired
    public TeachersController(IUsersService service) {
        this.service = service;
    }

    @RequestMapping(value = "/teachers", method = RequestMethod.GET)
    public String showTeachers(HttpSession session, Model model) {
        model.addAttribute("role", session.getAttribute("role"));
        return getTeachers(model);
    }

    private String getTeachers(Model model) {
        model.addAttribute("createRole", Roles.TEACHER_ROLE_ID);
        List<User> users = service.getUsers(Roles.TEACHER_ROLE_ID);
        if (users != null) {
            model.addAttribute("teachers", users);
        }
        return "teachers";
    }

    @RequestMapping(value = "/teacherCreate", method = RequestMethod.GET)
    public String addTeacherShowBlock(Model model) {
        model.addAttribute("create", "Создать");
        return getTeachers(model);
    }

    @RequestMapping(value = "/teacherCreate", method = RequestMethod.POST)
    public String addTeacher(@RequestAttribute String nameTeacher, @RequestAttribute String loginTeacher,
                             @RequestAttribute String passwordTeacher, @RequestAttribute String roleTeacher,
                             Model model) {
        User user = new User(nameTeacher, loginTeacher, passwordTeacher, Integer.parseInt(roleTeacher));
        service.createUser(user);
        return getTeachers(model);
    }

    @RequestMapping(value = "/teacherEdit/{id}", method = RequestMethod.GET)
    public String editTeacherShowBlock(@PathVariable(value = "id") String id, Model model) {
        User userById = service.getUserById(Integer.parseInt(id));
        model.addAttribute("user", userById);
        model.addAttribute("update", "Изменить");
        List<Role> roles = service.getRoles();
        if (roles != null) {
            model.addAttribute("roles", roles);
        }
        return getTeachers(model);
    }

    @RequestMapping(value = "/teacherEdit/{id}", method = RequestMethod.POST)
    public String editTeacher(@PathVariable(value = "id") String id, @RequestAttribute String nameTeacher,
                              @RequestAttribute String loginTeacher, @RequestAttribute String passwordTeacher,
                              @RequestAttribute String roleTeacher, Model model) {
        User user = new User(Integer.parseInt(id), loginTeacher, passwordTeacher, Integer.parseInt(roleTeacher), nameTeacher);
        service.updateUser(user);
        return getTeachers(model);
    }

    @RequestMapping(value = "/teacherDelete/{id}", method = RequestMethod.POST)
    public String deleteTeacher(@PathVariable(value = "id") String id, Model model) {
        int idUser = Integer.parseInt(id);
        service.deleteUserById(idUser);
        return getTeachers(model);
    }
}
