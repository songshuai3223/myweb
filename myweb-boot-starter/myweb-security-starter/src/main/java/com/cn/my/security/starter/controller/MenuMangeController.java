package com.cn.my.security.starter.controller;

import com.cn.my.security.starter.dto.RestResponse;
import com.cn.my.security.starter.resp.MenuResp;
import com.cn.my.security.starter.service.IMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description TODO
 * @Date 2021/4/22 16:11
 * @Created by songshuai
 */
@Api(tags = {"菜单管理"}, value = "/v1/menu", produces = "application/json")
@RequestMapping("/menu")
@RestController
public class MenuMangeController {

    @Autowired
    private IMenuService dspMenuService;

    @ApiOperation("菜单列表")
    @GetMapping("/list")
    public RestResponse<List<MenuResp>> findList(Integer roleId) {
        return RestResponse.successGet(dspMenuService.getMenuListByRole(roleId));
    }
}
