package com.zh.core.repository;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zh.core.mapper.NutritionMapper;
import com.zh.core.model.Nutrition;
import org.springframework.stereotype.Component;

@Component
public class NutritionRepository extends ServiceImpl<NutritionMapper,Nutrition> implements IService<Nutrition> {
}
