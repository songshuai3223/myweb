package com.cn.my.security.starter.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cn.my.security.starter.dto.RestResponse;
import com.cn.my.security.starter.module.Role;
import com.cn.my.security.starter.module.User;
import com.cn.my.security.starter.req.UserReq;
import com.cn.my.security.starter.resp.UserResp;
import com.cn.my.security.starter.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author caoxingrui
 * @date 2021/4/25-11:01
 * @Description
 */
@Api(tags = {"用户管理"}, value = "/v1/user", produces = "application/json")
@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    private IUserService dspUserService;

    @ApiOperation("用户管理列表")
    @GetMapping("/list")
    public RestResponse<Page<UserResp>> findList(@RequestParam(value = "pageNum", defaultValue = "1") @ApiParam(value = "分页页码，默认为1") Integer pageNum,
                                                 @RequestParam(value = "pageSize", defaultValue = "10") @ApiParam(value = "分页大小，默认为10") Integer pageSize,
                                                 String param) {
        return RestResponse.successGet(dspUserService.findList(param, pageNum, pageSize));
    }

    @ApiOperation("用户详情")
    @GetMapping("/info/{userId}")
    public RestResponse<UserResp> info(@PathVariable @Validated Long userId) {
        return RestResponse.successGet(dspUserService.findUserById(userId));
    }

    @ApiOperation("新增用户")
    @PostMapping("/_add")
    public RestResponse addUser(@RequestBody @Validated UserReq req) {
        dspUserService.addUser(req);
        return RestResponse.successPut();
    }

    @ApiOperation("编辑用户")
    @PostMapping("/_edit")
    public RestResponse editUser(@RequestBody @Validated UserReq req) {
        dspUserService.editUser(req);
        return RestResponse.successPut();
    }

    @ApiOperation("重置用户密码")
    @PostMapping("/_reset")
    public RestResponse resetUserPwd(@RequestBody @Validated User req) {
        dspUserService.resetUserPwd(req);
        return RestResponse.successPut();
    }

    @ApiOperation("删除用户")
    @PostMapping("/_del/{userId}")
    public RestResponse delUser(@PathVariable @Validated Long userId) {
        dspUserService.delUser(userId);
        return RestResponse.successPut();
    }

    @ApiOperation("角色列表")
    @GetMapping("/role/list")
    public RestResponse<List<Role>> findRoleList(String param) {
        return RestResponse.successGet(dspUserService.findRoleList(param));
    }

}
