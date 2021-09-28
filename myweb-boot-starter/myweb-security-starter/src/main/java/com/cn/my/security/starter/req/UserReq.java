package com.cn.my.security.starter.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author songshuai
 * @date 2021/4/25-15:31
 * @Description
 */
@Data
public class UserReq {

    /**
     * Id
     */
    @ApiModelProperty("用户id")
    private Long id;

    /**
     * 用户名
     */
    @ApiModelProperty("用户名")
    @NotEmpty(message = "用户名不能为空")
    private String username;

    /**
     * 密码
     */
    @ApiModelProperty("密码")
    private String password;

    /**
     * 手机号
     */
    @ApiModelProperty("手机号")
    @NotEmpty(message = "手机号不能为空")
    private String mobile;

    /**
     * 用户角色
     */
    @ApiModelProperty("用户角色ids")
    @NotNull(message = "角色不能为空")
    private List<Long> roleIds;
}
