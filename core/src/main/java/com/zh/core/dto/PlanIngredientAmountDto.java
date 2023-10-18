package com.zh.core.dto;

import lombok.Data;

@Data
public class PlanIngredientAmountDto {
    private String ingredientId;
    private String name;
    private String image;
    private Double amount;
    private String unit;
}
