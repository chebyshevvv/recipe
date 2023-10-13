package com.zh.core.repository;

import com.zh.core.model.RecipeIngredient;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RecipeIngredientRepository {
    private final List<RecipeIngredient> list = new ArrayList<>();
    public List<RecipeIngredient> listByRecipeId(String recipeId){
        return list.stream().filter(r -> r.getRecipeId().equalsIgnoreCase(recipeId)).toList();
    }
    public void save(RecipeIngredient ingredient){
        list.add(ingredient);
    }
    public void saveBatch(List<RecipeIngredient> ingredients){
        list.addAll(ingredients);
    }
}
