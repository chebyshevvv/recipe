package com.zh.core.repository;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zh.core.mapper.IngredientMapper;
import com.zh.core.model.Ingredient;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class IngredientRepository extends ServiceImpl<IngredientMapper,Ingredient> implements IService<Ingredient> {
    private final List<Ingredient> list = new ArrayList<>();
    {
        for (int i = 0; i < 5; i++) {
            String type = "xx" + i;
            for (int j = 0; j < 5; j++) {
                Ingredient ingredient = new Ingredient();
                ingredient.setId(i + "" + j);
                ingredient.setName("dd" + i + j);
                ingredient.setType(type);
                ingredient.setDescription("sdfasdfasd");
                ingredient.setImage("20231011141758th.jpg");
                list.add(ingredient);
            }
        }
    }
    public List<Ingredient> list(){
        return list;
    }
    public Ingredient getById(String id){
        return  list.stream().filter(r -> r.getId().equalsIgnoreCase(id)).findAny().get();
    }
}
