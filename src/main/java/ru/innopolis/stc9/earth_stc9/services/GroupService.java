package ru.innopolis.stc9.earth_stc9.services;

import ru.innopolis.stc9.earth_stc9.db.dao.GroupDao;
import ru.innopolis.stc9.earth_stc9.db.dao.IGroupDao;
import ru.innopolis.stc9.earth_stc9.pojo.Group;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Aleksandr Tikhonov - created 29.05.2018
 */

public class GroupService implements IGroupService {
    private IGroupDao groupDao = new GroupDao();

    @Override
    public boolean createGroup(Group group){
        try {
            return groupDao.addGroup(group);
        } catch (SQLException e) {
            logger.warn("Ошибка создания группы", e);
            return false;
        }
    }

    @Override
    public boolean updateGroup(Group group){
        try {
            return groupDao.updateGroup(group);
        } catch (SQLException e) {
            logger.warn("Ошибка обновления группы", e);
            return false;
        }
    }

    @Override
    public boolean deleteGroup(int id){
        try {
            return groupDao.deleteGroup(id);
        } catch (SQLException e) {
            logger.warn("Ошибка удаления группы", e);
            return false;
        }
    }

    @Override
    public Group getGroupById(int id){
        try {
            return groupDao.getGroupById(id);
        } catch (SQLException e) {
            logger.warn("Ошибка получения группы с id = " + id, e);
            return null;
        }
    }

    @Override
    public List<Group> getGroups(){
        try {
            return groupDao.getGroups();
        } catch (SQLException e) {
            logger.warn("Ошибка получения списка групп", e);
            return null;
        }
    }
}
