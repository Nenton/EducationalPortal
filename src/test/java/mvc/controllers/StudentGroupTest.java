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

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
/*
 *   Тесты доступа к страницам, тестируеться спринг секьюрити
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"../../../resources/main-servlet-test.xml", "../../../resources/security-context.xml"})
@WebAppConfiguration
public class StudentGroupTest {
    @Autowired
    WebApplicationContext context;
    MockMvc mockMvc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    public void getGroup() {
        try {
            mockMvc.perform(get("/studentgroup")
                    .with(user("user").roles("ADMIN")))
                    .andExpect(status().isOk());
            mockMvc.perform(get("/studentgroup")
                    .with(user("user").roles("TEACHER")))
                    .andExpect(status().isOk());
            mockMvc.perform(get("/studentgroup")
                    .with(user("user").roles("STUDENT")))
                    .andExpect(status().isOk());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getStudentList() {
        try {
            mockMvc.perform(get("/studentgroup/1")
                    .with(user("user").roles("ADMIN")))
                    .andExpect(status().isOk());
            mockMvc.perform(get("/studentgroup/2")
                    .with(user("user").roles("TEACHER")))
                    .andExpect(status().isOk());
            mockMvc.perform(get("/studentgroup/1")
                    .with(user("user").roles("STUDENT")))
                    .andExpect(status().isOk());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void addStudentList2() {
        try {
            mockMvc.perform(post("/addstudentgroup")
                    .param("studentnotgroup", "1")
                    .param("namegroup", "1")
                    .with(user("user").roles("ADMIN")))
                    .andExpect(status().isOk());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        }
    }
}
