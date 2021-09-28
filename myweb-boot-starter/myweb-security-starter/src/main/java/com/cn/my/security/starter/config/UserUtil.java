package com.cn.my.security.starter.config;

import com.cn.my.common.starter.util.ApiAssert;
import com.cn.my.security.starter.module.User;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Objects;

/**
 * @author songshuai
 * @date 2021/4/27-16:39
 * @Description
 */
public final class UserUtil {

    public static User getUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (Objects.nonNull(principal) && principal instanceof UserDetails) {
            User user = (User) principal;
            return user;
        }
        ApiAssert.failure(HttpStatus.BAD_REQUEST, "未找到用户信息");
        return null;
    }
}
