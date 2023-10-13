package com.zh.core.service.impl;

import com.zh.core.model.Ingredient;
import com.zh.core.repository.IngredientRepository;
import com.zh.core.service.IngredientService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class IngredientServiceImpl implements IngredientService {
    private final IngredientRepository repository;

    public IngredientServiceImpl(IngredientRepository repository) {
        this.repository = repository;
    }

    @Override
    public Map<String, List<Ingredient>> getMap() {
        return this.repository.list().stream().collect(Collectors.groupingBy(Ingredient::getType));
    }
}
