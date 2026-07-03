package com.example.bootfinal.service;

import com.example.bootfinal.dtorequest.CreateTaskRequest;
import com.example.bootfinal.entity.Task;
import com.example.bootfinal.enums.TaskStatus;
import com.example.bootfinal.repository.TaskRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    public TaskService(TaskRepository taskRepository){
        this.taskRepository=taskRepository;
    }

    public List<Task> getAll(String status){
        if (status==null){
            return taskRepository.findAll();
        }
        else {
            TaskStatus taskStatus = TaskStatus.valueOf(status);
            return taskRepository.findByStatus(taskStatus);
        }
    }

    public Task findById(Long id){
        if(id==null || id<0) {
            throw new IllegalArgumentException("Некорреткно введённый айди!");
        }
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isEmpty()){
            throw new EntityNotFoundException("Задачи с таким айди нет!");
        }
        return optionalTask.get();
    }

    public void deleteById(Long id){
        if(id==null || id<0) {
            throw new IllegalArgumentException("Некорреткно введённый айди!");
        }
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isEmpty()){
            throw new EntityNotFoundException("Задачи с таким айди нет!");
        }
        taskRepository.deleteById(id);
    }

    public void updateTask(){}

    public Task createTask(CreateTaskRequest request){
        Task newTask = new Task(request.getTitle(), request.getDescription());
        return taskRepository.save(newTask);
    }

}
