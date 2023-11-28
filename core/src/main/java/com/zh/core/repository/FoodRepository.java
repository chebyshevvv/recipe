package com.zh.core.repository;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zh.core.mapper.FoodMapper;
import com.zh.core.model.Food;
import org.springframework.stereotype.Component;

@Component
public class FoodRepository extends ServiceImpl<FoodMapper, Food> implements IService<Food> {
}
