package com.example.bootfinal.service;

import com.example.bootfinal.dtorequest.CreateTaskRequest;
import com.example.bootfinal.dtorequest.UpdateTaskRequest;
import com.example.bootfinal.entity.Task;
import com.example.bootfinal.enums.TaskStatus;
import com.example.bootfinal.repository.TaskRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class TaskService {
    private final TaskRepository taskRepository;
    public TaskService(TaskRepository taskRepository){
        this.taskRepository=taskRepository;
    }

    public List<Task> getAll(String status){
        log.debug("Запуск метода getAll() с аргументом status={}", status);
        List<Task> tasks ;
        try {
            if (status==null){
                tasks = taskRepository.findAll();
                log.info("Были получены все задачи");
                return tasks;
            }
            else {
                TaskStatus taskStatus = TaskStatus.valueOf(status);
                tasks= taskRepository.findByStatus(taskStatus);
                log.info("Были получены все задачи со статусом {}",taskStatus);
                return tasks;
            }
        }
        catch (Exception e){
            log.error("Ошибка при получении задач",e);
            throw new RuntimeException("Ошибка при получении задач",e);
        }
    }

    public Task findById(Long id){
        log.debug("Запуск метода findById() с аргументом id={}", id);
        if(id==null || id<0) {
            log.warn("Некорректно введённый id!");
            throw new IllegalArgumentException("Некорректно введённый id!");
        }
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isEmpty()){
            log.warn("Задачи с таким id нет!");
            throw new EntityNotFoundException("Задачи с таким id нет!");
        }
        try{
            Task found=optionalTask.get();
            log.info("Была найдена задача с id={}",id);
            return found;
        }
        catch (Exception e){
            log.error("Ошибка при получении задачи с id={}",id,e);
            throw new RuntimeException("Ошибка при получении задачи с id="+id,e);
        }
    }

    public void deleteById(Long id){
        log.debug("Запуск метода deleteById() с аргументом id={}", id);
        if(id==null || id<0) {
            log.warn("Некорректно введённый id!");
            throw new IllegalArgumentException("Некорректно введённый id!");
        }
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isEmpty()){
            log.warn("Задачи с таким id нет!");
            throw new EntityNotFoundException("Задачи с таким id нет!");
        }
        try {
            taskRepository.deleteById(id);
            log.info("Задача с id={} была удалена",id);
        }
        catch (Exception e){
                log.error("Ошибка при удалении задачи с id={}",id,e);
                throw new RuntimeException("Ошибка при удалении задачи с id="+id,e);
            }

    }

    public Task updateTask(Long id,UpdateTaskRequest request){
        log.debug("Запуск метода updateTask() с аргументом id={}", id);
        if(id==null || id<0) {
            log.warn("Некорректно введённый id!");
            throw new IllegalArgumentException("Некорректно введённый id!");
        }
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isEmpty()){
            log.warn("Задачи с таким id нет!");
            throw new EntityNotFoundException("Задачи с таким id нет!");
        }
        try {
            Task updated = optionalTask.get();
            updated.setTitle(request.getTitle());
            updated.setDescription(request.getDescription());
            updated.setTaskStatus(request.getStatus());
            updated=taskRepository.save(updated);
            log.info("Задача с id={} была обновлена",id);
            return updated;
        }
        catch (Exception e){
            log.error("Ошибка при обновлении задачи с id={}",id,e);
            throw new RuntimeException("Ошибка при обновлении задачи с id="+id,e);
        }

    }

    public Task createTask(CreateTaskRequest request){
        log.debug("Запуск метода createTask()");
        try {
            Task newTask = new Task(request.getTitle(), request.getDescription());
            newTask=taskRepository.save(newTask);
            log.info("Была создана задача {}({})",newTask.getTitle(),newTask.getId());
            return newTask;
        } catch (Exception e) {
            log.error("Ошибка при создании новой задачи",e);
            throw new RuntimeException("Ошибка при создании новой задачи",e);
        }

    }

}
