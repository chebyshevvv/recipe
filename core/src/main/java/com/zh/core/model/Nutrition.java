package com.zh.core.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Nutrition {
    private String id;
    private String ingredientId;
    private String name;
    private BigDecimal amount;
    private String unit;
}
