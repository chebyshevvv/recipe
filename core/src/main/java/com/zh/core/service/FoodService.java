package com.zh.core.service;

import com.zh.core.model.Food;

import java.util.List;

public interface FoodService {
    void save(Food food);
    List<Food> list(Food food);
    void remove(String id);
}
