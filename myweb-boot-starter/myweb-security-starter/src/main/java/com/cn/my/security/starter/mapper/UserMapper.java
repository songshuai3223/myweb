package com.cn.my.security.starter.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cn.my.security.starter.module.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author songshuai
 * @date 2021/4/25-11:34
 * @Description
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
