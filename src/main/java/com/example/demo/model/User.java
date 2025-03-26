package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference  // Prevents infinite recursion. "// Prevents infinite recursion . "This annotation solved the bug of infinite recursion between users and tasks refer this chat of chatgpt https://chatgpt.com/share/67e3c7a5-aad8-800e-9365-cf33e408d877 "
    private List<Task> tasks;

}
