package com.cn.my.common.starter.enums;

/**
 * @author songshuai
 * @date 2021/5/8-14:37
 * @Description
 */
public enum RouteStatusEnum {

    WORK(1),
    STOP(2),
    DELETE(0);

    private int num;

    RouteStatusEnum(int num) {
        this.num = num;
    }

    public int getNum() {
        return num;
    }

    @Override
    public String toString() {
        return this.name();
    }
}
