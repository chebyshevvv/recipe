package com.zh.core.repository;

import com.zh.core.model.RecipeStep;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RecipeStepRepository {
    private final List<RecipeStep> list = new ArrayList<>();
    public List<RecipeStep> listByRecipeId(String recipeId){
        return list.stream().filter(r -> r.getRecipeId().equalsIgnoreCase(recipeId)).toList();
    }
    public void save(RecipeStep step){
        list.add(step);
    }
    public void saveBatch(List<RecipeStep> steps){
        list.addAll(steps);
    }
}
