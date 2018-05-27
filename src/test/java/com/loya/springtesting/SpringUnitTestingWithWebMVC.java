package com.loya.springtesting;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.loya.springtesting.controller.UserController;
import com.loya.springtesting.entity.User;
import com.loya.springtesting.service.Userservice;
import com.loya.springtesting.utils.Helper;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Arrays;
import java.util.List;

import static com.loya.springtesting.utils.Helper.asJSONObject;
import static com.loya.springtesting.utils.Helper.asJSONString;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * A unit test does not access the database, hence it uses mock data
 */
@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class SpringUnitTestingWithWebMVC {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private Userservice userservice;

    @Autowired
    private ObjectMapper mapper;

    @Test
    public void givenUsers_whenGetUsers_thenReturnJsonArray() throws Exception {
        User alex = new User("Alex", 10000, "Wakannda");
        User tobi = new User("Tobi", 1500, "Wakannda");

        List<User> allUsers = Arrays.asList(alex, tobi);

        given(userservice.getAllUsers()).willReturn(allUsers);

        MvcResult mvcResult = mvc.perform(get("/users/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(allUsers.size())))
                .andExpect(jsonPath("$[1].salary", Matchers.is(tobi.getSalary()))).andReturn();

        System.out.println("Test: 'givenUsers_whenGetUsers_thenReturnJsonArray', Result: " +
                mvcResult.getResponse().getContentAsString());

    }

    @Test
    public void givenUser_WhenPostUser_thenReturnUser() throws Exception {
        //new User
        User tobi = new User(9, "Tobi", 1500, "Wakannda");

        String jsonString = asJSONString(tobi);

        JSONObject obj = asJSONObject(jsonString);

        given(userservice.adNewUser(tobi)).willReturn(tobi);
        //converts the object to a string
        //String json = mapper.writeValueAsString(tobi);
        MvcResult resultActions = mvc.perform(post("/users/adduser")
                .content(asJSONString(tobi))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        System.out.println("givenUser_WhenPostUser_thenReturnUser(): " + resultActions.getResponse());
        System.out.println("Json String:" + asJSONString(tobi));

        int id = 0;
        String name = null;
        int salary = 0;
        String teamName = null;

        id = obj.getInt("id");
        name = obj.getString("name");
        salary = obj.getInt("salary");
        teamName = obj.getString("teamName");

        System.out.println("id: " + id + "\n" + "name: " + name + "\n" + "salary: " + salary + "\n" + "teamName: " + teamName);
    }

    @Test
    public void givenUser_WhenPutUser_thenReturnUser() throws Exception {
        //new User
        User tobi = new User(99, "Tobi", 1500, "Wakanda");

        given(userservice.adNewUser(tobi)).willReturn(tobi);
        //converts the object to a string
        //String json = mapper.writeValueAsString(tobi);
        MvcResult resultActions = mvc.perform(put("/users/updateuser")
                .content(asJSONString(tobi))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print()).andReturn();

        System.out.println("givenUser_WhenPutUser_thenReturnUser(): " +
                resultActions.getResponse().getContentAsString());

    }

    @Test
    public void givenUserId_WhenGetUser_thenReturnUser_success() throws Exception {
        //mock data
        User eden = new User(1, "Eden", 1000, "Calypso");
        given(userservice.getUserById(eden.getId())).willReturn(java.util.Optional.ofNullable(eden));

        MvcResult mvcResult = mvc.perform(get("/users/getuserbyid/{id}", eden.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Eden")))
                .andExpect(jsonPath("$.salary", is(1000)))
                .andExpect(jsonPath("$.teamName", is("Calypso")))
                .andReturn();

        System.out.println("givenUserId_WhenGetUser_thenReturnUser_success: " + mvcResult.getResponse().getContentAsString());
    }


    @Test
    public void givenUserId_WhenDeleteUser_thenReturnVoid_success() throws Exception {
        //mock data
        User tayo = new User(1, "Eden", 1000, "Panther");

        userservice = mock(Userservice.class);
        doNothing().when(userservice).deletUserById(tayo.getId());


        MvcResult mvcResult = mvc.perform(delete("/users/deleteuserbyid/{id}", tayo.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        System.out.println("givenUserId_WhenGetUser_thenReturnUser_success: " + mvcResult.getResponse().getContentAsString());
    }

}