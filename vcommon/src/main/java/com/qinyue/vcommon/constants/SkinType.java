package com.qinyue.vcommon.constants;

/**
 * @ClassName: SkinType
 * @Description:
 * @Author: bodia
 * @Date: 2022/7/7
 */
public enum SkinType {
    NORMAL("normal"),NIGHT("night");
    private String value;

    SkinType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
