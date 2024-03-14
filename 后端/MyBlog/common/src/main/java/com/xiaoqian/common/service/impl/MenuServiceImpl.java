package com.xiaoqian.common.service.impl;

import com.xiaoqian.common.constants.SystemConstants;
import com.xiaoqian.common.domain.ResponseResult;
import com.xiaoqian.common.domain.pojo.Menu;
import com.xiaoqian.common.domain.pojo.Role;
import com.xiaoqian.common.domain.vo.MenuTreeVo;
import com.xiaoqian.common.domain.vo.MenuVo;
import com.xiaoqian.common.domain.vo.RoleMenuTreeVo;
import com.xiaoqian.common.enums.HttpCodeEnum;
import com.xiaoqian.common.exception.MenuException;
import com.xiaoqian.common.mapper.MenuMapper;
import com.xiaoqian.common.mapper.RoleMapper;
import com.xiaoqian.common.service.IMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaoqian.common.service.IRoleMenuService;
import com.xiaoqian.common.utils.BeanCopyUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 菜单权限表 服务实现类
 * </p>
 *
 * @author QianCCC
 * @since 2024-02-27
 */
@Service
@RequiredArgsConstructor
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {
    private final RoleMapper roleMapper;
    private final IRoleMenuService roleMenuService;

    /**
     * 查询用户的权限信息
     */
    @Override
    public List<String> queryPermissionsInfoByUserId(Long userId) {
        // 1. 判断是否为超级管理员，如果是则返回所有权限
        if (userId == 1L) {
            List<Menu> menuList = lambdaQuery()
                    .in(Menu::getMenuType, SystemConstants.MENU_TYPE_C, SystemConstants.MENU_TYPE_F)
                    .eq(Menu::getStatus, SystemConstants.MENU_STATUS_NORMAL)
                    .list();
            if (CollectionUtils.isEmpty(menuList)) {
                return new ArrayList<>();
            }
            return menuList.stream().map(Menu::getPerms).collect(Collectors.toList());
        }
        // 2. 查询普通管理员的角色集合
        List<Role> roles = roleMapper.queryRoleInfoByUserId(userId);
        if (CollectionUtils.isEmpty(roles)) {
            return new ArrayList<>();
        }
        // 3. 查询普通管理员的角色 id集合
        List<Long> roleIds = roles.stream().map(Role::getId).collect(Collectors.toList());
        // 4. 查询普通管理员菜单集合
        List<Menu> menuList = this.getBaseMapper().queryMenuInfoByRoleIds(roleIds);
        if (CollectionUtils.isEmpty(menuList)) {
            return new ArrayList<>();
        }
        // 5. 封装权限集合
        return menuList.stream()
                .map(Menu::getPerms)
                .collect(Collectors.toList());
    }

    /**
     * 查询超级管理员的菜单信息
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Menu> queryMenuInfoByAdmin() {
        List<Menu> menuList = lambdaQuery()
                .in(Menu::getMenuType, SystemConstants.MENU_TYPE_M, SystemConstants.MENU_TYPE_C)
                .eq(Menu::getStatus, SystemConstants.MENU_STATUS_NORMAL)
                .orderByAsc(Menu::getParentId, Menu::getOrderNum)
                .list();
        return CollectionUtils.isEmpty(menuList) ? new ArrayList<>() : menuList;
    }

    /**
     * 查询普通管理员的菜单信息
     */
    @Override
    public List<Menu> queryMenuInfoByUserId(Long userId) {
        // 1. 查询用户的角色集合
        List<Role> roles = roleMapper.queryRoleInfoByUserId(userId);
        if (CollectionUtils.isEmpty(roles)) {
            return new ArrayList<>();
        }
        // 2. 查询用户的角色 id集合
        List<Long> roleIds = roles.stream().map(Role::getId).collect(Collectors.toList());
        List<Menu> menuList = this.getBaseMapper().queryMenuInfoByRoleIds2(roleIds);
        return CollectionUtils.isEmpty(menuList) ? new ArrayList<>() : menuList;
    }

    /**
     * 管理端查询菜单列表(不分页)
     */
    @SuppressWarnings("unchecked")
    @Override
    public ResponseResult<List<MenuVo>> queryMenuList(String menuName, String status) {
        List<Menu> menuList = lambdaQuery()
                .like(StringUtils.hasText(menuName), Menu::getMenuName, menuName)
                .eq(StringUtils.hasText(status), Menu::getStatus, status)
                .orderByAsc(Menu::getParentId, Menu::getOrderNum)
                .list();
        if (CollectionUtils.isEmpty(menuList)) {
            return ResponseResult.okEmptyResult();
        }
        return ResponseResult.okResult(BeanCopyUtils.copyBeanList(menuList, MenuVo.class));
    }

    /**
     * 管理端新增菜单
     */
    @Override
    public ResponseResult<Object> addMenu(Menu menu) {
        return ResponseResult.okResult(save(menu));
    }

    /**
     * 根据菜单id查询菜单
     */
    @Override
    public ResponseResult<MenuVo> queryMenuById(Long menuId) {
        Menu menu = getById(menuId);
        if (Objects.isNull(menu)) {
            return ResponseResult.okResult();
        }
        return ResponseResult.okResult(BeanCopyUtils.copyBean(menu, MenuVo.class));
    }

    /**
     * 管理端修改菜单信息
     */
    @Override
    public ResponseResult<Object> updateMenu(Menu menu) {
        if (menu.getParentId().equals(menu.getId())) {
            throw new MenuException(HttpCodeEnum.MENU_SET_ERROR);
        }
        updateById(menu);
        return ResponseResult.okResult();
    }

    /**
     * 删除菜单
     */
    @Override
    public ResponseResult<Object> deleteMenu(Long menuId) {
        if (hasChild(menuId)) {
            throw new MenuException(HttpCodeEnum.MENU_DEL_ERROR);
        }

        return ResponseResult.okResult(removeById(menuId));
    }

    /**
     * 判断菜单是否存在子菜单
     */
    private boolean hasChild(Long menuId) {
        Menu menu = lambdaQuery()
                .eq(Menu::getParentId, menuId)
                .one();
        return !Objects.isNull(menu);
    }

    /**
     * 新增角色时需要查询菜单树
     */
    @Override
    public ResponseResult<List<MenuTreeVo>> queryMenuTree() {
        List<MenuTreeVo> menuTreeVoList = getMenuChildren(0L);
        if (CollectionUtils.isEmpty(menuTreeVoList)) {
            return ResponseResult.okEmptyResult();
        }
        return ResponseResult.okResult(menuTreeVoList);
    }

    /**
     * 递归查询子菜单
     * @param id 父级菜单id
     */
    private List<MenuTreeVo> getMenuChildren(Long id) {
        // 1. 查询出所有作为父节点为 id的菜单
        List<Menu> menuList = lambdaQuery().eq(Menu::getParentId, id).list();
        if (CollectionUtils.isEmpty(menuList)) {
            return new ArrayList<>();
        }
        // 2. 属性拷贝
        List<MenuTreeVo> menuTreeVoList = new ArrayList<>(menuList.size());
        for (Menu menu : menuList) {
            MenuTreeVo vo = new MenuTreeVo();
            // 2.1 设置id
            vo.setId(menu.getId());
            // 2.2 设置名称
            vo.setLabel(menu.getMenuName());
            // 2.3 父菜单id
            vo.setParentId(menu.getParentId());
            menuTreeVoList.add(vo);
        }
        // 3. 递归查询子菜单
        for (MenuTreeVo menuTreeVo : menuTreeVoList) {
            List<MenuTreeVo> children = getMenuChildren(menuTreeVo.getId());
            if (!CollectionUtils.isEmpty(children)) {
                menuTreeVo.setChildren(children);
            }
        }
        return menuTreeVoList;
    }

    /**
     * 根据角色id查询角色对应的菜单树
     */
    @Override
    public ResponseResult<RoleMenuTreeVo> queryMenuTreeByRoleId(Long roleId) {
        // 1. 通过角色 id查找对应角色的所有菜单 id
        List<Long> menuIds = roleMenuService.queryMenuIdsByRoleId(roleId);
        if (CollectionUtils.isEmpty(menuIds)) {
            return ResponseResult.okEmptyResult();
        }
        // 2. 查询所有菜单信息
        List<MenuTreeVo> menuTreeVoList = getMenuChildren(0L);
        // 3. 封装返回结果
        RoleMenuTreeVo roleMenuTreeVo = new RoleMenuTreeVo();
        // 3.1 封装该用户对应的菜单 id
        List<Long> roleIds = roleMenuService.queryMenuIdsByRoleId(roleId);
        if (!CollectionUtils.isEmpty(roleIds)) {
            roleMenuTreeVo.setCheckedKeys(roleIds);
        }
        // 3.2 返回菜单树
        roleMenuTreeVo.setMenus(menuTreeVoList);
        return ResponseResult.okResult(roleMenuTreeVo);
    }
}
