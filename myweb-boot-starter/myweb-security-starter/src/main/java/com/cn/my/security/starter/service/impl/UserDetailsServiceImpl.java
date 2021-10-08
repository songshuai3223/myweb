package com.cn.my.security.starter.service.impl;

import com.cn.my.security.starter.module.Role;
import com.cn.my.security.starter.module.User;
import com.cn.my.security.starter.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description TODO
 * @Date 2021/4/21 15:13
 * @Created by songshuai
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private IUserService userService;

    /**
     * 授权的时候是对角色授权
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User dspUser = userService.getUserByMobile(username);
        if (null == dspUser) {
            throw new UsernameNotFoundException(username);
        }
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        List<Role> dspRoleList = userService.getRoleByUsername(dspUser.getId());
        for (Role dspRole : dspRoleList) {
            authorities.add(new SimpleGrantedAuthority(dspRole.getRoleCode()));
        }
        dspUser.setAuthorities(authorities.stream().collect(Collectors.toSet()));
        return dspUser;
    }
}
