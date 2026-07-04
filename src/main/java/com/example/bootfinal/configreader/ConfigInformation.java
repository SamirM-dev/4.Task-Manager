package com.example.bootfinal.configreader;

import jakarta.annotation.PostConstruct;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Component
@ConfigurationProperties(prefix = "application")
@Validated
@Data
public class ConfigInformation {
    @NotBlank(message = "Отсутствует название приложения!")
    private String name;
    @NotBlank(message = "Отсутствует версия приложения!")
    private String version;
    @Min(value = 1,message = "Количество доступных задач меньше положенного")
    @Max(value =15,message = "Количество доступных задач больше положенного")
    private int tasksSize;
    @Email
    private String adminEmail;

    @PostConstruct
    public void init() {
        System.out.println("\n<======CONFIG INFORMATION======>");
        System.out.println("Application name : "+name+"-"+version);
        System.out.println("Maximum size : "+tasksSize+" tasks");
        System.out.println("Send questions to \""+adminEmail+"\" mail");
        System.out.println("<==============================>\n");
    }
}
