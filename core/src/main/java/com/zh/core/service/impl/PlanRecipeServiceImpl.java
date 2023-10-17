package com.zh.core.service.impl;

import cn.hutool.core.util.IdUtil;
import com.zh.core.dto.PlanRecipeQueryResultDto;
import com.zh.core.dto.PlanRecipeQueryParamDto;
import com.zh.core.model.PlanRecipe;
import com.zh.core.model.Recipe;
import com.zh.core.repository.PlanRecipeRepository;
import com.zh.core.repository.RecipeRepository;
import com.zh.core.service.PlanRecipeService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlanRecipeServiceImpl implements PlanRecipeService {
    private final PlanRecipeRepository repository;
    private final RecipeRepository recipeRepository;

    public PlanRecipeServiceImpl(PlanRecipeRepository repository, RecipeRepository recipeRepository) {
        this.repository = repository;
        this.recipeRepository = recipeRepository;
    }

    @Override
    public List<PlanRecipeQueryResultDto> list(PlanRecipeQueryParamDto dto) {
        List<PlanRecipe> list = this.repository.list(dto.getDate());
        List<PlanRecipeQueryResultDto> resultList = new ArrayList<>(list.size());
        for (PlanRecipe plan : list) {
            PlanRecipeQueryResultDto result = new PlanRecipeQueryResultDto();
            result.setId(plan.getId());
            result.setRecipeId(plan.getRecipeId());
            result.setDate(plan.getDate());
            result.setMeal(plan.getMeal());
            Recipe recipe = this.recipeRepository.getById(plan.getRecipeId());
            result.setRecipeName(recipe.getName());
            result.setRecipeDescription(recipe.getDescription());
            result.setRecipeImage(recipe.getImage());
            resultList.add(result);
        }
        return resultList;
    }

    @Override
    public void save(PlanRecipe recipe) {
        recipe.setId(IdUtil.getSnowflakeNextIdStr());
        this.repository.save(recipe);
    }
}
