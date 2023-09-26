package com.zh.core.controller;

import com.zh.common.http.HttpResult;
import com.zh.core.model.Recipe;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping("add")
    public HttpResult add(@RequestBody Recipe recipe){
        recipes.add(recipe);
        return HttpResult.ok();
    }
}
