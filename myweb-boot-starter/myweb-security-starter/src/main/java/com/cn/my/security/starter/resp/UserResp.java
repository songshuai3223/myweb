package com.cn.my.security.starter.resp;

import com.cn.my.security.starter.module.Role;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author songshuai
 * @date 2021/4/25-11:11
 * @Description
 */
@Data
public class UserResp {

    /**
     * Id
     */
    @ApiModelProperty("用户id")
    private Long id;

    /**
     * 用户名
     */
    @ApiModelProperty("用户名")
    private String username;

    /**
     * 手机号
     */
    @ApiModelProperty("手机号")
    private String mobile;

    /**
     * 修改时间
     */
    @ApiModelProperty("修改时间")
    private LocalDateTime updateTime;

    /**
     * 用户角色
     */
    @ApiModelProperty("用户角色列表")
    private List<Role> roleList;
}
