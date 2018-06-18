package ru.innopolis.stc9.earth_stc9.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.innopolis.stc9.earth_stc9.db.dao.GroupsStudentsDAO;
import ru.innopolis.stc9.earth_stc9.pojo.GroupStudents;
import ru.innopolis.stc9.earth_stc9.pojo.User;

import java.util.List;

@Service
public class GroupStudentService implements IGroupStudentService {

    @Autowired
    private GroupsStudentsDAO groupsStudentsDAO;

    @Override
    public List<User> getStudentfromGroup(int namegroup) {
        return groupsStudentsDAO.getGroupsStudents(namegroup);

    }

    @Override
    public boolean addStudentInGroup(GroupStudents groupStudents) {
        if (groupStudents == null) {
            return false;
        }
        return groupsStudentsDAO.addStudentGroup(groupStudents);
    }

    @Override
    public boolean deleteStudentInGroup(int id) {
        if (id == 0) {
            return false;
        }
        return groupsStudentsDAO.deleteStudentGroup(id);
    }

    @Override
    public boolean updateStudentInGroup(GroupStudents groupStudents) {
        if (groupStudents == null) {
            return false;
        }
        return groupsStudentsDAO.updateStudentGroup(groupStudents);
    }

    @Override
    public List<User> getStudentnotGroup() {
        return groupsStudentsDAO.getStudentNotGroup();

    }


}


