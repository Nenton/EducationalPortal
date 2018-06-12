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
import ru.innopolis.stc9.earth_stc9.controllers.users.TeachersController;
import ru.innopolis.stc9.earth_stc9.pojo.User;
import ru.innopolis.stc9.earth_stc9.services.IUsersService;

import java.util.Collections;

@RunWith(PowerMockRunner.class)
@PrepareForTest(TeachersController.class)
public class TeachersControllerTest {
    private TeachersController controller;
    @Mock
    private IUsersService service;

    @Mock
    private Model model;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
        controller = new TeachersController(service);
    }

    @Test
    public void showTeachersTest() {
        Mockito.when(service.getUsers(Roles.TEACHER_ROLE_ID)).thenReturn(Collections.emptyList());

        String page = controller.showTeachers(model);

        Mockito.verify(service, Mockito.only()).getUsers(Roles.TEACHER_ROLE_ID);
        Mockito.verify(model, Mockito.times(1)).addAttribute("teachers", Collections.emptyList());
        Assert.assertEquals("teachers", page);
    }

    @Test
    public void addTeacherShowBlockTest() {
        Mockito.when(service.getUsers(Roles.TEACHER_ROLE_ID)).thenReturn(Collections.emptyList());

        String page = controller.addTeacherShowBlock(model);

        Mockito.verify(service, Mockito.times(1)).getUsers(Roles.TEACHER_ROLE_ID);
        Mockito.verify(model, Mockito.times(1)).addAttribute("teachers", Collections.emptyList());
        Mockito.verify(model, Mockito.times(1)).addAttribute("create", "Создать");
        Assert.assertEquals("teachers", page);
    }

    @Test
    public void addTeacherTest() {
        Mockito.when(service.getUsers(Roles.TEACHER_ROLE_ID)).thenReturn(Collections.emptyList());
        User user = Mockito.mock(User.class);
        try {
            PowerMockito.whenNew(User.class)
                    .withArguments("User", "Login", "Pass", Integer.parseInt("1"))
                    .thenReturn(user);
        } catch (Exception e) {
            Assert.fail();
        }

        String page = controller.addTeacher("User", "Login", "Pass", "1", model);

        Mockito.verify(service, Mockito.times(1)).getUsers(Roles.TEACHER_ROLE_ID);
        Mockito.verify(service, Mockito.times(1)).createUser(user);
        Mockito.verify(model, Mockito.times(1)).addAttribute("teachers", Collections.emptyList());
        Assert.assertEquals("teachers", page);
    }

    @Test
    public void editTeacherShowBlockTest() {
        Mockito.when(service.getUsers(Roles.TEACHER_ROLE_ID)).thenReturn(Collections.emptyList());
        User user = Mockito.mock(User.class);
        Mockito.when(service.getUserById(1)).thenReturn(user);
        Mockito.when(service.getRoles()).thenReturn(Collections.emptyList());

        String page = controller.editTeacherShowBlock(1, model);

        Mockito.verify(service, Mockito.times(1)).getUsers(Roles.TEACHER_ROLE_ID);
        Mockito.verify(service, Mockito.times(1)).getRoles();
        Mockito.verify(model, Mockito.times(1)).addAttribute("teachers", Collections.emptyList());
        Mockito.verify(model, Mockito.times(1)).addAttribute("roles", Collections.emptyList());
        Mockito.verify(model, Mockito.times(1)).addAttribute("user", user);
        Mockito.verify(model, Mockito.times(1)).addAttribute("update", "Изменить");
        Assert.assertEquals("teachers", page);
    }

    @Test
    public void editTeacherTest() {
        Mockito.when(service.getUsers(Roles.TEACHER_ROLE_ID)).thenReturn(Collections.emptyList());
        User user = Mockito.mock(User.class);
        try {
            PowerMockito.whenNew(User.class)
                    .withArguments(15, "Login", "Pass", Integer.parseInt("1"), "User")
                    .thenReturn(user);
        } catch (Exception e) {
            Assert.fail();
        }

        String page = controller.editTeacher(15, "User", "Login", "Pass", "1", model);

        Mockito.verify(service, Mockito.times(1)).getUsers(Roles.TEACHER_ROLE_ID);
        Mockito.verify(service, Mockito.times(1)).updateUser(user);
        Mockito.verify(model, Mockito.times(1)).addAttribute("teachers", Collections.emptyList());
        Assert.assertEquals("teachers", page);
    }

    @Test
    public void deleteTeacherTest() {
        Mockito.when(service.getUsers(Roles.TEACHER_ROLE_ID)).thenReturn(Collections.emptyList());

        String page = controller.deleteTeacher(15, model);

        Mockito.verify(service, Mockito.times(1)).getUsers(Roles.TEACHER_ROLE_ID);
        Mockito.verify(service, Mockito.times(1)).deleteUserById(15);
        Mockito.verify(model, Mockito.times(1)).addAttribute("teachers", Collections.emptyList());
        Assert.assertEquals("teachers", page);
    }
}
