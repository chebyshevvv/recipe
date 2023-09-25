package com.zh.core.controller;

import cn.hutool.core.util.IdUtil;
import com.zh.common.http.HttpResult;
import com.zh.core.model.Recipe;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("recipe")
public class RecipeController {
    private final List<Recipe> recipes = new ArrayList<>();
    @GetMapping("list")
    public HttpResult recipes(){
        return HttpResult.ok(recipes);
    }
    @GetMapping("add")
    public HttpResult add(@RequestBody Recipe recipe){
        recipes.add(recipe);
        return HttpResult.ok();
    }
}
