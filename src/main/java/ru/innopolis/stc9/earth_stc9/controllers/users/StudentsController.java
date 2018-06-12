package ru.innopolis.stc9.earth_stc9.controllers.users;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.innopolis.stc9.earth_stc9.exceptions.UserException;
import ru.innopolis.stc9.earth_stc9.pojo.Role;
import ru.innopolis.stc9.earth_stc9.pojo.User;
import ru.innopolis.stc9.earth_stc9.services.IUsersService;

import java.util.List;

@Controller
public class StudentsController {
    private static Logger logger = Logger.getLogger(StudentsController.class);
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
        try {
            model.addAttribute("createRole", Roles.STUDENT_ROLE_ID);
            List<User> users = service.getUsers(Roles.STUDENT_ROLE_ID);
            if (users != null) {
                model.addAttribute("students", users);
            }
        } catch (Exception e) {
            logger.warn("Get students", e);
            showMessage(model, e.getMessage());
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
                             @RequestAttribute String passwordStudent, @RequestAttribute Integer roleStudent,
                             Model model) {
        try {
            if (nameStudent == null || nameStudent.isEmpty() || loginStudent == null ||
                    loginStudent.isEmpty() || passwordStudent == null || passwordStudent.isEmpty() ||
                    roleStudent == null) {
                throw new UserException("Ошибка создания студента, переданы нулевые параметры");
            }
            User user = new User(nameStudent, loginStudent, passwordStudent, roleStudent);
            service.createUser(user);
        } catch (Exception e) {
            logger.warn("Create student", e);
            showMessage(model, e.getMessage());
            addStudentShowBlock(model);
        }
        return getStudents(model);
    }

    @RequestMapping(value = "/studentEdit/{id}", method = RequestMethod.GET)
    public String editStudentShowBlock(@PathVariable(value = "id") int id, Model model) {
        try {
            if (id <= 0) {
                throw new UserException("Передан некорректный идентификатор студента");
            }
            User userById = service.getUserById(id);
            model.addAttribute("user", userById);
            model.addAttribute("update", "Изменить");
            List<Role> roles = service.getRoles();
            if (roles != null) {
                model.addAttribute("roles", roles);
            }
        } catch (Exception e) {
            logger.warn("Create student", e);
            showMessage(model, e.getMessage());
        }
        return getStudents(model);
    }

    @RequestMapping(value = "/studentEdit/{id}", method = RequestMethod.POST)
    public String editStudent(@PathVariable(value = "id") int id, @RequestAttribute String nameStudent,
                              @RequestAttribute String loginStudent, @RequestAttribute String passwordStudent,
                              @RequestAttribute Integer roleStudent, Model model) {
        try {
            if (id <= 0 || nameStudent == null || nameStudent.isEmpty() || loginStudent == null ||
                    loginStudent.isEmpty() || passwordStudent == null || passwordStudent.isEmpty() ||
                    roleStudent == null) {
                throw new UserException("Ошибка изменения студента, переданы нулевые параметры");
            }
            User user = new User(id, loginStudent, passwordStudent, roleStudent, nameStudent);
            service.updateUser(user);
        } catch (Exception e) {
            logger.warn("Edit student", e);
            showMessage(model, e.getMessage());
            editStudentShowBlock(id, model);
        }
        return getStudents(model);
    }

    @RequestMapping(value = "/studentDelete/{id}", method = RequestMethod.POST)
    public String deleteStudent(@PathVariable(value = "id") int id, Model model) {
        try {
            if (id <= 0) {
                throw new UserException("Передан некорректный идентификатор");
            }
            service.deleteUserById(id);
        } catch (Exception e) {
            logger.warn("Edit student", e);
            showMessage(model, e.getMessage());
        }
        return getStudents(model);
    }

    private void showMessage(Model model, String message) {
        model.addAttribute("message", message);
    }
}
