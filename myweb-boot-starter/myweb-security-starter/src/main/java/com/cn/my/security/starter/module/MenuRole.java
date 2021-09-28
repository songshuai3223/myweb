package com.cn.my.security.starter.module;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Description 数据服务菜单角色表
 * @Date 2021/4/22 14:28
 * @Created by songshuai
 */
@Data
@TableName("dsp_menu_role")
public class MenuRole {

    private Integer id;
    private Integer menuId;
    private Integer roleId;
    private Integer status;
    private Long createBy;
    private LocalDateTime createTime;
    private Long updateBy;
    private LocalDateTime updateTime;
}
