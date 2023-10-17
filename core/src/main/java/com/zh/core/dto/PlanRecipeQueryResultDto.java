package com.zh.core.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PlanRecipeQueryResultDto {
    private String id;
    private String recipeId;
    private LocalDate date;
    private Integer meal;
    private String recipeName;
    private String recipeDescription;
    private String recipeImage;
}
