package com.cn.my.security.starter.module;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Description 服务平台菜单表
 * @Date 2021/4/22 14:15
 * @Created by songshuai
 */
@Data
@TableName("dsp_menu")
public class Menu {
    /**
     * ID
     */
    private Integer id;

    /**
     * 菜单编码
     */
    private String menuCode;

    /**
     * 菜单名称
     */
    private String menuName;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 父ID
     */
    private Integer parentId;

    /**
     * 路由
     */
    private String routePath;

    /**
     * 文件路径
     */
    private String filePath;

    /**
     * 菜单级别
     */
    private Integer level;

    /**
     * 创建人
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
     * 更新时间
     */
    private LocalDateTime updateTime;

    /***
     * 排序
     */
    private Integer sort;

    /***
     * 图标
     */
    @ApiModelProperty("图标")
    private String icon;
}
