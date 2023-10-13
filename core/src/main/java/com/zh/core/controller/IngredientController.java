package com.zh.core.controller;

import com.zh.common.http.HttpResult;
import com.zh.core.service.IngredientService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
}
