package com.cn.my.security.starter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cn.my.common.starter.enums.CommonStatusEnum;
import com.cn.my.common.starter.enums.ErrorMsgEnum;
import com.cn.my.common.starter.util.ApiAssert;
import com.cn.my.common.starter.util.RSAEncryptUtil;
import com.cn.my.security.starter.config.UserUtil;
import com.cn.my.security.starter.converter.UserConverter;
import com.cn.my.security.starter.mapper.RoleMapper;
import com.cn.my.security.starter.mapper.UserMapper;
import com.cn.my.security.starter.mapper.UserRoleMapper;
import com.cn.my.security.starter.module.Role;
import com.cn.my.security.starter.module.User;
import com.cn.my.security.starter.module.UserRole;
import com.cn.my.security.starter.req.UserReq;
import com.cn.my.security.starter.resp.UserResp;
import com.cn.my.security.starter.service.IUserRoleService;
import com.cn.my.security.starter.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Description 用户管理service
 * @Date 2021/4/21 15:25
 * @Created by songshuai
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User>  implements IUserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private IUserRoleService userRoleService;


    @Autowired
    private UserConverter userConverter;

    @Value("${encodec.private-key}")
    private String privateKey;

    @Value("${encodec.public-key}")
    private String publicKey;

    @Override
    public User getUserByName(String username) {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.lambda().eq(User::getUsername, username)
                .eq(User::getStatus, CommonStatusEnum.NORMAL.getValue());
        return userMapper.selectOne(userQueryWrapper);
    }

    @Override
    public User getUserByMobile(String mobile) {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.lambda().eq(User::getMobile, mobile)
                .eq(User::getStatus, CommonStatusEnum.NORMAL.getValue());
        return userMapper.selectOne(userQueryWrapper);
    }

    @Override
    public List<Role> getRoleByUsername(long userId) {
        return roleMapper.findRoleByUser(userId);
    }

    @Override
    public Page<UserResp> findList(String param, Integer pageNum, Integer pageSize) {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.lambda().like(User::getUsername, param)
                .like(User::getMobile, param).eq(User::getStatus, CommonStatusEnum.NORMAL.getValue())
                .orderByDesc(User::getUpdateTime);
        Page<User> userPage = new Page<>(pageNum , pageSize);
        IPage<User> page = userMapper.selectPage(userPage , userQueryWrapper);
        Page result = new Page<UserResp>();
        List<User> userList = page.getRecords();
        if (!CollectionUtils.isEmpty(userList)) {
            // 设置总页数 总条数
            result.setTotal(page.getTotal());
            result.setPages(page.getPages());
            List<UserResp> resps = new ArrayList<>(userList.size());
            for (User user : userList) {
                UserResp resp = userConverter.user2UserResp(user);
                // 查询用户角色
                resp.setRoleList(this.getRoleByUsername(resp.getId()));
                resps.add(resp);
            }
            result.setRecords(resps);
        }
        return result;
    }

    @Override
    public UserResp findUserById(Long userId) {
        User user = this.getUserById(userId);
        UserResp userResp = userConverter.user2UserResp(user);
        userResp.setRoleList(this.getRoleByUsername(userResp.getId()));
        return userResp;
    }

    @Override
    public void addUser(UserReq req) {
        // 手动判断密码必填
        ApiAssert.isFalse(HttpStatus.BAD_REQUEST, "用户密码不能为空", StringUtils.isEmpty(req.getPassword()));

        checkUserDuplicate(req);

        // 登录用户
        User loginUser = UserUtil.getUser();


        //获取前端加密的密码
        String receivePassword = req.getPassword();
        //解析原始密码
        String originPassword = null;
        try {
            originPassword = RSAEncryptUtil.decrypt(receivePassword, privateKey);
        } catch (Exception e) {
            log.error("加密异常", e);
            log.error(e.getMessage());
        }
        BCryptPasswordEncoder bcryptPasswordEncoder = new BCryptPasswordEncoder();
        String password = bcryptPasswordEncoder.encode(originPassword);

        // 新增用户
        LocalDateTime now = LocalDateTime.now();
        User user = userConverter.userReq2User(req);
        user.setPassword(password);
        user.setCreateBy(loginUser.getId());
        user.setCreateTime(now);
        user.setUpdateBy(loginUser.getId());
        user.setUpdateTime(now);
        user.setStatus(CommonStatusEnum.NORMAL.getValue());
        userMapper.insert(user);

        // 新增用户角色
        List<Long> roleIds = req.getRoleIds();
        List<UserRole> roles = new ArrayList<>(roleIds.size());
        for (Long roleId : roleIds) {
            UserRole userRole = new UserRole();
            userRole.setRoleId(roleId);
            userRole.setUserId(user.getId());
            userRole.setCreateBy(loginUser.getId());
            userRole.setCreateTime(now);
            userRole.setUpdateBy(loginUser.getId());
            userRole.setUpdateTime(now);
            userRole.setStatus(CommonStatusEnum.NORMAL.getValue());
            roles.add(userRole);
        }
        userRoleService.saveBatch(roles);

    }

    private void checkUserDuplicate(UserReq req) {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.lambda().eq(User::getUsername, req.getUsername())
                .eq(User::getStatus, CommonStatusEnum.NORMAL.getValue());
        User user=userMapper.selectOne(userQueryWrapper);
        ApiAssert.isTrue(ErrorMsgEnum.DSP_USER_NAME_EXIST_ERROR, user == null || user.getId().equals(req.getId()));


        QueryWrapper<User> user2QueryWrapper = new QueryWrapper<>();
        user2QueryWrapper.lambda().eq(User::getMobile, req.getMobile())
                .eq(User::getStatus, CommonStatusEnum.NORMAL.getValue());
        user=userMapper.selectOne(user2QueryWrapper);
        ApiAssert.isTrue(ErrorMsgEnum.DSP_USER_PHONE_EXIST_ERROR, user == null || user.getId().equals(req.getId()));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void editUser(UserReq req) {
        User user = this.getUserById(req.getId());
        ApiAssert.notNull(ErrorMsgEnum.DSP_USER_NOT_ERROR, user);

        checkUserDuplicate(req);

        // 登录用户
        User loginUser = UserUtil.getUser();

        // 构造更新用户信息
        LocalDateTime now = LocalDateTime.now();
        user = User.builder()
                .id(req.getId())
                .username(req.getUsername())
                .mobile(req.getMobile())
                .updateBy(loginUser.getId())
                .updateTime(now)
                .build();
        userMapper.updateById(user);

        QueryWrapper<UserRole> userRoleQueryWrapper = new QueryWrapper<>();
        // 检查用户角色
        Set<Long> set = req.getRoleIds().stream().collect(Collectors.toSet());
        Set<Long> ids = this.getRoleByUsername(req.getId()).stream().map(Role::getId).collect(Collectors.toSet());
        // 移除的关联关系
        List<Long> delIds = ids.stream().filter(f -> !set.contains(f)).collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(delIds)) {
            UserRole userRole = UserRole.builder()
                    .updateBy(loginUser.getId())
                    .updateTime(now)
                    .status(CommonStatusEnum.DELETE.getValue())
                    .build();
            userRoleQueryWrapper.lambda().in(UserRole::getRoleId, delIds)
                    .eq(UserRole::getUserId, user.getId());
           userRoleMapper.update(userRole,userRoleQueryWrapper);
        }

        // 新增关联关系
        List<Long> addIds = set.stream().filter(f -> !ids.contains(f)).collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(addIds)) {
            List<UserRole> list = new ArrayList<>(addIds.size());
            for (Long id : addIds) {
                UserRole userRole = new UserRole();
                userRole.setRoleId(id);
                userRole.setUserId(user.getId());
                userRole.setCreateBy(loginUser.getId());
                userRole.setCreateTime(now);
                userRole.setUpdateBy(loginUser.getId());
                userRole.setUpdateTime(now);
                userRole.setStatus(CommonStatusEnum.NORMAL.getValue());
                list.add(userRole);
            }
            userRoleService.saveBatch(list);
        }
    }

    @Override
    public void resetUserPwd(User req) {
        User user = this.getUserById(req.getId());
        ApiAssert.isFalse(HttpStatus.BAD_REQUEST, "用户密码不能为空", StringUtils.isEmpty(req.getPassword()));

        // 登录用户
        User loginUser = UserUtil.getUser();
        //获取前端加密的密码
        String receivePassword = req.getPassword();
        //解析原始密码
        String originPassword = null;
        try {
            originPassword = RSAEncryptUtil.decrypt(receivePassword, privateKey);
        } catch (Exception e) {
            log.error("加密异常", e);
            log.error(e.getMessage());
        }
        BCryptPasswordEncoder bcryptPasswordEncoder = new BCryptPasswordEncoder();
        String password = bcryptPasswordEncoder.encode(originPassword);
        //后端进行加密
        user = User.builder()
                .id(user.getId())
                .password(password)
                .updateBy(loginUser.getId())
                .updateTime(LocalDateTime.now())
                .build();
        userMapper.updateById(user);
    }

    @Override
    public void delUser(Long userId) {
        User user = this.getUserById(userId);

        // 登录用户
        User loginUser = UserUtil.getUser();

        user = User.builder()
                .id(user.getId())
                .status(CommonStatusEnum.DELETE.getValue())
                .updateBy(loginUser.getId())
                .updateTime(LocalDateTime.now())
                .build();
        userMapper.updateById(user);
    }

    @Override
    public User getUserById(Long userId) {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.lambda().eq(User::getId, userId)
                .eq(User::getStatus, CommonStatusEnum.NORMAL.getValue())
                .orderByDesc(User::getUpdateTime);
        User user=userMapper.selectOne(userQueryWrapper);
        ApiAssert.notNull(ErrorMsgEnum.DSP_USER_NOT_ERROR, user);
        return user;
    }

    @Override
    public UserResp getCurrentUser() {
        UserResp userResp = new UserResp();
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = getUserByName(username);
        Long userId = user.getId();
        userResp.setId(userId);
        userResp.setUsername(user.getUsername());
        userResp.setMobile(user.getMobile());
        userResp.setUpdateTime(user.getUpdateTime());
        List<Role> roleList = getRoleByUsername(userId);
        userResp.setRoleList(roleList);
        return userResp;
    }

    @Override
    public List<Role> findRoleList(String param) {
        QueryWrapper<Role> roleQueryWrapper = new QueryWrapper<>();
        roleQueryWrapper.lambda().like(Role::getRoleName, param)
                .like(Role::getRoleCode, param).eq(Role::getStatus, CommonStatusEnum.NORMAL.getValue());
        return roleMapper.selectList(roleQueryWrapper);
    }
}
