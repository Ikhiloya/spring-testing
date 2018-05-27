package com.loya.springtesting.service;

import com.loya.springtesting.dao.UserRepository;

import com.loya.springtesting.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Userservice {
    @Autowired
    UserRepository userRepository;

    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    public User adNewUser(User user) {

        return this.userRepository.save(user);
    }

    public Optional<User> getUserById(int id) {
        return this.userRepository.findById(id);
    }

    public void deletUserById(int id) {
        this.userRepository.deleteById(id);
    }

    public User updateUser(User user) {
        return this.userRepository.save(user);
    }
    //findBy_Methods


}
