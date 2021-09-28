package com.cn.my.common.starter.enums;

import lombok.Getter;

/**
 * 异常信息枚举
 *
 * @author songshuai
 * @date 2021/3/4-15:17
 * @Description
 */
@Getter
public enum ErrorMsgEnum {

    /**
     * 成功
     */
    SUCCESS("成功"),

    PARAM_ERROR("参数不正确"),

    PARAM_EMPTY("参数不能为空"),

    DATASOURCEINFO_ERROR("数据源不存在"),

    RSA_ERROR("RSA解密失败"),

    SERVICE_NAME_ERROR("接口名称已存在"),

    SERVICE_PATH_ERROR("接口路径已存在"),

    SERVICE_CODE_ERROR("接口编号已存在"),

    SERVICE_NOT_NAME_ERROR("接口名称不能为空"),

    SERVICE_NOT_PATH_ERROR("接口路径不能为空"),

    SERVICE_NOT_CODE_ERROR("接口编号不能为空"),

    SERVICE_NOT_VERSION_ERROR("接口期数不能为空"),

    MD_INSTALL_ERROR("md文档保存失败"),

    PDF_INSTALL_ERROR("md文档保存失败"),

    DOC_ERROR("接口配置文档生成异常"),

    SERVICE_UPDATE_ERROR("接口修改异常"),

    SERVICE_INSTALL_ERROR("接口保存异常"),

    SERVICE_CONFIG_ERROR("服务配置信息不存在"),

    SERVICE_CONFIG_STATE_ERROR("服务状态不符，不允许变更状态"),

    SERVICE_CONFIG_FORMAT_ERROR("接口配置信息格式不正确"),

    SERVICE_CONFIG_PARAM_ERROR("检测到字段有更改,请重新生成接口配置"),

    SERVICE_CATEINFO_ERROR("获取信息源异常"),

    SERVICE_INPUTCOLUMN_ERROR("获取输入字段异常"),

    SERVICE_OUTPUTCOLUMN_ERROR("获取输出字段异常"),

    SERVICE_NO_FOUND_ERROR("服务不存在"),

    TAG_NAME_ERROR("标签名称已存在"),

    TAG_CODE_ERROR("标签编码已存在"),

    TAG_NOT_ERROR("标签不存在"),

    TAG_WORK_ERROR("标签在使用中,不允许删除"),

    ROUTE_NOT_FOUND("路由不存在"),

    ROUTE_STATUS_ERROR("路由状态调整失败"),

    ROUTE_NAME_ERROR("路由名称已经存在"),

    ROUTE_CODE_ERROR("路由编码已经存在"),

    CLUSTER_NOT_ERROR("集群不存在"),

    CLUSTER_NAME_NOT_ERROR("集群名称已存在"),

    CLUSTER_CODE_NOT_ERROR("集群编码已存在"),

    CLUSTER_DEL_ERROR("集群删除失败"),

    SERVICE_PLUGIN_CODE_ERROR("插件编号已存在"),

    SERVICE_PLUGIN_NAME_ERROR("插件名称已存在"),

    SERVICE_PLUGIN_NOT_ERROR("插件不存在"),

    CONSUMER_USERNAME_EXIST_ERROR("应用名称已存在"),

    CONSUMER_ID_EXIST_ERROR("应用ID已存在"),

    USER_GROUP_CODE_EXIST_ERROR("授权ID已存在"),

    USER_GROUP_NAME_EXIST_ERROR("授权名称已存在"),

    USER_GROUP_NOT_ERROR("授权不存在"),

    RELATION_CONSUMER_DEL_ERROR("删除关联应用失败"),

    RELATION_ROUTE_DEL_ERROR("删除关联路由失败"),

    DSP_USER_NOT_ERROR("用户不存在"),

    DSP_USER_NAME_EXIST_ERROR("用户名已存在"),

    DSP_USER_PHONE_EXIST_ERROR("手机号码已存在"),

    DATABASE_DATASOURCE_CODE_ERROR("相同编号数据库源已经存在"),

    DATABASE_DATASOURCE_NAME_ERROR("同名数据库源已经存在"),

    DATABASE_DATASOURCE_NO_FOUND_ERROR("数据库源不存在"),

    DATABASE_TYPE_NO_FOUND_ERROR("数据库类型不存在");

    private String message;

    ErrorMsgEnum(String message) {
        this.message = message;
    }

}
