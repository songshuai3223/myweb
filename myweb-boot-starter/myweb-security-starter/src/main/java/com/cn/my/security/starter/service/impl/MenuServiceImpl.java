package com.cn.my.security.starter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cn.my.common.starter.enums.CommonStatusEnum;
import com.cn.my.security.starter.converter.MenuConverter;
import com.cn.my.security.starter.mapper.MenuMapper;
import com.cn.my.security.starter.mapper.MenuRoleMapper;
import com.cn.my.security.starter.module.Menu;
import com.cn.my.security.starter.module.MenuRole;
import com.cn.my.security.starter.resp.MenuResp;
import com.cn.my.security.starter.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description TODO
 * @Date 2021/4/22 14:34
 * @Created by songshuai
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {


    @Autowired
    private MenuConverter dspMenuConverter;
    @Autowired
    private MenuRoleMapper menuRoleMapper;
    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<MenuResp> getMenuListByRole(Integer roleId) {
        QueryWrapper<MenuRole> menuRoleQueryWrapper = new QueryWrapper<>();
        menuRoleQueryWrapper.lambda().eq(MenuRole::getRoleId, roleId)
                .eq(MenuRole::getStatus, CommonStatusEnum.NORMAL.getValue());
        List<MenuRole> dspMenuRoleList = menuRoleMapper.selectList(menuRoleQueryWrapper);

        List menuIdList = dspMenuRoleList.stream().map(MenuRole::getMenuId).collect(Collectors.toList());
        QueryWrapper<Menu> menuQueryWrapper = new QueryWrapper<>();
        menuQueryWrapper.lambda().in(Menu::getId, menuIdList)
                .eq(Menu::getStatus, CommonStatusEnum.NORMAL.getValue());
        List<Menu> dspMenuList = menuMapper.selectList(menuQueryWrapper);
        List<MenuResp> dspMenuRespList = dspMenuConverter.tagInfoDtoList2TagInfoList(dspMenuList);
        return dspMenuRespList;
    }
}
