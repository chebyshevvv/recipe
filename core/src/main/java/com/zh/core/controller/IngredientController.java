package com.zh.core.controller;

import cn.hutool.core.util.IdUtil;
import com.zh.common.http.HttpResult;
import com.zh.core.model.Ingredient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("ingredient")
public class IngredientController {
    @GetMapping("map")
    public HttpResult recipes(){
        Map<String, List<Ingredient>> map = new HashMap<>();
        for (int i = 0; i < 5; i++) {
            List<Ingredient> list = new ArrayList<>();
            String type = IdUtil.getSnowflakeNextIdStr();
            String typeName = "xx" + i;
            map.put(typeName,list);
            for (int j = 0; j < 5; j++) {
                Ingredient ingredient = new Ingredient();
                ingredient.setId(IdUtil.getSnowflakeNextIdStr());
                ingredient.setName("dd" + i + j);
                ingredient.setType(type);
                ingredient.setTypeName(typeName);
                ingredient.setDescription("ss");
                ingredient.setImage("images/th.jpg");
                list.add(ingredient);
            }
        }
        return HttpResult.ok(map);
    }
}
