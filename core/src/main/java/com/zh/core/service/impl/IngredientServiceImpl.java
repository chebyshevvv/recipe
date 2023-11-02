package com.zh.core.service.impl;

import cn.hutool.core.util.IdUtil;
import com.zh.core.model.Ingredient;
import com.zh.core.model.Nutrition;
import com.zh.core.repository.IngredientRepository;
import com.zh.core.repository.NutritionRepository;
import com.zh.core.service.IngredientService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class IngredientServiceImpl implements IngredientService {
    private final IngredientRepository repository;
    private final NutritionRepository nutritionRepository;

    public IngredientServiceImpl(IngredientRepository repository, NutritionRepository nutritionRepository) {
        this.repository = repository;
        this.nutritionRepository = nutritionRepository;
    }

    @Override
    public Map<String, List<Ingredient>> getMap() {
        return this.repository.list().stream().collect(Collectors.groupingBy(Ingredient::getType));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(Map map) {
        List<Map> list = (List<Map>) map.get("FoundationFoods");
        List<Ingredient> ingredientList = new ArrayList<>();
        List<Nutrition> nutritionList = new ArrayList<>();
        for (Map m : list) {
            Ingredient ingredient = new Ingredient();
            ingredient.setId(IdUtil.getSnowflakeNextIdStr());
            ingredient.setName(String.valueOf(m.get("description")));
            ingredient.setType(String.valueOf(((Map) m.get("foodCategory")).get("description")));
            ingredient.setDescription(String.valueOf(m.get("description")));
            List<Map> nutrients = (List<Map>) m.get("foodNutrients");
            ingredientList.add(ingredient);
            for (Map n : nutrients) {
                Map nutrient = (Map) n.get("nutrient");
                Nutrition nutrition = new Nutrition();
                nutrition.setId(IdUtil.getSnowflakeNextIdStr());
                nutrition.setIngredientId(ingredient.getId());
                nutrition.setName(String.valueOf(nutrient.get("name")));
                if (n.containsKey("amount"))
                nutrition.setAmount(new BigDecimal(String.valueOf(n.get("amount"))));
                nutrition.setUnit(String.valueOf(nutrient.get("unitName")));
                nutritionList.add(nutrition);
            }
        }
        this.repository.saveBatch(ingredientList);
        this.nutritionRepository.saveBatch(nutritionList);
    }
}
