package com.zh.core.controller;

import cn.hutool.core.util.IdUtil;
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
    @GetMapping("{id}")
    public HttpResult recipes(@PathVariable String id){
        return HttpResult.ok(recipes.stream().filter(r->r.getId().equals(id)).findAny().get());
    }
    @PostMapping("add")
    public HttpResult add(@RequestBody Recipe recipe){
        recipe.setId(IdUtil.getSnowflakeNextIdStr());
        recipes.add(recipe);
        return HttpResult.ok();
    }
    @GetMapping("listByListId")
    public HttpResult listByListId(String listId){
        return HttpResult.ok(recipes);
    }
}
