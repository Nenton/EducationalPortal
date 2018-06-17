package ru.innopolis.stc9.earth_stc9.db.dao;

import ru.innopolis.stc9.earth_stc9.pojo.GroupStudents;
import ru.innopolis.stc9.earth_stc9.pojo.User;

import java.sql.SQLException;
import java.util.List;

public interface IGroupsStudentsDAO {
    /*
     *  Получение списка студентов по определенной группе
     */
    List<User> getGroupsStudents(int namegroup) throws SQLException;

    /*
     *  Добавление студента по определенной группе
     */
    boolean addStudentGroup(GroupStudents groupStudents) throws SQLException;

    /*
     *  Получение студента по Id
     */
    GroupStudents getUserById(int id) throws SQLException;

    /*
     *  Удаление студента по Id
     */
    boolean deleteStudentGroup(int id) throws SQLException;

    /*
     *  Обновление студента по Id
     */
    boolean updateStudentGroup(GroupStudents groupStudents) throws SQLException;

    /*
     *  Получение списка студентов не включенных в группу
     */

    List<User> getStudentNotGroup() throws SQLException;

}
