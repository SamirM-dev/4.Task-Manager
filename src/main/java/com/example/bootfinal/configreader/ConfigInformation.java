package com.example.bootfinal.configreader;

import jakarta.annotation.PostConstruct;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Component
@PropertySource("application")
@Validated
public class ConfigInformation {
    @NotBlank(message = "Отсутствует название приложения!")
    private String name;
    @NotBlank(message = "Отсутствует версия приложения!")
    private String version;
    @Size(min = 1,max = 15,message = "Количество задач выходит за рамки дозволенного!")
    private int tasksSize;
    @Email
    private String adminEmail;

    @PostConstruct
    public void init() {
        System.out.println("<======CONFIG INFORMATION======>");
        System.out.println("Application name : "+name+"-"+version);
        System.out.println("Maximum size : "+tasksSize+" tasks");
        System.out.println("Send questions to \""+adminEmail+"\" mail");
        System.out.println("<==============================>");
    }
}
