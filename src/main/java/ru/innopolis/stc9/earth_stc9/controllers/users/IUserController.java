package ru.innopolis.stc9.earth_stc9.controllers.users;

public interface IUserController {
    /**
     * Get current role id
     */
    int getRoleCreate();

    /**
     * Get path page for current role
     */
    String getPathPage();
}
