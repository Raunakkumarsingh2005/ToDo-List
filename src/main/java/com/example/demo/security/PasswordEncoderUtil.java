//package com.example.demo.security;
//
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//public class PasswordEncoderUtil {
//    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//
//    public static String encodePassword(String password) {
//        return passwordEncoder.encode(password);
//    }
//
//    public static boolean isPasswordValid(String rawPassword, String encodedPassword) {
//        return passwordEncoder.matches(rawPassword, encodedPassword);
//    }
//}
