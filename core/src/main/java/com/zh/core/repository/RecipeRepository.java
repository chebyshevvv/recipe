package com.zh.core.repository;

import cn.hutool.core.util.IdUtil;
import com.zh.core.model.Recipe;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RecipeRepository {
    private final List<Recipe> list = new ArrayList<>();
    {
        for (int i = 0; i < 9; i++) {
            Recipe recipe = new Recipe();
            recipe.setId("123456789");
            recipe.setName("测试");
            recipe.setDescription("测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试");
            recipe.setImage("20231011141758th.jpg");
            list.add(recipe);
        }
    }
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
