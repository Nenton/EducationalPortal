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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"../../../resources/main-servlet-test.xml"})
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
                .build();
    }

    @Test
    public void showUsersTest() {
        try {
            mockMvc.perform(get("/students"))
                    .andExpect(status().isOk());
        } catch (Exception e) {
            Assert.fail();
        }
    }

    @Test
    public void addUserShowBlockTest() {
        try {
            mockMvc.perform(get("/studentCreate"))
                    .andExpect(status().isOk());
        } catch (Exception e) {
            Assert.fail();
        }
    }

    @Test
    public void addUserTest() {
        try {
            mockMvc.perform(post("/studentCreate"))
                    .andExpect(status().isOk());
        } catch (Exception e) {
            Assert.fail();
        }
    }

    @Test
    public void editUserShowBlockTest() {
        try {
            mockMvc.perform(get("/studentEdit/0"))
                    .andExpect(status().isOk());

        } catch (Exception e) {
            Assert.fail();
        }
    }

    @Test
    public void editUserTest() {
        try {
            mockMvc.perform(post("/studentEdit/0"))
                    .andExpect(status().isOk());
        } catch (Exception e) {
            Assert.fail();
        }
    }

    @Test
    public void deleteUserTest() {
        try {
            mockMvc.perform(post("/studentDelete/0"))
                    .andExpect(status().isOk());
        } catch (Exception e) {
            Assert.fail();
        }
    }
}