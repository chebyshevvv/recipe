package com.zh.core.service;

import com.zh.core.dto.PlanIngredientAmountDto;
import com.zh.core.dto.PlanRecipeQueryParamDto;
import com.zh.core.dto.PlanRecipeQueryResultDto;
import com.zh.core.model.PlanRecipe;

import java.util.List;

public interface PlanRecipeService {
    List<PlanRecipeQueryResultDto> list(PlanRecipeQueryParamDto dto);
    void save(PlanRecipe recipe);
    List<PlanIngredientAmountDto> todayIngredients();
}
