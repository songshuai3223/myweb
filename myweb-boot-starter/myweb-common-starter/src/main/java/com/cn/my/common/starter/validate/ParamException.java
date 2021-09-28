package com.cn.my.common.starter.validate;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.graalvm.polyglot.HostAccess;

/**
 * 参数错误
 *
 * @author biren
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ParamException extends RuntimeException {
    private String msg;

    private ParamException(String message) {
        super(message);
        this.msg = message;
    }

    /**
     * 供js脚本中用于抛出异常
     *
     * @param msg 提示信息
     */
    @HostAccess.Export
    public static ParamException error(String msg) {
        return new ParamException(msg);
    }
}
