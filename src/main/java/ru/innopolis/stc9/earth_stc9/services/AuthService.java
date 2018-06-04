package ru.innopolis.stc9.earth_stc9.services;

import ru.innopolis.stc9.earth_stc9.db.dao.IRoleDao;
import ru.innopolis.stc9.earth_stc9.db.dao.IUserDao;
import ru.innopolis.stc9.earth_stc9.db.dao.RoleDao;
import ru.innopolis.stc9.earth_stc9.db.dao.UserDao;
import ru.innopolis.stc9.earth_stc9.pojo.Role;
import ru.innopolis.stc9.earth_stc9.pojo.User;

import java.sql.SQLException;

public class AuthService implements IAuthService {
    private IUserDao userDao = new UserDao();
    private IRoleDao roleDao = new RoleDao();

    @Override
    public boolean checkAuth(String login, String password) {
        if (login == null || password == null || login.isEmpty() || password.isEmpty()) {
            return false;
        }
        User user = null;
        try {
            user = userDao.getUserByLogin(login);
        } catch (SQLException e) {
            logger.warn("Ошибка проверки авторизации", e);
        }
        return user != null && user.getPasswordHash().equals(password);
    }

    @Override
    public Role getRoleByUserLogin(String login) {
        if (login == null || login.isEmpty()) {
            return null;
        }
        Role role = null;
        try {
            role = roleDao.getRoleByLogin(login);
        } catch (SQLException e) {
            logger.warn("Ошибка проверки авторизации", e);
        }
        return role;
    }
}
