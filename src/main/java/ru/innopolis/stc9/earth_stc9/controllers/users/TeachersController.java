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
public class TeachersController {
    private static Logger logger = Logger.getLogger(StudentsController.class);
    private final IUsersService service;

    @Autowired
    public TeachersController(IUsersService service) {
        this.service = service;
    }

    @RequestMapping(value = "/teachers", method = RequestMethod.GET)
    public String showTeachers(Model model) {
        return getTeachers(model);
    }

    private String getTeachers(Model model) {
        try {
            model.addAttribute("createRole", Roles.TEACHER_ROLE_ID);
            List<User> users = service.getUsers(Roles.TEACHER_ROLE_ID);
            if (users != null) {
                model.addAttribute("teachers", users);
            }
        } catch (Exception e) {
            logger.warn("Get teachers", e);
            showMessage(model, e.getMessage());
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
                             @RequestAttribute String passwordTeacher, @RequestAttribute Integer roleTeacher,
                             Model model) {
        try {
            if (nameTeacher == null || nameTeacher.isEmpty() || loginTeacher == null || loginTeacher.isEmpty() ||
                    passwordTeacher == null || passwordTeacher.isEmpty() || roleTeacher == null) {
                throw new UserException("Ошибка создания преподавателя, переданы нулевые параметры");
            }
            User user = new User(nameTeacher, loginTeacher, passwordTeacher, roleTeacher);
            service.createUser(user);
        } catch (Exception e) {
            logger.warn("Create teacher", e);
            showMessage(model, e.getMessage());
            addTeacherShowBlock(model);
        }
        return getTeachers(model);
    }

    @RequestMapping(value = "/teacherEdit/{id}", method = RequestMethod.GET)
    public String editTeacherShowBlock(@PathVariable(value = "id") int id, Model model) {
        try {
            if (id <= 0) {
                throw new UserException("Передан некорректный идентификатор преподавателя");
            }
            User userById = service.getUserById(id);
            model.addAttribute("user", userById);
            model.addAttribute("update", "Изменить");
            List<Role> roles = service.getRoles();
            if (roles != null) {
                model.addAttribute("roles", roles);
            }
        } catch (Exception e) {
            logger.warn("Edit teacher", e);
            showMessage(model, e.getMessage());
        }
        return getTeachers(model);
    }

    @RequestMapping(value = "/teacherEdit/{id}", method = RequestMethod.POST)
    public String editTeacher(@PathVariable(value = "id") int id, @RequestAttribute String nameTeacher,
                              @RequestAttribute String loginTeacher, @RequestAttribute String passwordTeacher,
                              @RequestAttribute Integer roleTeacher, Model model) {
        try {
            if (id <= 0 || nameTeacher == null || nameTeacher.isEmpty() || loginTeacher == null ||
                    loginTeacher.isEmpty() || passwordTeacher == null || passwordTeacher.isEmpty() ||
                    roleTeacher == null) {
                throw new UserException("Ошибка изменения преподавателя, переданы нулевые параметры");
            }
            User user = new User(id, loginTeacher, passwordTeacher, roleTeacher, nameTeacher);
            service.updateUser(user);
        } catch (Exception e) {
            logger.warn("Edit teacher", e);
            showMessage(model, e.getMessage());
            editTeacherShowBlock(id, model);
        }
        return getTeachers(model);
    }

    @RequestMapping(value = "/teacherDelete/{id}", method = RequestMethod.POST)
    public String deleteTeacher(@PathVariable(value = "id") int id, Model model) {
        try {
            if (id <= 0) {
                throw new UserException("Передан некорректный идентификатор");
            }
            service.deleteUserById(id);
        } catch (Exception e) {
            logger.warn("Delete teacher", e);
            showMessage(model, e.getMessage());
        }
        return getTeachers(model);
    }

    private void showMessage(Model model, String message) {
        model.addAttribute("message", message);
    }
}
