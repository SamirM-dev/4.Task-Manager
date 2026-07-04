package com.example.bootfinal.dtorequest;

import com.example.bootfinal.enums.TaskStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
public class UpdateTaskRequest {

    private String title;
    private String description;
    private TaskStatus status;

}
