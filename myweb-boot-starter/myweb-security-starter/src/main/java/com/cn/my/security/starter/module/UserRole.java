package com.cn.my.security.starter.module;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @Description 数据服务用户角色关系表
 * @Date 2021/4/21 16:05
 * @Created by songshuai
 */
@TableName("dsp_user_role")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRole {
    /**
     * id
     */
    private Long id;

    /**
     * 用户表ID
     */
    private Long userId;

    /**
     * 角色表ID
     */
    private Long roleId;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 更新时间
     */
    private Long createBy;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新人
     */
    private Long updateBy;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;
}
