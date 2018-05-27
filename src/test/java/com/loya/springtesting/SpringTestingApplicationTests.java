package com.loya.springtesting;

import com.loya.springtesting.dao.UserRepository;
import com.loya.springtesting.entity.User;
import com.loya.springtesting.service.Userservice;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.BDDMockito.given;

/**
 * Unit test
 */

@RunWith(SpringRunner.class)
@WebMvcTest
public class SpringTestingApplicationTests {

    @Autowired
    MockMvc mockMvc;

    //mock repository/data for a unit test
    @MockBean
    Userservice userservice;


    @Test
    public void contextLoads() throws Exception {
//        User alex = new User("Alex", 10000, "Wakannda");
//
//        List<User> allUsers = Arrays.asList(alex);
//
//        given(userservice.getAllUsers()).willReturn(allUsers);
//
//        //mock data
//        //because there's nothing on the db now, it will ultimately return an empty list of users
////        Mockito.when(userservice.getAllUsers())
////                .thenReturn(allUsers);
//
//        MvcResult mvcResult = mockMvc.perform(
//
//                MockMvcRequestBuilders.get("/users/all")
//                        .accept(MediaType.APPLICATION_JSON)
//        ).andReturn();
//
//        System.out.println("mock text result: " + mvcResult.getResponse().getContentAsString());
//
//        //verify the result of the test
//        // Mockito.verify(userservice).getAllUsers();

    }

}
