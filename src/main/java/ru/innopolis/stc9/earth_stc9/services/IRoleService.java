package ru.innopolis.stc9.earth_stc9.services;

import ru.innopolis.stc9.earth_stc9.pojo.Role;

import java.util.List;


public interface IRoleService {

    /**
     * Get all roles entity
     */
    List<Role> getRoles();

    /**
     * Create role entity
     */
    boolean createRole(Role role);

    /**
     * Delete role entity
     */
    boolean deleteRole(int id);
}
