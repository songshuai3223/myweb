package com.cn.my.common.starter.enums;

import io.swagger.annotations.ApiModel;

/**
 * 状态
 *
 * @author biren
 */
@ApiModel("状态枚举")
public enum ServiceStateEnum {
    /**
     * 已停用
     */
    STOP,
    /**
     * 待启用
     */
    WAIT,
    /**
     * 启用中
     */
    WORK,
    /**
     * 已弃用
     */
    DISCARD,
    /**
     * 已删除
     */
    DELETE;

    @Override
    public String toString() {
        return this.name();
    }
}
