package com.zh.core.service.impl;

import cn.hutool.core.util.IdUtil;
import com.zh.core.dto.PlanIngredientAmountDto;
import com.zh.core.dto.PlanRecipeQueryResultDto;
import com.zh.core.dto.PlanRecipeQueryParamDto;
import com.zh.core.model.Ingredient;
import com.zh.core.model.PlanRecipe;
import com.zh.core.model.Recipe;
import com.zh.core.model.RecipeIngredient;
import com.zh.core.repository.IngredientRepository;
import com.zh.core.repository.PlanRecipeRepository;
import com.zh.core.repository.RecipeIngredientRepository;
import com.zh.core.repository.RecipeRepository;
import com.zh.core.service.PlanRecipeService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PlanRecipeServiceImpl implements PlanRecipeService {
    private final PlanRecipeRepository repository;
    private final RecipeRepository recipeRepository;
    private final RecipeIngredientRepository recipeIngredientRepository;
    private final IngredientRepository ingredientRepository;

    public PlanRecipeServiceImpl(PlanRecipeRepository repository, RecipeRepository recipeRepository, RecipeIngredientRepository recipeIngredientRepository, IngredientRepository ingredientRepository) {
        this.repository = repository;
        this.recipeRepository = recipeRepository;
        this.recipeIngredientRepository = recipeIngredientRepository;
        this.ingredientRepository = ingredientRepository;
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
    public List<PlanRecipeQueryResultDto> recommend() {
        List<Recipe> recipes = this.recipeRepository.list();
        List<PlanRecipeQueryResultDto> resultList = new ArrayList<>(recipes.size());
        for (Recipe recipe : recipes) {
            PlanRecipeQueryResultDto result = new PlanRecipeQueryResultDto();
            result.setRecipeId(recipe.getId());
            result.setMeal(0);
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

    @Override
    public List<PlanIngredientAmountDto> todayIngredients() {
        List<PlanIngredientAmountDto> list = new ArrayList<>();
        List<PlanRecipe> planList = this.repository.list(LocalDate.now());
        List<RecipeIngredient> ingredientList = new ArrayList<>();
        for (PlanRecipe plan : planList) {
            List<RecipeIngredient> ingredients = this.recipeIngredientRepository.listByRecipeId(plan.getRecipeId());
            ingredientList.addAll(ingredients);
        }
        Map<String, List<RecipeIngredient>> map = ingredientList.stream().collect(Collectors.groupingBy(RecipeIngredient::getIngredientId));
        map.forEach(
                (k,v) -> {
                    PlanIngredientAmountDto result = new PlanIngredientAmountDto();
                    Ingredient ingredient = this.ingredientRepository.getById(k);
                    result.setIngredientId(k);
                    result.setName(ingredient.getName());
                    result.setImage(ingredient.getImage());
                    double amount = v.stream().map(RecipeIngredient::getAmount).map(BigDecimal::valueOf).reduce(BigDecimal.ZERO, BigDecimal::add).doubleValue();
                    result.setAmount(amount);
                    result.setUnit(v.get(0).getUnit());
                    list.add(result);
                }
        );
        return list;
    }
}
