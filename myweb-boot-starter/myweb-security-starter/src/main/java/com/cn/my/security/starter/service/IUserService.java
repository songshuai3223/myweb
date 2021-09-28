package com.cn.my.security.starter.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cn.my.security.starter.module.Role;
import com.cn.my.security.starter.module.User;
import com.cn.my.security.starter.req.UserReq;
import com.cn.my.security.starter.resp.UserResp;

import java.util.List;

/**
 * @Description 用户管理
 * @Date 2021/4/21 15:11
 * @Created by songshuai
 */
public interface IUserService extends IService<User> {

    /**
     * 根据用户名查询用户信息
     *
     * @param username
     * @return
     */
    User getUserByName(String username);

    /**
     * 根据手机号查询用户信息
     *
     * @param mobile
     * @return
     */
    User getUserByMobile(String mobile);

    /**
     * 根据ID查找对应的角色
     *
     * @param userId
     * @return
     */
    List<Role> getRoleByUsername(long userId);

    /**
     * 用户管理列表
     *
     * @param param    查询条件
     * @param pageNum
     * @param pageSize
     * @return
     */
    Page<UserResp> findList(String param, Integer pageNum, Integer pageSize);

    /**
     * 根据用户id查询用户信息
     *
     * @param userId
     * @return
     */
    User getUserById(Long userId);

    /**
     * 根据用户id查询用户信息
     *
     * @param userId
     * @return
     */
    UserResp findUserById(Long userId);

    /**
     * 新增用户
     *
     * @param req
     */
    void addUser(UserReq req);

    /**
     * 编辑用户
     *
     * @param req
     */
    void editUser(UserReq req);

    /**
     * 重置用户密码
     *
     * @param req
     */
    void resetUserPwd(User req);

    /**
     * 删除用户
     *
     * @param userId
     */
    void delUser(Long userId);

    UserResp getCurrentUser();

    /**
     * 查询所有角色
     *
     * @param param 查询条件 角色名|角色编码
     * @return
     */
    List<Role> findRoleList(String param);
}
