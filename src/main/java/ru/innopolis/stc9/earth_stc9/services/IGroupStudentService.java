package ru.innopolis.stc9.earth_stc9.services;

import ru.innopolis.stc9.earth_stc9.pojo.GroupStudents;
import ru.innopolis.stc9.earth_stc9.pojo.User;

import java.util.List;

/**
 * @author Azat Timershin - created 14.06.2018
 */

public interface IGroupStudentService {

    List<User> getStudentfromGroup(int namegroup);

    boolean addStudentInGroup(GroupStudents groupStudents);

    boolean deleteStudentInGroup(int id);

    boolean updateStudentInGroup(GroupStudents groupStudents);

    List<User> getStudentnotGroup();
}
