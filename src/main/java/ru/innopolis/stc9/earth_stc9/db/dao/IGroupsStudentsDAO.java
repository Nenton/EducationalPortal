package ru.innopolis.stc9.earth_stc9.db.dao;

import ru.innopolis.stc9.earth_stc9.pojo.GroupStudents;
import ru.innopolis.stc9.earth_stc9.pojo.User;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IGroupsStudentsDAO {
    ArrayList<User> getGroupsStudents(int studentId) throws SQLException;

    boolean addStudentGroup(GroupStudents groupStudents) throws SQLException;

    GroupStudents getUserById(int id) throws SQLException;

    boolean deleteStudentGroup(int id) throws SQLException;

    boolean updateStudentGroup(GroupStudents groupStudents) throws SQLException;
}
