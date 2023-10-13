package com.zh.core.service;

import com.zh.core.dto.RecipeDto;
import com.zh.core.model.Recipe;

import java.util.List;

public interface RecipeService {
    void save(RecipeDto dto);
    RecipeDto getById(String id);
    List<Recipe> list();
}
