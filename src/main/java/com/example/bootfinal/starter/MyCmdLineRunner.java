package com.example.bootfinal.starter;

import com.example.bootfinal.dtorequest.CreateTaskRequest;
import com.example.bootfinal.service.TaskService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class MyCmdLineRunner implements CommandLineRunner {
    private final TaskService taskService;
    private final Environment environment;
    CreateTaskRequest createTaskRequest = new CreateTaskRequest();

    public MyCmdLineRunner(TaskService taskService, Environment environment) {
        this.taskService = taskService;
        this.environment = environment;
    }

    @Override
    public void run(String... args) throws Exception {
        for (int i = 1; i <4; i++) {
            createTaskRequest.setTitle("Task #"+i);
            createTaskRequest.setDescription("Description for Task #"+i);
            taskService.createTask(createTaskRequest);
        }
    }
}
