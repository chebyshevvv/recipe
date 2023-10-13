package com.zh.core.repository;

import cn.hutool.core.util.IdUtil;
import com.zh.core.model.Ingredient;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class IngredientRepository {
    private final List<Ingredient> list = new ArrayList<>();
    {
        for (int i = 0; i < 5; i++) {
            String type = IdUtil.getSnowflakeNextIdStr();
            String typeName = "xx" + i;
            for (int j = 0; j < 5; j++) {
                Ingredient ingredient = new Ingredient();
                ingredient.setId(i + "" + j);
                ingredient.setName("dd" + i + j);
                ingredient.setType(type);
                ingredient.setTypeName(typeName);
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
