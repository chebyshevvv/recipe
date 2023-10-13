package com.zh.core.controller;

import com.zh.common.http.HttpResult;
import com.zh.core.dto.RecipeDto;
import com.zh.core.service.RecipeService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("recipe")
public class RecipeController {
    private final RecipeService service;

    public RecipeController(RecipeService service) {
        this.service = service;
    }

    @GetMapping("list")
    public HttpResult recipes(){
        return HttpResult.ok(this.service.list());
    }
    @GetMapping("{id}")
    public HttpResult recipes(@PathVariable String id){
        return HttpResult.ok(this.service.getById(id));
    }
    @PostMapping("add")
    public HttpResult add(@RequestBody RecipeDto dto){
        this.service.save(dto);
        return HttpResult.ok();
    }
}
