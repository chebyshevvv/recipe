package com.zh.core.model;

import lombok.Data;

@Data
public class RecipeIngredient {
    private String id;
    private String recipeId;
    private String ingredientId;
    private Double amount;
    private String unit;
}
