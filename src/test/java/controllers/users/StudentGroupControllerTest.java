package controllers.users;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.innopolis.stc9.earth_stc9.controllers.users.GroupStudentController;
import ru.innopolis.stc9.earth_stc9.pojo.Group;
import ru.innopolis.stc9.earth_stc9.pojo.GroupStudents;
import ru.innopolis.stc9.earth_stc9.pojo.User;
import ru.innopolis.stc9.earth_stc9.services.IGroupService;
import ru.innopolis.stc9.earth_stc9.services.IGroupStudentService;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/*
 *   Тестирование слоя контроллеров
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"../../../resources/main-servlet-test.xml", "../../../resources/security-context.xml"})
@WebAppConfiguration
public class StudentGroupControllerTest {
    MockMvc mockMvc;
    @Mock
    IGroupStudentService mockGroupStudentService;
    List<Group> group;
    List<User> groupStudents;
    List<User> studentnotGroup;
    Group Getgroup;
    @Mock
    private IGroupService mockGroupService;
    @InjectMocks
    private GroupStudentController groupStudentController;

    @Before
    public void before() {
        group = Arrays.asList(new Group(1, "один", "один"),
                new Group(2, "два", "два"));
        groupStudents = Arrays.asList(new User(1, "Иван Иванов", 1, "stc-11", "test"),
                new User(2, "Иван Иванов", 2, "stc-10", "test"));
        studentnotGroup = Arrays.asList(new User(1, "студент"));
        Getgroup = new Group(100, "stc-100", "Юбилейная группа");
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(groupStudentController)
                .build();

    }

    @Test
    public void getGroupsTest() {
        when(mockGroupService.getGroups()).thenReturn(group);
        try {
            mockMvc.perform(get("/studentgroup"))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_FORM_URLENCODED))
                    .andExpect(view().name("studentgroup"))
                    .andExpect(model().attribute("group", hasSize(2)))
                    .andExpect(model().attribute("studentnotgroup", hasSize(1)));
            verify(mockGroupService, times(1)).getGroups();
            verifyNoMoreInteractions(mockGroupService);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void getStudentListTest() {
        when(mockGroupStudentService.getStudentfromGroup(1)).thenReturn(groupStudents);
        when(mockGroupStudentService.getStudentnotGroup()).thenReturn(studentnotGroup);
        when(mockGroupService.getGroupById(1)).thenReturn(Getgroup);
        try {
            mockMvc.perform(get("/studentgroup/{groupname}", 1))
                    .andExpect(status().isOk())
                    .andExpect(view().name("viewstudentgroup"))
                    .andExpect(model().attribute("studentgroup", hasSize(2)))
                    .andExpect(model().attribute("studentnotgroup", hasSize(1)))
                    .andExpect(model().attribute("group", Getgroup));
            verify(mockGroupStudentService, times(1)).getStudentfromGroup(1);
            verify(mockGroupStudentService, times(1)).getStudentnotGroup();
            verify(mockGroupService, times(1)).getGroupById(1);
            verifyNoMoreInteractions(mockGroupStudentService);
            verifyNoMoreInteractions(mockGroupService);

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    @Test
    public void addStudentGroupTest() {
        GroupStudents groupStudents = new GroupStudents(1, 1);
        when(mockGroupStudentService.addStudentInGroup(groupStudents)).thenReturn(true);

        try {
            mockMvc.perform(post("/addstudentgroup")
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                    .param("studentnotgroup", "1")
                    .param("namegroup", "1"))
                    .andExpect(status().isOk());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void deleteStudentGroupTest() {
        User userFordeleted = new User(1, "Василий Иванов");
        when(mockGroupStudentService.deleteStudentInGroup(userFordeleted.getId())).thenReturn(true);
        try {
            mockMvc.perform(post("/studentgroupdelete/{id}", 1)
                    .param("idgroup", "stc-100"))
                    .andExpect(status().isOk());
            verify(mockGroupStudentService, times(1)).deleteStudentInGroup(userFordeleted.getId());
            verifyNoMoreInteractions(mockGroupStudentService);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
