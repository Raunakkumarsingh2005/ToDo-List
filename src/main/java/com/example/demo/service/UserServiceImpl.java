package com.example.demo.service;

import com.example.demo.Interfaces.UserService;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User updateUser(Long id, User user) {
        User existingUser = getUserById(id);
        if (existingUser != null) {
            existingUser.setName(user.getName());
            existingUser.setEmail(user.getEmail());
            existingUser.setPassword(user.getPassword());
            existingUser.setUsername(user.getUsername());
            existingUser.setTasks(user.getTasks());
        }
        assert existingUser != null;
        return userRepository.save(existingUser);
    }

    @Override
    public void deleteUserById(Long id) {
        User existingUser = getUserById(id);
        if (existingUser != null) {
            userRepository.deleteById(id);
        }
        assert existingUser != null : "User does not exists";
    }

//    @Autowired
//    private UserRepository userRepository;
//
//    public User CreateUser(User user) {
//
//    }
//
//    public User getUser(Long id) {
//        return userRepository.findById(id).orElse(null);
//    }
//
//    public User updateUser(User user) {
//
//    }
//
//    public void deleteUser(Long id) {
//        userRepository.deleteById(id);
//    }
}
