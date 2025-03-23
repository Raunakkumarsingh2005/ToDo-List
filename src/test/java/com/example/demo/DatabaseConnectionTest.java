//package com.example.demo;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import javax.sql.DataSource;
//import java.sql.Connection;
//import java.sql.SQLException;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.test.context.junit4.SpringRunner;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class DatabaseConnectionTest {
//
//    @Autowired
//    private DataSource dataSource;
//
//    @Test
//    public void testDatabaseConnection() {
//        try (Connection connection = dataSource.getConnection()) {
//            System.out.println("Database connection is successful");
//        } catch (SQLException e) {
//            System.out.println("Database connection failed");
//            e.printStackTrace();
//        }
//    }
//}
