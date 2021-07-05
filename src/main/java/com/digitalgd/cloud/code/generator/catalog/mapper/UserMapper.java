package com.digitalgd.cloud.code.generator.catalog.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.digitalgd.cloud.code.generator.catalog.model.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
/**
 * <p>
 *  Mapper 接口
 * @since 2021-07-05
 * </p>
 *
 * @author songshuai3223@163.com
 */

@Mapper
public interface UserMapper extends BaseMapper<User> {

}
