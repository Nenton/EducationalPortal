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
import ru.innopolis.stc9.earth_stc9.controllers.users.UsersController;
import ru.innopolis.stc9.earth_stc9.pojo.User;
import ru.innopolis.stc9.earth_stc9.services.IUsersService;

import java.util.Collections;

@RunWith(PowerMockRunner.class)
@PrepareForTest(UsersController.class)
public class UsersControllerTest {
    private UsersController controller;
    @Mock
    private IUsersService service;

    @Mock
    private Model model;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
        controller = new UsersController(service);
    }

    @Test
    public void showUsersTest() {
        Mockito.when(service.getUsers()).thenReturn(Collections.emptyList());

        String page = controller.showUsers(model);

        Mockito.verify(service, Mockito.only()).getUsers();
        Mockito.verify(model, Mockito.times(1)).addAttribute("users", Collections.emptyList());
        Assert.assertEquals("users", page);
    }

    @Test
    public void addUserShowBlockTest() {
        Mockito.when(service.getUsers()).thenReturn(Collections.emptyList());
        Mockito.when(service.getRoles()).thenReturn(Collections.emptyList());

        String page = controller.addUserShowBlock(model);

        Mockito.verify(service, Mockito.times(1)).getUsers();
        Mockito.verify(service, Mockito.times(1)).getRoles();
        Mockito.verify(model, Mockito.times(1)).addAttribute("users", Collections.emptyList());
        Mockito.verify(model, Mockito.times(1)).addAttribute("create", "Создать");
        Mockito.verify(model, Mockito.times(1)).addAttribute("roles", Collections.emptyList());
        Assert.assertEquals("users", page);
    }

    @Test
    public void addUserTest() {
        Mockito.when(service.getUsers()).thenReturn(Collections.emptyList());
        User user = Mockito.mock(User.class);
        try {
            PowerMockito.whenNew(User.class)
                    .withArguments("User", "Login", "Pass", Integer.parseInt("1"))
                    .thenReturn(user);
        } catch (Exception e) {
            Assert.fail();
        }

        String page = controller.addUser("User", "Login", "Pass", "1", model);

        Mockito.verify(service, Mockito.times(1)).getUsers();
        Mockito.verify(service, Mockito.times(1)).createUser(user);
        Mockito.verify(model, Mockito.times(1)).addAttribute("users", Collections.emptyList());
        Assert.assertEquals("users", page);
    }

    @Test
    public void editUserShowBlockTest() {
        Mockito.when(service.getUsers()).thenReturn(Collections.emptyList());
        User user = Mockito.mock(User.class);
        Mockito.when(service.getUserById(1)).thenReturn(user);
        Mockito.when(service.getRoles()).thenReturn(Collections.emptyList());

        String page = controller.editUserShowBlock(1, model);

        Mockito.verify(service, Mockito.times(1)).getUsers();
        Mockito.verify(service, Mockito.times(1)).getRoles();
        Mockito.verify(model, Mockito.times(1)).addAttribute("users", Collections.emptyList());
        Mockito.verify(model, Mockito.times(1)).addAttribute("roles", Collections.emptyList());
        Mockito.verify(model, Mockito.times(1)).addAttribute("user", user);
        Mockito.verify(model, Mockito.times(1)).addAttribute("update", "Изменить");
        Assert.assertEquals("users", page);
    }

    @Test
    public void editUserTest() {
        Mockito.when(service.getUsers()).thenReturn(Collections.emptyList());
        User user = Mockito.mock(User.class);
        try {
            PowerMockito.whenNew(User.class)
                    .withArguments(15, "Login", "Pass", Integer.parseInt("1"), "User")
                    .thenReturn(user);
        } catch (Exception e) {
            Assert.fail();
        }

        String page = controller.editUser(15, "User", "Login", "Pass", "1", model);

        Mockito.verify(service, Mockito.times(1)).getUsers();
        Mockito.verify(service, Mockito.times(1)).updateUser(user);
        Mockito.verify(model, Mockito.times(1)).addAttribute("users", Collections.emptyList());
        Assert.assertEquals("users", page);
    }

    @Test
    public void deleteUserTest() {
        Mockito.when(service.getUsers()).thenReturn(Collections.emptyList());

        String page = controller.deleteUser(15, model);

        Mockito.verify(service, Mockito.times(1)).getUsers();
        Mockito.verify(service, Mockito.times(1)).deleteUserById(15);
        Mockito.verify(model, Mockito.times(1)).addAttribute("users", Collections.emptyList());
        Assert.assertEquals("users", page);
    }
}
