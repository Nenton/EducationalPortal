package ru.innopolis.stc9.earth_stc9.services;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.innopolis.stc9.earth_stc9.db.dao.IRoleDao;
import ru.innopolis.stc9.earth_stc9.db.dao.IUserDao;
import ru.innopolis.stc9.earth_stc9.pojo.Role;
import ru.innopolis.stc9.earth_stc9.pojo.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class UsersService implements IUsersService {
    private Logger logger = Logger.getLogger(UsersService.class);
    @Autowired
    private IUserDao userDao;
    @Autowired
    private IRoleDao roleDao;

    @Override
    public User getUserById(int id) {
        try {
            return userDao.getUserById(id);
        } catch (SQLException e) {
            logger.warn("Ошибка получения пользователя по id - " + id, e);
            return null;
        }
    }

    @Override
    public User getUserByLogin(String login) {
        try {
            return userDao.getUserByLogin(login);
        } catch (SQLException e) {
            logger.warn("Ошибка получения пользователя по login - " + login, e);
            return null;
        }
    }

    @Override
    public boolean deleteUserById(int idUser) {
        try {
            return userDao.deleteUserById(idUser);
        } catch (SQLException e) {
            logger.warn("Ошибка удаления пользователя по id - " + idUser, e);
            return false;
        }
    }

    @Override
    public boolean createUser(User user) {
        try {
            return userDao.addUser(user);
        } catch (SQLException e) {
            logger.warn("Ошибка создания пользователя", e);
            return false;
        }
    }

    @Override
    public List<User> getUsers() {
        try {
            return userDao.getUsers();
        } catch (SQLException e) {
            logger.warn("Ошибка получения пользователей", e);
            return new ArrayList<>();
        }
    }

    @Override
    public List<User> getUsers(int roleId) {
        try {
            return userDao.getUsers(roleId);
        } catch (SQLException e) {
            logger.warn("Ошибка получения пользователей по id role - " + roleId, e);
            return new ArrayList<>();
        }
    }

    @Override
    public List<Role> getRoles() {
        try {
            return roleDao.getRoles();
        } catch (SQLException e) {
            logger.warn("Ошибка получения ролей", e);
            return new ArrayList<>();
        }
    }

    @Override
    public boolean updateUser(User user) {
        try {
            return userDao.updateUser(user);
        } catch (SQLException e) {
            logger.warn("Ошибка редактирования пользователя", e);
            return false;
        }
    }
}
