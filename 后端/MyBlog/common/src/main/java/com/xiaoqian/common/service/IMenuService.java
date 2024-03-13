package com.xiaoqian.common.service;

import com.xiaoqian.common.domain.ResponseResult;
import com.xiaoqian.common.domain.pojo.Menu;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaoqian.common.domain.vo.MenuVo;

import java.util.List;

/**
 * <p>
 * 菜单权限表 服务类
 * </p>
 *
 * @author QianCCC
 * @since 2024-02-27
 */
public interface IMenuService extends IService<Menu> {

    List<String> queryPermissionsInfoByUserId(Long userId);

    List<Menu> queryMenuInfoByAdmin();

    List<Menu> queryMenuInfoByUserId(Long userId);

    ResponseResult<List<MenuVo>> queryMenuList(String menuName, String status);

    ResponseResult<Object> addMenu(Menu menu);

    ResponseResult<MenuVo> queryMenuById(Long menuId);

    ResponseResult<Object> updateMenu(Menu menu);

    ResponseResult<Object> deleteMenu(Long menuId);
}
