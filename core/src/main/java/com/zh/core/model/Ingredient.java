package com.zh.core.model;

import lombok.Data;

@Data
public class Ingredient {
    private String id;
    private String name;
    private String typeId;
    private String typeName;
    private String description;
    private String image;
}
