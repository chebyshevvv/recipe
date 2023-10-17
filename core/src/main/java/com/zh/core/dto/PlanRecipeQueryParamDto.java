package com.zh.core.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PlanRecipeQueryParamDto {
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
}
