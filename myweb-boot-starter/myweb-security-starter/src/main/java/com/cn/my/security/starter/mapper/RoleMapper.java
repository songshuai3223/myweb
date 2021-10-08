package com.cn.my.security.starter.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cn.my.security.starter.module.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author songshuai
 * @date 2021/4/25-11:35
 * @Description
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * @param userId
     * @return
     */
    @Select("select distinct dr.* from dsp_user_role dur left join dsp_role dr on dur.role_id = dr.id " +
            "where dur.status = 1 and dr.status = 1 and dur.user_id = #{userId}")
    List<Role> findRoleByUser(@Param("userId") Long userId);
}
