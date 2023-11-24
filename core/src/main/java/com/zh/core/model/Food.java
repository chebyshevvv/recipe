package com.zh.core.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Food {
    private String id;
    private String name;
    private String description;
    private String image;
    private Integer star;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timeLimit;
}
