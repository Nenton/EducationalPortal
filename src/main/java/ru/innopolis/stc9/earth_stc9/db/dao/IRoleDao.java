package ru.innopolis.stc9.earth_stc9.db.dao;
import ru.innopolis.stc9.earth_stc9.pojo.Role;

import java.sql.SQLException;
import java.util.List;

public interface IRoleDao {
    String COLUMN_ID = "id";
    String COLUMN_ROLE = "name";

    /**
     * Insert role into DB
     */
    boolean addRole(Role role) throws SQLException;

    /**
     * Delete role from DB
     */
    boolean deleteRole(Role role) throws SQLException;

    /**
     * Delete role from DB by role id
     */
    boolean deleteRole(int id) throws SQLException;

    /**
     * Get role from DB by role id
     */
    Role getRoleById(int id) throws SQLException;

    /**
     * Update role from DB
     */
    boolean updateRole(Role role) throws SQLException;

    /**
     * Get all roles from DB
     */
    List<Role> getRoles() throws SQLException;

    /**
     * Get role from DB by user login
     */
    Role getRoleByLogin(String login) throws SQLException;
}
