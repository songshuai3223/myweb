package com.cn.my.log.starter;

import java.util.UUID;

/**
 * @author songshuai
 * @date 2020/1/17-17:51
 * @Description
 */
public class LogUtils {
    public static final String LOG_ID = "LOG_ID";
    //一下常量为数广日志平台要求的日志
    public static final String IP = "ip";

    public static String logId() {
        return UUID.randomUUID().toString();
    }
}
