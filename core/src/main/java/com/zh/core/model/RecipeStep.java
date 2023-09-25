package com.zh.core.model;

import lombok.Data;

@Data
public class RecipeStep {
    private String id;
    private String recipeId;
    private String title;
    private String description;
    private String image;
}
