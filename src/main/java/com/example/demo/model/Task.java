package com.example.demo.model;

import com.example.demo.constants.Status;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.springframework.boot.autoconfigure.web.WebProperties;

import java.time.LocalDate;
import java.util.Date;

@Data
@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long taskId;
    private String description;

    @Column(nullable = false)
    private String title;

    private LocalDate dueDate;
    
    @Column(nullable = false)
    private Status status;

    @Column(nullable = false, updatable = false)
    private LocalDate created_At;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference  // Prevents infinite recursion . "This annotation solved the bug of infinite recursion between users and tasks refer this chat of chatgpt https://chatgpt.com/share/67e3c7a5-aad8-800e-9365-cf33e408d877 "
    private User user;

}
