package com.example.bootfinal.entity;

import com.example.bootfinal.enums.TaskStatus;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;
@Getter
@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String description;
    @Enumerated(EnumType.STRING)
    private TaskStatus status;
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public Task(){}

    public Task(String title, String description) {
        this.title = title;
        this.description = description;
        this.status=TaskStatus.NEW;
        this.createdAt=LocalDateTime.now();
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTaskStatus(TaskStatus taskStatus) {
        this.status = taskStatus;
    }
}
