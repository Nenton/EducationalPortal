package ru.innopolis.stc9.earth_stc9.services;

import com.sun.istack.internal.Nullable;
import org.apache.log4j.Logger;
import ru.innopolis.stc9.earth_stc9.pojo.Role;

import java.util.List;


public interface IRoleService {
    Logger logger = Logger.getLogger(IRoleService.class);

    /**
     * Get all roles entity
     */
    @Nullable
    List<Role> getRoles();

    /**
     * Create role entity
     */
    @Nullable
    boolean createRole(Role role);

    /**
     * Delete role entity
     */
    @Nullable
    boolean deleteRole(int id);
}
