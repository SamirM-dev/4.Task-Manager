package com.example.bootfinal.controller;

import com.example.bootfinal.dtorequest.CreateTaskRequest;
import com.example.bootfinal.entity.Task;
import com.example.bootfinal.service.TaskService;
import io.micrometer.core.ipc.http.HttpSender;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getById(@PathVariable Long id){
        Task task = taskService.findById(id);
        return ResponseEntity.ok(task);
    }

    @GetMapping()
    public ResponseEntity<List<Task>> getAll(@RequestParam(required = false) String status){
        List<Task> taskList = taskService.getAll(status);
        return ResponseEntity.ok(taskList);
    }

    @PostMapping
    public ResponseEntity<Task> create(@Valid @RequestBody CreateTaskRequest request){
        Task createdTask = taskService.createTask(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTask);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> update(@PathVariable Long id){

    }

    public delete(){}

}
