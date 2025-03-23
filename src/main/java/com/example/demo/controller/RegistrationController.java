//package com.example.demo.controller;
//
//import com.example.demo.forms.RegistrationForm;
//import com.example.demo.model.User;
//import com.example.demo.security.CustomValidationException;
//import com.example.demo.service.UserServiceImpl;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.validation.Valid;
//
//@RestController
//@RequestMapping("/api/registration")
//public class RegistrationController {
//
//    private UserServiceImpl userService;
//
//    @Autowired
//    public RegistrationController(UserServiceImpl userServuce) {
//        this.userService = userService;
//    }
//
//    @PostMapping("/register")
//    public ResponseEntity<String> registerUser(@Valid @RequestBody User user, BindingResult bindingResult) {
//        if (bindingResult.hasErrors()) {
//            throw new CustomValidationException(bindingResult.getAllErrors());
//        }
//        userService.registerUser(user);
//        return ResponseEntity.ok("User registered successfully");
//    }
//}
