package com.zh.core.controller;

import com.zh.common.http.HttpResult;
import com.zh.core.service.IngredientService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("ingredient")
public class IngredientController {
    private final IngredientService service;

    public IngredientController(IngredientService service) {
        this.service = service;
    }

    @GetMapping("map")
    public HttpResult recipes(){
        return HttpResult.ok(this.service.getMap());
    }
    @PostMapping
    public HttpResult test(@RequestBody Map list){
        this.service.save(list);
        return HttpResult.ok();
    }
}
