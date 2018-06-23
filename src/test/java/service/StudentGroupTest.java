package service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import ru.innopolis.stc9.earth_stc9.db.dao.GroupsStudentsDAO;
import ru.innopolis.stc9.earth_stc9.pojo.GroupStudents;
import ru.innopolis.stc9.earth_stc9.pojo.User;
import ru.innopolis.stc9.earth_stc9.services.GroupStudentService;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/*
 *   Тесты слоя сервисы
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({GroupStudentService.class})
public class StudentGroupTest {
    private GroupStudentService groupStudentService;
    private GroupStudents groupStudents;
    private GroupsStudentsDAO groupsStudentsDAO;
    private List<User> list;


    @Before
    public void setUp() throws IllegalAccessException {
        groupStudentService = new GroupStudentService();
        groupStudents = new GroupStudents(2, 2, 2);
        groupsStudentsDAO = PowerMockito.mock(GroupsStudentsDAO.class);
        Field field = PowerMockito.field(GroupStudentService.class, "groupsStudentsDAO");
        field.set(groupStudentService, groupsStudentsDAO);
        list = new ArrayList<>();

    }

    @Test
    public void getStudentfromGroup() {
        PowerMockito.when(groupsStudentsDAO.getGroupsStudents(1)).thenReturn(list);
        assertEquals(groupStudentService.getStudentfromGroup(1), list);
    }

    @Test
    public void addStudentInGroup()

    {
        PowerMockito.when(groupsStudentsDAO.addStudentGroup(groupStudents)).thenReturn(true);
        assertTrue(groupStudentService.addStudentInGroup(groupStudents));
    }

    @Test
    public void deleteStudentInGroup() {
        PowerMockito.when(groupsStudentsDAO.deleteStudentGroup(1)).thenReturn(true);
        assertTrue(groupStudentService.deleteStudentInGroup(1));
    }

    @Test
    public void updateStudentInGroup() {
        PowerMockito.when(groupsStudentsDAO.updateStudentGroup(groupStudents)).thenReturn(true);
        assertTrue(groupStudentService.updateStudentInGroup(groupStudents));
    }

    @Test
    public void getStudentnotGroup() {
        PowerMockito.when(groupsStudentsDAO.getStudentNotGroup()).thenReturn(list);
        assertEquals(groupStudentService.getStudentnotGroup(), list);
    }

}
