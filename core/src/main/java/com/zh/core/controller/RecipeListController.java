package com.zh.core.controller;

import com.zh.common.http.HttpResult;
import com.zh.core.model.Recipe;
import com.zh.core.model.RecipeList;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("lists")
public class RecipeListController {
    private final List<RecipeList> lists = new ArrayList<>();

    @GetMapping
    public HttpResult recipes(){
        return HttpResult.ok(lists);
    }
    @PostMapping("add")
    public HttpResult add(@RequestBody RecipeList list){
        lists.add(list);
        return HttpResult.ok();
    }
}
