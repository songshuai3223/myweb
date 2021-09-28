package com.cn.my.common.starter.util;

import com.cn.my.common.starter.enums.ErrorMsgEnum;
import com.cn.my.common.starter.exceptions.DspException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.util.ObjectUtils;

import java.util.Objects;

/**
 * <p>
 * API 断言
 * </p>
 *
 * @author Caratacus
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ApiAssert {

    /**
     * obj1 eq obj2
     *
     * @param obj1
     * @param obj2
     * @param errorMsgEnum
     */
    public static void equals(ErrorMsgEnum errorMsgEnum, Object obj1, Object obj2) {
        if (!Objects.equals(obj1, obj2)) {
            failure(errorMsgEnum);
        }
    }

    public static void isTrue(ErrorMsgEnum errorMsgEnum, boolean condition) {
        if (!condition) {
            failure(errorMsgEnum);
        }
    }

    public static void isFalse(ErrorMsgEnum errorMsgEnum, boolean condition) {
        if (condition) {
            failure(errorMsgEnum);
        }
    }

    public static void isNull(ErrorMsgEnum errorMsgEnum, Object... conditions) {
        if (ApiAssert.isNotNull(conditions)) {
            failure(errorMsgEnum);
        }
    }

    public static void notNull(ErrorMsgEnum errorMsgEnum, Object... conditions) {
        if (ApiAssert.isNull(conditions)) {
            failure(errorMsgEnum);
        }
    }

    /**
     * <p>
     * 失败结果
     * </p>
     *
     * @param errorMsgEnum 异常错误码
     */
    public static void failure(ErrorMsgEnum errorMsgEnum) {
        throw new DspException(errorMsgEnum.getMessage());
    }

    public static void notEmpty(ErrorMsgEnum errorMsgEnum, Object[] array) {
        if (ObjectUtils.isEmpty(array)) {
            failure(errorMsgEnum);
        }
    }

    /**
     * obj1 eq obj2
     *
     * @param obj1
     * @param obj2
     * @param status
     */
    public static void equals(HttpStatus status, String msg, Object obj1, Object obj2) {
        if (!Objects.equals(obj1, obj2)) {
            failure(status, msg);
        }
    }

    public static void isTrue(HttpStatus status, String msg, boolean condition) {
        if (!condition) {
            failure(status, msg);
        }
    }

    public static void isFalse(HttpStatus status, String msg, boolean condition) {
        if (condition) {
            failure(status, msg);
        }
    }

    public static void isNull(HttpStatus status, String msg, Object... conditions) {
        if (ApiAssert.isNotNull(conditions)) {
            failure(status, msg);
        }
    }

    public static void notNull(HttpStatus status, String msg, Object... conditions) {
        if (ApiAssert.isNull(conditions)) {
            failure(status, msg);
        }
    }

    public static void notEmpty(HttpStatus status, String msg, Object[] array) {
        if (ObjectUtils.isEmpty(array)) {
            failure(status, msg);
        }
    }

    /**
     * <p>
     * 失败结果
     * </p>
     *
     * @param status 异常错误码
     */
    public static void failure(HttpStatus status, String msg) {
        throw new DspException(status, msg);
    }

    /**
     * 判断object是否为空,集合会校验size
     */
    private static boolean isNull(Object... objs) {
        for (Object obj : objs) {
            if (ObjectUtils.isEmpty(obj)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断object是否不为空,集合会校验size
     */
    private static boolean isNotNull(Object... obj) {
        return !ApiAssert.isNull(obj);
    }
}
