package com.zh.core.controller;

import com.zh.common.http.HttpResult;
import com.zh.core.model.Food;
import com.zh.core.service.FoodService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("food")
public class FoodController {
    private final FoodService service;

    public FoodController(FoodService service) {
        this.service = service;
    }
    @GetMapping("list")
    public HttpResult<List<Food>> list(Food food){
        return HttpResult.ok(service.list(food));
    }
    @PostMapping("save")
    public HttpResult<?> save(@RequestBody Food food){
        service.save(food);
        return HttpResult.ok();
    }
}
