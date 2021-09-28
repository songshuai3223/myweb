package com.cn.my.common.starter.enums;

import io.swagger.annotations.ApiModel;

/**
 * 字段类型
 *
 * @author songshuai
 * @date 2021/3/2-17:59
 * @Description
 */
@ApiModel("字段状态枚举")
public enum ColumnTypeEnum {

    /**
     * 输入
     */
    IN,
    /**
     * 输出
     */
    OUT;
}
