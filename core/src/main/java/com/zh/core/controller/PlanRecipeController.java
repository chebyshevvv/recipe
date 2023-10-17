package com.zh.core.controller;

import com.zh.common.http.HttpResult;
import com.zh.core.dto.PlanRecipeQueryParamDto;
import com.zh.core.model.PlanRecipe;
import com.zh.core.service.PlanRecipeService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("plan/recipes")
public class PlanRecipeController {
    private final PlanRecipeService service;

    public PlanRecipeController(PlanRecipeService service) {
        this.service = service;
    }
    @GetMapping
    public HttpResult list(PlanRecipeQueryParamDto dto){
        return HttpResult.ok(this.service.list(dto));
    }
    @PostMapping("save")
    public HttpResult save(@RequestBody PlanRecipe recipe){
        this.service.save(recipe);
        return HttpResult.ok();
    }
}
