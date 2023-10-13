package com.zh.core.dto;

import lombok.Data;

import java.util.List;

@Data
public class RecipeDto {
    private String id;
    private String name;
    private String description;
    private String image;
    private List<RecipeIngredientDto> ingredients;
    private List<RecipeStepDto> steps;

    @Data
    public static class RecipeIngredientDto{
        private String id;
        private String ingredientId;
        private Double amount;
        private String unit;
    }

    @Data
    public static class RecipeStepDto{
        private String id;
        private String title;
        private String description;
        private String image;
    }
}
