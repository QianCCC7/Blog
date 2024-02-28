package com.xiaoqian.common.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaoqian.common.domain.ResponseResult;
import com.xiaoqian.common.domain.pojo.Menu;
import com.xiaoqian.common.domain.pojo.User;
import com.xiaoqian.common.domain.vo.AdminUserInfo;
import com.xiaoqian.common.domain.vo.LoginUserInfo;
import com.xiaoqian.common.domain.vo.MenuVo;
import com.xiaoqian.common.domain.vo.RouterVo;
import com.xiaoqian.common.mapper.UserMapper;
import com.xiaoqian.common.service.IAdminUserService;
import com.xiaoqian.common.service.IMenuService;
import com.xiaoqian.common.service.IRoleService;
import com.xiaoqian.common.utils.BeanCopyUtils;
import com.xiaoqian.common.utils.UserContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminUserServiceImpl extends ServiceImpl<UserMapper, User> implements IAdminUserService {
    private final IMenuService menuService;
    private final IRoleService roleService;

    /**
     * 管理员登录后，返回的管理员的信息
     */
    @Override
    public ResponseResult<AdminUserInfo> queryAdminInfo() {
        // 1. 获取当前登录用户
        Long userId = UserContext.getUserId();
        // 2. 查询用户的角色信息
        List<String> roles = roleService.queryRoleInfoByUserId(userId);
        // 3. 查询用户的权限信息
        List<String> permissions = menuService.queryPermissionsInfoByUserId(userId);
        // 4. 查询用户的基础信息
        User user = UserContext.getUser();
        LoginUserInfo userInfo = BeanCopyUtils.copyBean(user, LoginUserInfo.class);
        // 5. 封装返回结果
        AdminUserInfo adminUserInfo = new AdminUserInfo();
        adminUserInfo.setUser(userInfo);
        adminUserInfo.setPermissions(permissions);
        adminUserInfo.setRoles(roles);
        return ResponseResult.okResult(adminUserInfo);
    }

    /**
     * 查询管理员能访问的菜单数据以实现动态路由
     */
    @Override
    public ResponseResult<RouterVo> getRouters() {
        // 1. 获取当前登录用户
        Long userId = UserContext.getUserId();
        List<Menu> menuList;
        if (userId == 1L) {
            // 2 是超级管理员，返回所有可用的权限菜单
            menuList = menuService.queryMenuInfoByAdmin();
        } else {
            // 3 是普通管理员，返回对应具有的权限菜单
            menuList = menuService.queryMenuInfoByUserId(userId);
        }
        if (CollectionUtils.isEmpty(menuList)) {
            return ResponseResult.okResult();
        }
        return ResponseResult.okResult(new RouterVo(getMenuVoList(menuList)));
    }

    /**
     * 根据菜单列表获取其对应的 vo列表数据
     */
    private List<MenuVo> getMenuVoList(List<Menu> menuList) {
        List<MenuVo> menuVoList = BeanCopyUtils.copyBeanList(menuList, MenuVo.class);
        for (MenuVo menuVo : menuVoList) {
            Long menuId = menuVo.getId();
            List<MenuVo> children = getMenuChildren(menuId);
            if (!CollectionUtils.isEmpty(children)) {
                menuVo.setChildren(children);
            }
        }
        return menuVoList;
    }

    /**
     * 递归查询对应菜单的子菜单
     */
    @SuppressWarnings("unchecked")
    private List<MenuVo> getMenuChildren(Long rootMenuId) {
        List<Menu> commentList = menuService.lambdaQuery()
                .eq(Menu::getParentId, rootMenuId)
                .orderByAsc(Menu::getParentId, Menu::getOrderNum)
                .list();
        return getMenuVoList(commentList);
    }

}
