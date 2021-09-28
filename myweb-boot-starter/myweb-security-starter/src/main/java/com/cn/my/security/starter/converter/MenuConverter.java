package com.cn.my.security.starter.converter;

import com.cn.my.security.starter.module.Menu;
import com.cn.my.security.starter.resp.MenuResp;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @author songshuai
 * @date 2021/4/27-16:26
 * @Description
 */
@Mapper(componentModel = "spring")
public interface MenuConverter {

    /**
     * dto转实体TagInfo
     *
     * @param dspMenu
     * @return
     */
    MenuResp tagInfoDto2TagInfo(Menu dspMenu);

    /**
     * dto转实体TagInfo
     *
     * @param dspMenuList
     * @return
     */
    List<MenuResp> tagInfoDtoList2TagInfoList(List<Menu> dspMenuList);
}
