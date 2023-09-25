package com.zh.common.dto;

import lombok.Data;

import java.util.List;

@Data
public class CheckDto {
    private List<String> checkIds;
    private Byte checkStatus;
    private String checkOpinion;
}
