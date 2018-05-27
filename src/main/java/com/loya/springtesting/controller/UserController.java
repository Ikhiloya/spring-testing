package com.loya.springtesting.controller;

import com.loya.springtesting.dao.UserRepository;
import com.loya.springtesting.entity.User;
import com.loya.springtesting.service.Userservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    Userservice userservice;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<User> all() {
        return userservice.getAllUsers();
    }


    @RequestMapping(value = "/adduser", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody()
    public User addNewUser(@RequestBody User user) {
        return this.userservice.adNewUser(user);
    }

    @RequestMapping(value = "/getuserbyid/{id}", method = RequestMethod.GET)
    public Optional<User> getUserById(@PathVariable("id") int id){
        return this.userservice.getUserById(id);
    }

    @RequestMapping(value = "/deleteuserbyid/{id}", method = RequestMethod.DELETE)
    public void deleteUserById(@PathVariable("id") int id){
        this.userservice.deletUserById(id);
//        String url ="/deleteuserbyid/" + id;
//        return
    }

    @RequestMapping(value = "/updateuser", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody()
    public User updateUser(@RequestBody User user) {
        return this.userservice.updateUser(user);
    }

}
