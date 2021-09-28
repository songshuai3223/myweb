package com.cn.my.security.starter.resp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description 服务平台菜单表
 * @Date 2021/4/22 14:15
 * @Created by songshuai
 */
@Data
public class MenuResp {
    /**
     * ID
     */

    @ApiModelProperty("id")
    private Integer id;
    /**
     * 菜单编码
     */
    @ApiModelProperty("菜单编码")
    private String menuCode;
    /**
     * 菜单名称
     */
    @ApiModelProperty("菜单名称")
    private String menuName;

    /**
     * 父ID
     */
    @ApiModelProperty("父ID")
    private Integer parentId;
    /**
     * 路由
     */
    @ApiModelProperty("路由")
    private String routePath;
    /**
     * 菜单级别
     */
    @ApiModelProperty("菜单级别")
    private Integer level;
    /***
     * 排序
     */
    @ApiModelProperty("排序")
    private Integer sort;
    /***
     * 图标
     */
    @ApiModelProperty("图标")
    private String icon;
}
