package ru.innopolis.stc9.earth_stc9.services;

import ru.innopolis.stc9.earth_stc9.pojo.GroupStudents;
import ru.innopolis.stc9.earth_stc9.pojo.User;

import java.util.ArrayList;

public interface IGroupStudentService {
    ArrayList<User> getStudentfromGroup(int id);

    boolean addStudentInGroup(GroupStudents groupStudents);

    boolean deleteStudentInGroup(int id);

    boolean updateStudentInGroup(GroupStudents groupStudents);
}
