package com.example.demo.model;

import com.example.demo.constants.Status;
import jakarta.persistence.*;
import lombok.Data;
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
    private User user;

}
