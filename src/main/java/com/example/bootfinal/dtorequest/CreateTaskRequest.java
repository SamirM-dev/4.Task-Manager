package com.example.bootfinal.dtorequest;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;

public class CreateTaskRequest {
    @NotBlank(message = "Необходимо заполнить имя!")
    private String title;
    @Size(max=500,message = "Описание не должно превышать 500 символов!")
    private String description;

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
