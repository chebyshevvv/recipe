package com.zh.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zh.core.model.Food;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FoodMapper extends BaseMapper<Food> {
}
