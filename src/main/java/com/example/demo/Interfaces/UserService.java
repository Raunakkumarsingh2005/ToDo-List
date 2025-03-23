package com.example.demo.Interfaces;

import com.example.demo.model.User;

import java.util.List;

public interface UserService {
    User createUser(User user);
    User getUserById(Long id);
    List<User> getAllUser();
    User updateUser(Long id,User user);
    void deleteUserById(Long id);
}
