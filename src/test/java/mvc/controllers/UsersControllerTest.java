package mvc.controllers;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("../../../resources/main-servlet-test.xml")
@WebAppConfiguration
public class UsersControllerTest {
    @Autowired
    private WebApplicationContext context;
    private MockMvc mockMvc;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .build();
    }

    @Test
    public void showUsersTest() {
        try {
            mockMvc.perform(get("/users"))
                    .andExpect(status().isOk());
        } catch (Exception e) {
            Assert.fail();
        }
    }

    @Test
    public void addUserShowBlockTest() {
        try {
            mockMvc.perform(get("/userCreate"))
                    .andExpect(status().isOk());
        } catch (Exception e) {
            Assert.fail();
        }
    }

    @Test
    public void addUserTest() {
        try {
            mockMvc.perform(post("/userCreate"))
                    .andExpect(status().isOk());
        } catch (Exception e) {
            Assert.fail();
        }
    }

    @Test
    public void editUserShowBlockTest() {
        try {
            MockHttpSession session = Mockito.mock(MockHttpSession.class);
            Mockito.when(session.getAttribute("login")).thenReturn("admin");
            Mockito.when(session.getAttribute("role")).thenReturn("1");
            mockMvc.perform(get("/userEdit/*")
                    .session(session))
                    .andExpect(status().isOk());
        } catch (Exception e) {
            Assert.fail();
        }
    }

    @Test
    public void editUserTest() {
        try {
            mockMvc.perform(post("/userEdit/*"))
                    .andExpect(status().isOk());
        } catch (Exception e) {
            Assert.fail();
        }
    }

    @Test
    public void deleteUserTest() {
        try {
            mockMvc.perform(post("/userDelete/*"))
                    .andExpect(status().isOk());
        } catch (Exception e) {
            Assert.fail();
        }
    }

}
