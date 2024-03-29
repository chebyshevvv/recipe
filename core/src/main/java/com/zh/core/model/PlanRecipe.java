package com.zh.core.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PlanRecipe {
    private String id;
    private String recipeId;
    private LocalDate date;
    private Integer meal;
}
