package com.example.userservice.user;

import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    private User initUser;

    private Gson gson = new Gson();

    @Before
    public void init(){
        initUser = new User();
        initUser.setId(44);
        initUser.setName("test name");
        initUser.setEmail("email@gmail.com");
        initUser.setAddress("This is example adress");
        initUser.setSalary(70000);
        initUser.setPhoneNumber("845916998");
        initUser.setMemberType(User.MemberType.SILVER); // this should be platinum but I mock via silver
        initUser.setRegisterDate(new Date());
        initUser.setReferenceCode(201800000000L);
    }

    @Test
    public void addUserTest () throws Exception {
        User testUser = new User();
        testUser.setId(44);
        testUser.setName("test name");
        testUser.setEmail("email@gmail.com");
        testUser.setAddress("This is example address");
        testUser.setSalary(70000);
        testUser.setPhoneNumber("845916998");

        when(userService.saveUser(testUser)).thenReturn(initUser);

        RequestBuilder request = MockMvcRequestBuilders
                .post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(testUser));

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json("{'id':44,'name':'test name','email':'email@gmail.com'," +
                        "'address':'This is example adress','salary':70000,'phoneNumber':'845916998'," +
                        "'registerDate':'"+new SimpleDateFormat("yyyy-MM-dd").format(new Date()) +
                        "','memberType':'SILVER','referenceCode':201800000000}"))
                .andReturn();
    }

    @Test
    public void getUserTest () throws Exception {
        when(userService.getOneUser(44)).thenReturn(initUser);

        RequestBuilder request = MockMvcRequestBuilders
                .get("/users/44")
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json("{'id':44,'name':'test name','email':'email@gmail.com'," +
                        "'address':'This is example adress','salary':70000,'phoneNumber':'845916998'," +
                        "'registerDate':'"+new SimpleDateFormat("yyyy-MM-dd").format(initUser.getRegisterDate()) +
                        "','memberType':'SILVER','referenceCode':201800000000}"))
                .andReturn();
    }
}
