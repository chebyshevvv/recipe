package com.zh.core.repository;

import com.zh.core.model.Recipe;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RecipeRepository {
    private final List<Recipe> list = new ArrayList<>();
    public List<Recipe> list(){
        return list;
    }
    public Recipe getById(String id){
        return  list.stream().filter(r -> r.getId().equalsIgnoreCase(id)).findAny().get();
    }
    public void save(Recipe recipe){
        list.add(recipe);
    }
}
