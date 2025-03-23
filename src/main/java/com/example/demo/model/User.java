package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @NotEmpty(message = "Username is required")
//    @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters")
    private String username;

//    @NotEmpty(message = "Password is required")
//    @Size(min = 8, max = 40, message = "Password must be between 8 and 40 characters")
    @Column(nullable = false)
    private String password;

//    @Transient
//    @Column(nullable = false)
//    private String confirmPassword;

//    @NotEmpty(message = "Email is required")
//    @Email(message = "Invalid email")
    @Column(nullable = false)
    private String email;
    private String name;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Task> tasks;

}
