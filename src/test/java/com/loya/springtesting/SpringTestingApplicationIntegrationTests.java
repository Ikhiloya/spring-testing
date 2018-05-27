package com.loya.springtesting;

import com.loya.springtesting.entity.User;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static com.loya.springtesting.utils.Helper.asJSONObject;
import static com.loya.springtesting.utils.Helper.asJSONString;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Integration Test
 */

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = SpringTestingApplication.class
)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application.properties") //annotation to load the properties file
public class SpringTestingApplicationIntegrationTests {

    @Autowired
    MockMvc mockMvc;


    @Test
    public void contextLoads() throws Exception {

        MvcResult mvcResult = mockMvc.perform(

                get("/all/users")
                        .accept(MediaType.APPLICATION_JSON)
        ).andReturn();

        System.out.println("integrated test result " + mvcResult.getResponse());

    }


    /**
     * This integration test accesses persisted data on the database and returns a Json Array
     * Note that it fetches the data without any service or Dao. Access to database is done through the url
     */
    @Test
    public void givenUsersURI_whenMockMVC_thenReturnJsonArray() throws Exception {
        MvcResult mvcResult = this.mockMvc
                .perform(get("/users/all"))
                .andDo(print()).andExpect(status().isOk())
                .andReturn();

        System.out.println("integrated test result " + mvcResult.getResponse().getContentAsString());

    }


    @Test
    public void givenUserIdURI_getUser_whenMockMVC_thenReturnJsonObject() throws Exception {
        //post a user first
        //post a new user
        User faith = new User("Faith", 9700, "Black Crow");

        MvcResult mvcPostResult = this.mockMvc.perform(post("/users/adduser")
                .content(asJSONString(faith))
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        int id = 0;
        //converts the returned result of the post to a string
        String jsonString = mvcPostResult.getResponse().getContentAsString();

        //converts string to a jsonObject
        JSONObject obj = asJSONObject(jsonString);
        id = obj.getInt("id"); //gets the id from the JsonObject


        MvcResult mvcResult = this.mockMvc
                .perform(get("/users/getuserbyid/{id}", id))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.id", Matchers.is(id)))
                .andExpect(jsonPath("$.name", Matchers.is("Faith")))
                .andExpect(jsonPath("$.salary", Matchers.is(9700)))
                .andExpect(jsonPath("$.teamName", Matchers.is("Black Crow")))
                .andReturn();
        System.out.println("integrated test result " + mvcResult.getResponse().getContentAsString());
    }

    /**
     * This integration test posts a new User object to the database and verifies its response
     * Access to the Db is done through the url.
     * This returns a Json object as part of the response
     */
    @Test
    public void givenUserURIWithPost_whenMockMVC_thenVerifyResponse() throws Exception {
        //new User
        User tobi = new User("Mark", 9700, "Black Crow");

        MvcResult mvcResult = this.mockMvc.perform(post("/users/adduser")
                .content(asJSONString(tobi))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())

                .andReturn();
//                .equals(tobi);
        // .andExpect(jsonPath("$[4].name", Matchers.is("Mark")));
        System.out.println("integrated test result " + mvcResult.getResponse().getContentAsString());
    }


    @Test
    public void givenUserIdURI_deleteUser_whenMockMVC_thenReturnJsonObject() throws Exception {
        //post a new user
        User mark = new User("Mark", 9700, "Black Crow");

        MvcResult mvcPostResult = this.mockMvc.perform(post("/users/adduser")
                .content(asJSONString(mark))
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        int id = 0;
        //converts the returned result of the post to a string
        String jsonString = mvcPostResult.getResponse().getContentAsString();

        //converts string to a jsonObject
        JSONObject obj = asJSONObject(jsonString);
        id = obj.getInt("id"); //gets the id from the JsonObject
        MvcResult mvcResult = this.mockMvc
                .perform(delete("/users/deleteuserbyid/{id}", id))
                .andDo(print()).andExpect(status().isOk())
                .andReturn();
        System.out.println("integrated test result " + mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void givenUserURIWithPut_whenMockMVC_thenVerifyResponse() throws Exception {
        //new User
        User tobi = new User("Mark", 9700, "Black Crow");

        MvcResult mvcResult = this.mockMvc.perform(put("/users/updateuser")
                .content(asJSONString(tobi))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk()).andReturn();
//                .equals(tobi);
        // .andExpect(jsonPath("$[4].name", Matchers.is("Mark")));
        System.out.println("integrated test result " + mvcResult.getResponse().getContentAsString());
    }

}
