package com.cn.my.security.starter.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.cn.my.security.starter.module.Menu;
import com.cn.my.security.starter.resp.MenuResp;

import java.util.List;

/**
 * @Description 菜单管理
 * @Date 2021/4/22 14:33
 * @Created by songshuai
 */
public interface IMenuService extends IService<Menu> {
    /**
     * 根据角色查询菜单列表，未形成树状结构
     *
     * @param roleId
     * @return
     */
    List<MenuResp> getMenuListByRole(Integer roleId);
}
