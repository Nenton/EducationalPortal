package ru.innopolis.stc9.earth_stc9.services;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.innopolis.stc9.earth_stc9.db.dao.GroupsStudentsDAO;
import ru.innopolis.stc9.earth_stc9.pojo.GroupStudents;
import ru.innopolis.stc9.earth_stc9.pojo.User;

import java.sql.SQLException;
import java.util.ArrayList;

@Service
public class GroupStudentService implements IGroupStudentService {
    private Logger logger = Logger.getLogger(UsersService.class);
    @Autowired
    private GroupsStudentsDAO groupsStudentsDAO;

    @Override
    public ArrayList<User> getStudentfromGroup(int id) {
        try {
            return groupsStudentsDAO.getGroupsStudents(id);
        } catch (SQLException e) {
            logger.warn("Ошибка получения студента по id группы- " + id, e);
            return null;
        }
    }


    @Override
    public boolean addStudentInGroup(GroupStudents groupStudents) {
        try {
            return groupsStudentsDAO.addStudentGroup(groupStudents);
        } catch (SQLException e) {
            logger.warn("Ошибка добавления id студента в  группу ", e);
            return false;
        }

    }

    @Override
    public boolean deleteStudentInGroup(int id) {
        try {
            return groupsStudentsDAO.deleteStudentGroup(id);
        } catch (SQLException e) {
            logger.warn("Ошибка удаления id студента из  группы ", e);
            return false;
        }
    }

    @Override
    public boolean updateStudentInGroup(GroupStudents groupStudents) {
        try {
            return groupsStudentsDAO.updateStudentGroup(groupStudents);
        } catch (SQLException e) {
            logger.warn("Ошибка обновления  студента в  группе ", e);
            return false;
        }

    }

}


