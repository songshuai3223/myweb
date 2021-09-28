package com.cn.my.common.starter.exceptions;

/**
 * @author biren
 * 函数定义异常
 */
public class FunException extends SystemException {

    public FunException(String message) {
        super(message);
    }

    public FunException(String message, Throwable cause) {
        super(message, cause);
    }
}
