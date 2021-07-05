package com.digitalgd.cloud.code.generator.catalog.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.digitalgd.cloud.code.generator.catalog.model.entity.User;
import com.digitalgd.cloud.code.generator.catalog.mapper.UserMapper;
import com.digitalgd.cloud.code.generator.catalog.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import java.util.List;
/**
 * <p>
 *  服务实现类
 * @since 2021-07-05
 * </p>
 *
 * @author songshuai3223@163.com
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>implements IUserService {
    @Autowired
    private UserMapper userMapper;

    /***
     * 根据主键查询User
     * @param user
     * @return
     */
    @Override
    public User getUser(User user){
        QueryWrapper<User> userQueryWrapper=new QueryWrapper<>(user);
        return userMapper.selectOne(userQueryWrapper);
    }

    /***
     * 根据实体参数查询User
     * @param user
     * @return
     */
    @Override
    public List<User> getUserlist(User user){
        QueryWrapper<User> userQueryWrapper=new QueryWrapper<>(user);
        return userMapper.selectList(userQueryWrapper);
    }
    /***
     * 根据实体参数查询分页User列表
     * @param user
     * @param userPage
     * @return
     */
    @Override
    public IPage<User> getUserPagelist(IPage<User> userPage, User user){
        QueryWrapper<User> userQueryWrapper=new QueryWrapper<>(user);
        return userMapper.selectPage(userPage,userQueryWrapper);
    }
    /***
    * 根据实体参数模糊查询User
    * @param user
    * @return
    */
    @Override
    public List<User> getUserLikeList(User user){
        QueryWrapper<User> userQueryWrapper=new QueryWrapper<>();

        userQueryWrapper.like("id", StringUtils.isEmpty(user.getId()))?"":user.getId());
        userQueryWrapper.like("name", StringUtils.isEmpty(user.getName()))?"":user.getName());
        userQueryWrapper.like("age", StringUtils.isEmpty(user.getAge()))?"":user.getAge());
        userQueryWrapper.like("email", StringUtils.isEmpty(user.getEmail()))?"":user.getEmail());

        return userMapper.selectList(userQueryWrapper);
        }

    /***
     * 根据实体参数查询分页User分页列表
     * @param user
     * @param userPage
     * @return
     */
    @Override
    public IPage<User> getUserLikePageList(IPage<User> userPage, User user){
        QueryWrapper<User> userQueryWrapper=new QueryWrapper<>();

        userQueryWrapper.like("id", user.getId());
            userQueryWrapper.like("name", user.getName());
            userQueryWrapper.like("age", user.getAge());
            userQueryWrapper.like("email", user.getEmail());

        return userMapper.selectPage(userPage,userQueryWrapper);
        }
    /***
     * 根据条件查询总数
     * @param user
     * @return
     */
    @Override
    public int countUser(User user){
        QueryWrapper<User> userQueryWrapper=new QueryWrapper<>(user);
        return userMapper.selectCount(userQueryWrapper);
    }

    /***
     * 插入User
     * @param user
     * @return
     */
    @Override
    public int saveUser(User user){
        return userMapper.insert(user);
    }

    /***
     * 修改User
     * @param user
     * @return
     */
    @Override
    public int updateUser(User user){
        return userMapper.updateById(user);
    }

    /***
     * 删除User
     * @param user
     * @return
     */
    @Override
    public int removeUser(User user){
        QueryWrapper<User> userQueryWrapper=new QueryWrapper<>(user);
        return userMapper.delete(userQueryWrapper);
    }

    /***
     * 批量插入User
     * @param userList
     * @return
     */
    @Override
    public boolean batchSaveUser(List<User> userList){
        return saveBatch(userList);
    }

    /***
     * 批量修改User
     * @param userList
     * @return
     */
    @Override
    public boolean batchUpdateUser(List<User> userList){
        return updateBatchById(userList);
    }

    /***
     * 批量删除Usermd
     * @param user
     * @return
     */
    @Override
     public int batchRemoveUser(User user){
        QueryWrapper<User> userQueryWrapper=new QueryWrapper<>(user);
        return userMapper.delete(userQueryWrapper);
    }
}
