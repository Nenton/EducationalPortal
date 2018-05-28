package ru.innopolis.stc9.earth_stc9.services;

import ru.innopolis.stc9.earth_stc9.pojo.Role;
import ru.innopolis.stc9.earth_stc9.pojo.User;

import java.util.List;

public interface IUsersService {

    /**
     * Get user entity by user id
     */
    User getUserById(int id);

    /**
     * Get user entity by user login
     */
    User getUserByLogin(String login);

    /**
     * Delete user entity by user id
     */
    boolean deleteUserById(int idUser);

    /**
     * Create user entity
     */
    boolean createUser(User user);

    /**
     * Get all user
     */
    List<User> getUsers();

    /**
     * Get users with choose role id
     */
    List<User> getUsers(int roleId);

    /**
     * Get all roles
     */
    List<Role> getRoles();

    /**
     * Update user
     */
    boolean updateUser(User user);
}
