package com.zh.core.service;

import com.zh.core.model.Ingredient;

import java.util.List;
import java.util.Map;

public interface IngredientService {
    Map<String, List<Ingredient>> getMap();
}
