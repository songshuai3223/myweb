package com.digitalgd.cloud.code.generator.catalog.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.digitalgd.cloud.code.generator.catalog.model.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * <p>
 *  服务类
 * @since 2021-07-05
 * </p>
 *
 * @author songshuai3223@163.com
 */
public interface IUserService extends IService<User> {
    /***
     * 根据主键查询User
     * @param user
     * @return
     */
    User getUser(User user);

    /***
     * 根据实体参数查询User列表
     * @param user
     * @return
     */
    List<User> getUserlist(User user);

    /***
     * 根据实体参数查询分页User分页列表
     * @param user
     * @param userPage
     * @return
     */
    IPage<User> getUserPagelist(IPage<User> userPage, User user);

    /***
     * 根据实体参数模糊查询User列表
     * @param user
     * @return
     */
    List<User> getUserLikeList(User user);


    /***
     * 根据实体参数查询分页User分页列表
     * @param user
     * @param userPage
     * @return
     */
    IPage<User> getUserLikePageList(IPage<User> userPage, User user);
    /***
     * 根据条件查询总数
     * @param user
     * @return
     */
    int countUser(User user);

    /***
     * 插入User
     * @param user
     * @return
     */
    int saveUser(User user);

    /***
     * 修改User
     * @param user
     * @return
     */
    int updateUser(User user);

    /***
     * 删除User
     * @param user
     * @return
     */
    int removeUser(User user);

    /***
     * 批量插入User
     * @param userList
     * @return
     */
    boolean batchSaveUser(List<User>  userList);

    /***
     * 批量修改User
     * @param userList
     * @return
     */
    boolean batchUpdateUser(List<User> userList);

    /***
     * 批量删除User
     * @param user
     * @return
     */
    int batchRemoveUser(User user);
}