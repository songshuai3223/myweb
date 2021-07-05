package com.digitalgd.cloud.code.generator.catalog.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.digitalgd.cloud.code.generator.catalog.model.entity.User;
import com.digitalgd.cloud.code.generator.catalog.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import java.util.List;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
/**
 * <p>
 *  前端控制器
 * @since 2021-07-05
 * </p>
 *
 * @author songshuai3223@163.com
 */
@Api(tags = {"/user"}, description = "相关接口")
@RestController
@RequestMapping(value = "user", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Validated
public class UserRestController {

    @Autowired
    private IUserService userService;

    /***
     * 根据主键查询User
     * @param user
     * @return
     */
    @PostMapping("/get")
    @ResponseBody
    @ApiOperation(value="根据主键查询User", notes="根据主键查询User")
    @ApiImplicitParam(paramType="query", name = "user", value = "", required = true, dataType = "User")
    public User getUser(@RequestBody User user){
       return userService.getUser(user);
    }

    /***
     * 根据实体参数查询User列表
     * @param user
     * @return
     */
    @PostMapping("/getlist")
    @ResponseBody
    @ApiOperation(value="根据实体参数查询User列表", notes="根据实体参数查询User列表")
    @ApiImplicitParam(paramType="query", name = "user", value = "", required = true, dataType = "User")
    public List<User> getUserlist(@RequestBody User user){
        return userService.getUserlist(user);
    }

    /***
     * 根据实体参数查询User分页列表
     * @param  user
     * @return
     */
    @PostMapping("/getlist/page")
    @ResponseBody
    @ApiOperation(value="根据实体参数查询User分页列表", notes="根据实体参数查询User分页列表")
    @ApiImplicitParam(paramType="query", name = "user", value = "用户信息", required = true, dataType = "User")
    public IPage<User> getUserPagelist(@RequestBody IPage<User> userPage, @RequestBody User user){
        return userService.getUserPagelist(userPage,user);
    }

    /***
     * 根据实体参数查询User列表
     * @param user
     * @return
     */
    @PostMapping("/getlist/like")
    @ResponseBody
    @ApiOperation(value="根据实体参数模糊查询User列表", notes="根据实体参数模糊查询User列表")
    @ApiImplicitParam(paramType="query", name = "user", value = "", required = true, dataType = "User")
    public List<User> getUserLikeList(@RequestBody User user){
        return userService.getUserLikeList(user);
    }

    /***
     * 根据实体参数查询User分页列表
     * @param user
     * @return
     */
    @PostMapping("/getlist/like/page")
    @ResponseBody
    @ApiOperation(value="根据实体参数模糊查询User分页列表", notes="根据实体参数模糊查询User分页列表")
    @ApiImplicitParam(paramType="query", name = "user", value = "", required = true, dataType = "User")
    public IPage<User> getUserLikePageList(@RequestBody IPage<User> userPage, @RequestBody User user){
        return userService.getUserLikePageList(userPage,user);
    }

    /***
     * 根据条件查询总数
     * @param user
     * @return
     */
    @PostMapping("/count")
    @ResponseBody
    @ApiOperation(value="根据条件查询总数", notes="根据条件查询总数")
    @ApiImplicitParam(paramType="query", name = "user", value = "", required = true, dataType = "User")
    public int countUser(@RequestBody User user){
        return userService.countUser(user);
    }

    /***
     * 插入User
     * @param user
     * @return
     */
    @PostMapping("/save")
    @ResponseBody
    @ApiOperation(value="插入User", notes="插入User")
    @ApiImplicitParam(paramType="data", name = "user", value = "", required = true, dataType = "User")
    public int saveUser(@RequestBody User user){
        return userService.saveUser(user);
    }

    /***
     * 修改User
     * @param user
     * @return
     */
    @PostMapping("/update")
    @ResponseBody
    @ApiOperation(value="修改User", notes="修改User")
    @ApiImplicitParam(paramType="data", name = "user", value = "", required = true, dataType = "User")
    public int updateUser(@RequestBody User user){
        return userService.updateUser(user);
    }

    /***
     * 删除User
     * @param user
     * @return
     */
    @PostMapping("/delete")
    @ResponseBody
    @ApiOperation(value="删除User", notes="删除User")
    @ApiImplicitParam(paramType="data", name = "user", value = "", required = true, dataType = "User")
    public int removeUser(@RequestBody User user){
        return userService.removeUser(user);
    }

    /***
     * 批量插入User
     * @param userList
     * @return
     */
    @PostMapping("/batch/save")
    @ResponseBody
    @ApiOperation(value="批量插入User", notes="批量插入User")
    @ApiImplicitParam(paramType="data", name = "userList", value = "", required = true, dataType = "List<User>")
    public boolean batchSaveUser(@RequestBody List<User> userList){
        return userService.saveBatch(userList);
    }

    /***
     * 批量修改User
     * @param userList
     * @return
     */
    @PostMapping("/batch/update")
    @ResponseBody
    @ApiOperation(value="批量修改User", notes="批量修改User")
    @ApiImplicitParam(paramType="data", name = "userList", value = "", required = true, dataType = "List<User>")
    public boolean batchUpdateUser(@RequestBody List<User> userList){
        return userService.batchUpdateUser(userList);
    }

    /***
     * 批量删除User
     * @param user
     * @return
     */
    @PostMapping("/batch/delete")
    @ResponseBody
    @ApiOperation(value="批量删除删除User", notes="批量删除删除User")
    @ApiImplicitParam(paramType="data", name = "user", value = "", required = true, dataType = "User")
    public int batchRemoveUser(@RequestBody User user){
        return userService.batchRemoveUser(user);
    }
}
