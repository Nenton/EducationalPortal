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
public class StudentsController {
    private final IUsersService service;

    @Autowired
    public StudentsController(IUsersService service) {
        this.service = service;
    }

    @RequestMapping(value = "/students", method = RequestMethod.GET)
    public String showStudents(Model model) {
        return getStudents(model);
    }

    private String getStudents(Model model) {
        model.addAttribute("createRole", Roles.STUDENT_ROLE_ID);
        List<User> users = service.getUsers(Roles.STUDENT_ROLE_ID);
        if (users != null) {
            model.addAttribute("students", users);
        }
        return "students";
    }

    @RequestMapping(value = "/studentCreate", method = RequestMethod.GET)
    public String addStudentShowBlock(Model model) {
        model.addAttribute("create", "Создать");
        return getStudents(model);
    }

    @RequestMapping(value = "/studentCreate", method = RequestMethod.POST)
    public String addStudent(@RequestAttribute String nameStudent, @RequestAttribute String loginStudent,
                             @RequestAttribute String passwordStudent, @RequestAttribute String roleStudent,
                             Model model) {
        User user = new User(nameStudent, loginStudent, passwordStudent, Integer.parseInt(roleStudent));
        service.createUser(user);
        return getStudents(model);
    }

    @RequestMapping(value = "/studentEdit/{id}", method = RequestMethod.GET)
    public String editStudentShowBlock(@PathVariable(value = "id") int id, Model model) {
        User userById = service.getUserById(id);
        model.addAttribute("user", userById);
        model.addAttribute("update", "Изменить");
        List<Role> roles = service.getRoles();
        if (roles != null) {
            model.addAttribute("roles", roles);
        }
        return getStudents(model);
    }

    @RequestMapping(value = "/studentEdit/{id}", method = RequestMethod.POST)
    public String editStudent(@PathVariable(value = "id") int id, @RequestAttribute String nameStudent,
                              @RequestAttribute String loginStudent, @RequestAttribute String passwordStudent,
                              @RequestAttribute String roleStudent, Model model) {
        User user = new User(id, loginStudent, passwordStudent, Integer.parseInt(roleStudent), nameStudent);
        service.updateUser(user);
        return getStudents(model);
    }

    @RequestMapping(value = "/studentDelete/{id}", method = RequestMethod.POST)
    public String deleteStudent(@PathVariable(value = "id") int id, Model model) {
        service.deleteUserById(id);
        return getStudents(model);
    }
}
