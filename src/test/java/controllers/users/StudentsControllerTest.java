package controllers.users;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.ui.Model;
import ru.innopolis.stc9.earth_stc9.controllers.users.Roles;
import ru.innopolis.stc9.earth_stc9.controllers.users.StudentsController;
import ru.innopolis.stc9.earth_stc9.pojo.User;
import ru.innopolis.stc9.earth_stc9.services.IUsersService;

import java.util.Collections;

@RunWith(PowerMockRunner.class)
@PrepareForTest(StudentsController.class)
public class StudentsControllerTest {
    private StudentsController controller;
    @Mock
    private IUsersService service;

    @Mock
    private Model model;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
        controller = new StudentsController(service);
    }

    @Test
    public void showStudentsTest() {
        Mockito.when(service.getUsers(Roles.STUDENT_ROLE_ID)).thenReturn(Collections.emptyList());

        String page = controller.showStudents(model);

        Mockito.verify(service, Mockito.only()).getUsers(Roles.STUDENT_ROLE_ID);
        Mockito.verify(model, Mockito.times(1)).addAttribute("students", Collections.emptyList());
        Assert.assertEquals("students", page);
    }

    @Test
    public void addStudentShowBlockTest() {
        Mockito.when(service.getUsers(Roles.STUDENT_ROLE_ID)).thenReturn(Collections.emptyList());

        String page = controller.addStudentShowBlock(model);

        Mockito.verify(service, Mockito.times(1)).getUsers(Roles.STUDENT_ROLE_ID);
        Mockito.verify(model, Mockito.times(1)).addAttribute("students", Collections.emptyList());
        Mockito.verify(model, Mockito.times(1)).addAttribute("create", "Создать");
        Assert.assertEquals("students", page);
    }

    @Test
    public void addStudentTest() {
        Mockito.when(service.getUsers(Roles.STUDENT_ROLE_ID)).thenReturn(Collections.emptyList());
        User user = Mockito.mock(User.class);
        try {
            PowerMockito.whenNew(User.class)
                    .withArguments("User", "Login", "Pass", 0)
                    .thenReturn(user);
        } catch (Exception e) {
            Assert.fail();
        }

        String page = controller.addStudent("User", "Login", "Pass", 0, model);

        Mockito.verify(service, Mockito.times(1)).getUsers(Roles.STUDENT_ROLE_ID);
        Mockito.verify(service, Mockito.times(1)).createUser(user);
        Mockito.verify(model, Mockito.times(1)).addAttribute("students", Collections.emptyList());
        Assert.assertEquals("students", page);
    }

    @Test
    public void editStudentShowBlockTest() {
        Mockito.when(service.getUsers(Roles.STUDENT_ROLE_ID)).thenReturn(Collections.emptyList());
        User user = Mockito.mock(User.class);
        Mockito.when(service.getUserById(1)).thenReturn(user);
        Mockito.when(service.getRoles()).thenReturn(Collections.emptyList());

        String page = controller.editStudentShowBlock(1, model);

        Mockito.verify(service, Mockito.times(1)).getUsers(Roles.STUDENT_ROLE_ID);
        Mockito.verify(service, Mockito.times(1)).getRoles();
        Mockito.verify(model, Mockito.times(1)).addAttribute("students", Collections.emptyList());
        Mockito.verify(model, Mockito.times(1)).addAttribute("roles", Collections.emptyList());
        Mockito.verify(model, Mockito.times(1)).addAttribute("user", user);
        Mockito.verify(model, Mockito.times(1)).addAttribute("update", "Изменить");
        Assert.assertEquals("students", page);
    }

    @Test
    public void editStudentTest() {
        Mockito.when(service.getUsers(Roles.STUDENT_ROLE_ID)).thenReturn(Collections.emptyList());
        User user = Mockito.mock(User.class);
        try {
            PowerMockito.whenNew(User.class)
                    .withArguments(15, "Login", "Pass", 0, "User")
                    .thenReturn(user);
        } catch (Exception e) {
            Assert.fail();
        }

        String page = controller.editStudent(15, "User", "Login", "Pass", 0, model);

        Mockito.verify(service, Mockito.times(1)).getUsers(Roles.STUDENT_ROLE_ID);
        Mockito.verify(service, Mockito.times(1)).updateUser(user);
        Mockito.verify(model, Mockito.times(1)).addAttribute("students", Collections.emptyList());
        Assert.assertEquals("students", page);
    }

    @Test
    public void deleteStudentTest() {
        Mockito.when(service.getUsers(Roles.STUDENT_ROLE_ID)).thenReturn(Collections.emptyList());

        String page = controller.deleteStudent(15, model);

        Mockito.verify(service, Mockito.times(1)).getUsers(Roles.STUDENT_ROLE_ID);
        Mockito.verify(service, Mockito.times(1)).deleteUserById(15);
        Mockito.verify(model, Mockito.times(1)).addAttribute("students", Collections.emptyList());
        Assert.assertEquals("students", page);
    }
}
