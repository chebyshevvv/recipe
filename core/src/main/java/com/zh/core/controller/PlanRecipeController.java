package com.zh.core.controller;

import cn.hutool.core.collection.CollectionUtil;
import com.zh.common.http.HttpResult;
import com.zh.core.dto.PlanRecipeQueryParamDto;
import com.zh.core.dto.PlanRecipeQueryResultDto;
import com.zh.core.model.PlanRecipe;
import com.zh.core.service.PlanRecipeService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

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
    @GetMapping("ingredients")
    public HttpResult todayIngredients(){
        return HttpResult.ok(this.service.todayIngredients());
    }
    @GetMapping("today")
    public HttpResult today(PlanRecipeQueryParamDto dto){
        List<PlanRecipeQueryResultDto> list = this.service.list(dto);
        if (CollectionUtil.isEmpty(list)){
            list = this.service.recommend();
        }
        return HttpResult.ok(list);
    }
}
