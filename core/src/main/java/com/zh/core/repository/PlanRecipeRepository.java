package com.zh.core.repository;

import com.zh.core.model.PlanRecipe;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class PlanRecipeRepository {
    private final List<PlanRecipe> list = new ArrayList<>();
    public PlanRecipe today(){
        return list.stream().filter(p -> p.getDate().equals(LocalDate.now())).findAny().get();
    }
}
