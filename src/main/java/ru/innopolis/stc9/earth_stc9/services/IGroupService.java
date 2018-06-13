package ru.innopolis.stc9.earth_stc9.services;

import ru.innopolis.stc9.earth_stc9.pojo.Group;

import java.util.List;

/**
 * @author Aleksandr Tikhonov - created 29.05.2018
 */

public interface IGroupService {
    boolean createGroup(Group group);

    boolean updateGroup(Group group);

    boolean deleteGroup(int id);

    Group getGroupById(int id);

    List<Group> getGroups();
}
