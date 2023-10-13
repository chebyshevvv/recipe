package com.zh.core.repository;

import com.zh.core.model.Ingredient;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class IngredientRepository {
    private final List<Ingredient> list = new ArrayList<>();
    public List<Ingredient> list(){
        return list;
    }
    public Ingredient getById(String id){
        return  list.stream().filter(r -> r.getId().equalsIgnoreCase(id)).findAny().get();
    }
}
