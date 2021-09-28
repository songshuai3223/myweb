package com.cn.my.security.starter.mapper;



import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cn.my.security.starter.module.Role;
import com.cn.my.security.starter.module.User;
import com.cn.my.security.starter.module.UserRole;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author songshuai
 * @date 2021/4/25-11:35
 * @Description
 */
public interface UserRoleMapper extends BaseMapper<UserRole> {
}
