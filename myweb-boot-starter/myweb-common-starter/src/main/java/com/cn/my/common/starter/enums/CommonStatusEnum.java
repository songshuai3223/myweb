package com.cn.my.common.starter.enums;

/**
 * 状态枚举
 *
 * @author songshuai
 */
public enum CommonStatusEnum {

    /**
     * 禁用
     */
    DISABLE(2),
    /**
     * 正常
     */
    NORMAL(1),
    /**
     * 删除
     */
    DELETE(0);

    private final int value;

    CommonStatusEnum(final int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
