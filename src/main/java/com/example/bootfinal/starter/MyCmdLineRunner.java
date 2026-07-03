package com.example.bootfinal.starter;

import com.example.bootfinal.dtorequest.CreateTaskRequest;
import com.example.bootfinal.service.TaskService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MyCmdLineRunner implements CommandLineRunner {
    private final TaskService taskService;
    public MyCmdLineRunner(TaskService taskService) {
        this.taskService = taskService;
    }
    @Override
    public void run(String... args) throws Exception {
        for (int i = 1; i <4; i++) {
            taskService.createTask(new CreateTaskRequest("Task #"+i,"Description for task #"+i));
        }
    }
}
