package com.zh.core.service.impl;

import cn.hutool.core.util.IdUtil;
import com.zh.core.dto.RecipeDto;
import com.zh.core.model.Recipe;
import com.zh.core.model.RecipeIngredient;
import com.zh.core.model.RecipeStep;
import com.zh.core.repository.RecipeIngredientRepository;
import com.zh.core.repository.RecipeRepository;
import com.zh.core.repository.RecipeStepRepository;
import com.zh.core.service.RecipeService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecipeServiceImpl implements RecipeService {
    private final RecipeRepository repository;
    private final RecipeIngredientRepository recipeIngredientRepository;
    private final RecipeStepRepository stepRepository;

    public RecipeServiceImpl(RecipeRepository repository, RecipeIngredientRepository recipeIngredientRepository, RecipeStepRepository stepRepository) {
        this.repository = repository;
        this.recipeIngredientRepository = recipeIngredientRepository;
        this.stepRepository = stepRepository;
    }

    @Override
    public void save(RecipeDto dto) {
        Recipe recipe = new Recipe();
        recipe.setId(IdUtil.getSnowflakeNextIdStr());
        recipe.setName(dto.getName());
        recipe.setDescription(dto.getDescription());
        recipe.setImage(dto.getImage());

        List<RecipeIngredient> ingredientList = new ArrayList<>(dto.getIngredients().size());
        for (RecipeDto.RecipeIngredientDto ingredient : dto.getIngredients()) {
            RecipeIngredient recipeIngredient = new RecipeIngredient();
            recipeIngredient.setId(IdUtil.getSnowflakeNextIdStr());
            recipeIngredient.setRecipeId(recipe.getId());
            recipeIngredient.setIngredientId(ingredient.getIngredientId());
            recipeIngredient.setAmount(ingredient.getAmount());
            recipeIngredient.setUnit(ingredient.getUnit());
            ingredientList.add(recipeIngredient);
        }

        List<RecipeStep> stepList = new ArrayList<>(dto.getSteps().size());
        for (RecipeDto.RecipeStepDto step : dto.getSteps()) {
            RecipeStep recipeStep = new RecipeStep();
            recipeStep.setId(IdUtil.getSnowflakeNextIdStr());
            recipeStep.setRecipeId(recipe.getId());
            recipeStep.setTitle(step.getTitle());
            recipeStep.setDescription(step.getDescription());
            recipeStep.setImage(step.getImage());
            stepList.add(recipeStep);
        }

        this.repository.save(recipe);
        this.recipeIngredientRepository.saveBatch(ingredientList);
        this.stepRepository.saveBatch(stepList);
    }

    @Override
    public RecipeDto getById(String id) {
        Recipe recipe = this.repository.getById(id);
        List<RecipeIngredient> ingredientList = this.recipeIngredientRepository.listByRecipeId(id);
        List<RecipeStep> stepList = this.stepRepository.listByRecipeId(id);

        RecipeDto dto = new RecipeDto();
        dto.setId(recipe.getId());
        dto.setName(recipe.getName());
        dto.setDescription(recipe.getDescription());
        dto.setImage(recipe.getImage());
        dto.setIngredients(ingredientList.stream()
                .map(i -> {
                    RecipeDto.RecipeIngredientDto d = new RecipeDto.RecipeIngredientDto();
                    d.setId(i.getId());
                    d.setIngredientId(i.getIngredientId());
                    d.setAmount(i.getAmount());
                    d.setUnit(i.getUnit());
                    return d;
                }).toList()
        );
        dto.setSteps(stepList.stream()
                .map(i -> {
                    RecipeDto.RecipeStepDto d = new RecipeDto.RecipeStepDto();
                    d.setId(i.getId());
                    d.setTitle(i.getTitle());
                    d.setDescription(i.getDescription());
                    d.setImage(i.getImage());
                    return d;
                }).toList()
        );

        return dto;
    }

    @Override
    public List<Recipe> list() {
        return this.repository.list();
    }
}
