package ru.innopolis.stc9.earth_stc9.db.dao;

import ru.innopolis.stc9.earth_stc9.pojo.Group;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Aleksandr Tikhonov - created 29.05.2018
 */

public interface IGroupDao {
    String COLUMN_GROUP_ID = "id";
    String COLUMN_GROUP_NAME = "name";
    String COLUMN_GROUP_DESCRIPTION = "descr";

    boolean addGroup(Group group) throws SQLException;
    boolean updateGroup(Group group) throws SQLException;
    boolean deleteGroup(int id) throws SQLException;
    Group getGroupById(int id) throws SQLException;
    List<Group> getGroups() throws SQLException;
}
