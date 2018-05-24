package ru.innopolis.stc9.earth_stc9.controllers.users;

import ru.innopolis.stc9.earth_stc9.controllers.AbstractController;
import ru.innopolis.stc9.earth_stc9.pojo.Role;
import ru.innopolis.stc9.earth_stc9.pojo.User;
import ru.innopolis.stc9.earth_stc9.services.IUsersService;
import ru.innopolis.stc9.earth_stc9.services.UsersService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Class for pages different roles
 */
public abstract class UsersAbstractController extends AbstractController implements IUserController {
    private IUsersService service = new UsersService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("doGet" + this.getClass().getName());
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        List<User> users = null;

        int roleId = getRoleCreate();
        if (roleId == 0) {
            users = service.getUsers();
        } else {
            users = service.getUsers(roleId);
            req.setAttribute("createRole", getRoleCreate());
        }
        if (users != null) {
            req.setAttribute("users", users);
        }
        List<Role> roles = service.getRoles();
        req.setAttribute("roles", roles);
        req.setAttribute("path", getPathPage());
        req.getRequestDispatcher("/pages/users.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        logger.info("doPost" + this.getClass().getName());
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        if (req.getParameter("deleteBtn") != null) {
            int idUser = Integer.parseInt(req.getParameter("userId"));
            service.deleteUserById(idUser);
            resp.sendRedirect(getPathPage());
        }

        if (req.getParameter("createUser") != null) {
            String name = req.getParameter("nameUser");
            String login = req.getParameter("loginUser");
            String password = req.getParameter("passwordUser");
            int roleId;
            if (req.getParameter("roleIdUser") != null) {
                roleId = Integer.parseInt(req.getParameter("roleIdUser"));
            } else {
                roleId = Integer.parseInt(req.getParameter("role"));
            }
            User user = new User(name, login, password, roleId);
            service.createUser(user);
            resp.sendRedirect(getPathPage());
        }
    }
}