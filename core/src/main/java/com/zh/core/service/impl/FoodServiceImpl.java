package com.zh.core.service.impl;

import cn.hutool.core.lang.Validator;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.zh.core.model.Food;
import com.zh.core.repository.FoodRepository;
import com.zh.core.service.FoodService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FoodServiceImpl implements FoodService {
    private final FoodRepository repository;

    public FoodServiceImpl(FoodRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(Food food) {
        Validator.validateNotEmpty(food.getName(),"");

        food.setCreateTime(LocalDateTime.now());
        repository.save(food);

    }

    @Override
    public List<Food> list(Food food) {
        QueryWrapper<Food> wrapper = Wrappers.query(food);
        wrapper.orderByDesc("time_limit","create_time");
        return repository.list(wrapper);
    }
}
