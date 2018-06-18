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
public class StudentsControllerTest {
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
            mockMvc.perform(get("/students")
                    .with(user("user").roles("ADMIN")))
                    .andExpect(status().isOk());
            mockMvc.perform(get("/students")
                    .with(user("user").roles("TEACHER")))
                    .andExpect(status().isOk());
            mockMvc.perform(get("/students")
                    .with(user("user").roles("STUDENT")))
                    .andExpect(status().isOk());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

    @Test
    public void notShowUsersTest() {
        try {
            mockMvc.perform(get("/students")
                    .with(anonymous()))
                    .andExpect(status().isFound());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

    @Test
    public void addUserShowBlockTest() {
        try {
            mockMvc.perform(get("/studentCreate")
                    .with(user("user").roles("ADMIN")))
                    .andExpect(status().isOk());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

    @Test
    public void notAccessAddUserShowBlockTest() {
        try {
            mockMvc.perform(get("/studentCreate")
                    .with(user("user").roles("TEACHER", "STUDENT")))
                    .andExpect(status().isForbidden());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

    @Test
    public void notAddUserShowBlockTest() {
        try {
            mockMvc.perform(get("/studentCreate")
                    .with(anonymous()))
                    .andExpect(status().isFound());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

    @Test
    public void addUserTest() {
        try {
            mockMvc.perform(post("/studentCreate")
                    .with(user("admin").roles("ADMIN")))
                    .andExpect(status().isOk());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

    @Test
    public void notAccessAddUserTest() {
        try {
            mockMvc.perform(post("/studentCreate")
                    .with(user("user").roles("TEACHER", "STUDENT")))
                    .andExpect(status().isForbidden());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

    @Test
    public void notAddUserTest() {
        try {
            mockMvc.perform(post("/studentCreate")
                    .with(anonymous()))
                    .andExpect(status().isFound());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

    @Test
    public void editUserShowBlockTest() {
        try {
            mockMvc.perform(get("/studentEdit/0")
                    .with(user("admin").roles("ADMIN")))
                    .andExpect(status().isOk());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

    @Test
    public void notAccessEditUserShowBlockTest() {
        try {
            mockMvc.perform(get("/studentEdit/0")
                    .with(user("user").roles("TEACHER", "STUDENT")))
                    .andExpect(status().isForbidden());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

    @Test
    public void notEditUserShowBlockTest() {
        try {
            mockMvc.perform(get("/studentEdit/0")
                    .with(anonymous()))
                    .andExpect(status().isFound());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

    @Test
    public void editUserTest() {
        try {
            mockMvc.perform(post("/studentEdit/0")
                    .with(user("admin").roles("ADMIN")))
                    .andExpect(status().isOk());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

    @Test
    public void notAccessEditUserTest() {
        try {
            mockMvc.perform(post("/studentEdit/0")
                    .with(user("user").roles("TEACHER", "STUDENT")))
                    .andExpect(status().isForbidden());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

    @Test
    public void notEditUserTest() {
        try {
            mockMvc.perform(post("/studentEdit/0")
                    .with(anonymous()))
                    .andExpect(status().isFound());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

    @Test
    public void deleteUserTest() {
        try {
            mockMvc.perform(post("/studentDelete/0")
                    .with(user("admin").roles("ADMIN")))
                    .andExpect(status().isOk());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

    @Test
    public void notAccessDeleteUserTest() {
        try {
            mockMvc.perform(post("/studentDelete/0")
                    .with(user("user").roles("TEACHER", "STUDENT")))
                    .andExpect(status().isForbidden());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

    @Test
    public void notDeleteUserTest() {
        try {
            mockMvc.perform(post("/studentDelete/0")
                    .with(anonymous()))
                    .andExpect(status().isFound());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        }
    }
}