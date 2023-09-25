package com.zh.common.constant;

import lombok.Getter;

@Getter
public enum BooleanEnum {

    TRUE((byte) 1,true),

    FALSE((byte) 0,false);

    private final byte code;
    private final boolean value;

    BooleanEnum(byte code, boolean value) {
        this.code = code;
        this.value = value;
    }
}
