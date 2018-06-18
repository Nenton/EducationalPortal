package mvc.controllers;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.anonymous;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"../../../resources/main-servlet-test.xml", "../../../resources/security-context.xml"})
@WebAppConfiguration
public class UsersControllerTest {
    @Autowired
    private WebApplicationContext context;
    private MockMvc mockMvc;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    public void showUsersTest() {
        try {
            mockMvc.perform(get("/users")
                    .with(user("user").roles("ADMIN")))
                    .andExpect(status().isOk());
            mockMvc.perform(get("/users")
                    .with(user("user").roles("STUDENT")))
                    .andExpect(status().isOk());
            mockMvc.perform(get("/users")
                    .with(user("user").roles("TEACHER")))
                    .andExpect(status().isOk());
            mockMvc.perform(get("/users")
                    .with(anonymous()))
                    .andExpect(status().isFound());
        } catch (Exception e) {
            Assert.fail();
        }
    }

    @Test
    public void addUserShowBlockTest() {
        try {
            mockMvc.perform(get("/userCreate")
                    .with(user("user").roles("ADMIN")))
                    .andExpect(status().isOk());
            mockMvc.perform(get("/userCreate")
                    .with(user("user").roles("STUDENT", "TEACHER")))
                    .andExpect(status().isForbidden());
            mockMvc.perform(get("/userCreate")
                    .with(anonymous()))
                    .andExpect(status().isFound());
        } catch (Exception e) {
            Assert.fail();
        }
    }

    @Test
    public void addUserTest() {
        try {
            mockMvc.perform(post("/userCreate")
                    .with(user("user").roles("ADMIN")))
                    .andExpect(status().isOk());
            mockMvc.perform(post("/userCreate")
                    .with(user("user").roles("STUDENT", "TEACHER")))
                    .andExpect(status().isForbidden());
            mockMvc.perform(post("/userCreate")
                    .with(anonymous()))
                    .andExpect(status().isFound());
        } catch (Exception e) {
            Assert.fail();
        }
    }

    @Test
    public void editUserShowBlockTest() {
        try {
            mockMvc.perform(get("/userEdit/0")
                    .with(user("user").roles("ADMIN")))
                    .andExpect(status().isOk());
            mockMvc.perform(get("/userEdit/0")
                    .with(user("user").roles("STUDENT", "TEACHER")))
                    .andExpect(status().isForbidden());
            mockMvc.perform(get("/userEdit/0")
                    .with(anonymous()))
                    .andExpect(status().isFound());
        } catch (Exception e) {
            Assert.fail();
        }
    }

    @Test
    public void editUserTest() {
        try {
            mockMvc.perform(post("/userEdit/0")
                    .with(user("user").roles("ADMIN")))
                    .andExpect(status().isOk());
            mockMvc.perform(post("/userEdit/0")
                    .with(user("user").roles("STUDENT", "TEACHER")))
                    .andExpect(status().isForbidden());
            mockMvc.perform(post("/userEdit/0")
                    .with(anonymous()))
                    .andExpect(status().isFound());
        } catch (Exception e) {
            Assert.fail();
        }
    }

    @Test
    public void deleteUserTest() {
        try {
            mockMvc.perform(post("/userDelete/0")
                    .with(user("user").roles("ADMIN")))
                    .andExpect(status().isOk());
            mockMvc.perform(post("/userDelete/0")
                    .with(user("user").roles("STUDENT", "TEACHER")))
                    .andExpect(status().isForbidden());
            mockMvc.perform(post("/userDelete/0")
                    .with(anonymous()))
                    .andExpect(status().isFound());
        } catch (Exception e) {
            Assert.fail();
        }
    }
}