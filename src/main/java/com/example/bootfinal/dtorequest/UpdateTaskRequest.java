package com.example.bootfinal.dtorequest;

import com.example.bootfinal.enums.TaskStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UpdateTaskRequest {

    @NotBlank(message = "Необходимо заполнить имя!")
    private String title;
    @Size(max=500,message = "Описание не должно превышать 500 символов!")
    private String description;
    private TaskStatus status;

}
