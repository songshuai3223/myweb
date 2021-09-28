package com.cn.my.security.starter.converter;

import com.cn.my.security.starter.module.User;
import com.cn.my.security.starter.req.UserReq;
import com.cn.my.security.starter.resp.UserResp;
import org.mapstruct.Mapper;

/**
 * @author songshuai
 * @date 2021/4/25-15:02
 * @Description
 */
@Mapper(componentModel = "spring")
public interface UserConverter {

    UserResp user2UserResp(User dspUser);

    User userReq2User(UserReq req);
}
