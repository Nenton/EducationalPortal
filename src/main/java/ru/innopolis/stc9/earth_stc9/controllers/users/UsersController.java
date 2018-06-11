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
public class UsersController {
    private static Logger logger = Logger.getLogger(UsersController.class);
    private final IUsersService service;

    @Autowired
    public UsersController(IUsersService service) {
        this.service = service;
    }

    /**
     * Метод отображает начальную страницу с пользователями
     * В виде таблицы с параметрами пользователей
     *
     * @param model - модель данных на выходе
     * @return - имя jsp файла
     */
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String showUsers(Model model) {
        return getUsers(model);
    }

    /**
     * Дефолтный метод получения всех пользователей и внесение их в модель
     *
     * @param model - модель данных на выходе
     * @return - имя jsp файла
     */
    private String getUsers(Model model) {
        try {
            List<User> users = service.getUsers();
            if (users != null) {
                model.addAttribute("users", users);
            }
        } catch (Exception e) {
            logger.warn("Get users", e);
            showMessage(model, e.getMessage());
        }
        return "users";
    }

    /**
     * Метод отображает блок добавления пользователя
     *
     * @param model - модель данных на выходе
     * @return - имя jsp файла
     */
    @RequestMapping(value = "/userCreate", method = RequestMethod.GET)
    public String addUserShowBlock(Model model) {
        try {
            model.addAttribute("create", "Создать");
            List<Role> roles = service.getRoles();
            if (roles != null) {
                model.addAttribute("roles", roles);
            }

        } catch (Exception e) {
            logger.warn("Create user", e);
            showMessage(model, e.getMessage());
        }
        return getUsers(model);
    }

    /**
     * Метод отправляет запрос в сервис на добавление пользователя
     *
     * @param nameUser     - имя пользователя
     * @param loginUser    - логин пользователя
     * @param passwordUser - пароль пользователя
     * @param role         - роль пользователя
     * @param model        - модель данных на выходе
     * @return - имя jsp файла
     */
    @RequestMapping(value = "/userCreate", method = RequestMethod.POST)
    public String addUser(@RequestAttribute String nameUser, @RequestAttribute String loginUser,
                          @RequestAttribute String passwordUser, @RequestAttribute Integer role,
                          Model model) {
        try {
            if (nameUser == null || nameUser.isEmpty() || loginUser == null || loginUser.isEmpty() ||
                    passwordUser == null || passwordUser.isEmpty() || role == null) {
                throw new UserException("Ошибка создания пользователя, переданы нулевые параметры пользователя");
            }
            User user = new User(nameUser, loginUser, passwordUser, role);
            service.createUser(user);
        } catch (Exception e) {
            logger.warn("Create user", e);
            showMessage(model, e.getMessage());
            return addUserShowBlock(model);
        }
        return getUsers(model);
    }

    /**
     * Метод отображает блок изменения данных пользователя
     *
     * @param id    - идентификатор пользователя
     * @param model - модель данных на выходе
     * @return - имя jsp файла
     */
    @RequestMapping(value = "/userEdit/{id}", method = RequestMethod.GET)
    public String editUserShowBlock(@PathVariable(value = "id") int id, Model model) {
        try {
            if (id <= 0) {
                throw new UserException("Передан некорректный идентификатор");
            }
            User userById = service.getUserById(id);
            model.addAttribute("user", userById);
            model.addAttribute("update", "Изменить");
            List<Role> roles = service.getRoles();
            if (roles != null) {
                model.addAttribute("roles", roles);
            }
        } catch (Exception e) {
            logger.warn("Edit user", e);
            showMessage(model, e.getMessage());
        }
        return getUsers(model);
    }

    /**
     * Метод отправляет запрос в сервис на изменение данных пользователя
     *
     * @param id           - идентификатор пользователя
     * @param nameUser     - имя пользователя
     * @param loginUser    - логин пользователя
     * @param passwordUser - пароль пользователя
     * @param role         - роль пользователя
     * @param model        - модель данных на выходе
     * @return - имя jsp файла
     */
    @RequestMapping(value = "/userEdit/{id}", method = RequestMethod.POST)
    public String editUser(@PathVariable(value = "id") int id, @RequestAttribute String nameUser,
                           @RequestAttribute String loginUser, @RequestAttribute String passwordUser,
                           @RequestAttribute Integer role, Model model) {
        try {
            if (id <= 0 || nameUser == null || nameUser.isEmpty() || loginUser == null || loginUser.isEmpty() ||
                    passwordUser == null || passwordUser.isEmpty() || role == null) {
                throw new UserException("Ошибка изменения пользователя, переданы нулевые параметры пользователя");
            }
            User user = new User(id, loginUser, passwordUser, role, nameUser);
            service.updateUser(user);
        } catch (Exception e) {
            logger.warn("Edit user", e);
            showMessage(model, e.getMessage());
            editUserShowBlock(id, model);
        }
        return getUsers(model);
    }

    /**
     * Метод отправляет запрос в сервис на удаление пользователя
     *
     * @param id    - идентификатор пользователя
     * @param model - модель данных на выходе
     * @return - имя jsp файла
     */
    @RequestMapping(value = "/userDelete/{id}", method = RequestMethod.POST)
    public String deleteUser(@PathVariable(value = "id") int id, Model model) {
        try {
            if (id <= 0) {
                throw new UserException("Ошибка удаления пользователя, передан некорректный идентификатор");
            }
            service.deleteUserById(id);
        } catch (Exception e) {
            logger.warn("Delete user", e);
            showMessage(model, e.getMessage());
        }
        return getUsers(model);
    }

    /**
     * Метод добавляет сообщение в модель
     *
     * @param model   - модель данных на выходе
     * @param message - текст сообщения
     */
    private void showMessage(Model model, String message) {
        model.addAttribute("message", message);
    }
}
