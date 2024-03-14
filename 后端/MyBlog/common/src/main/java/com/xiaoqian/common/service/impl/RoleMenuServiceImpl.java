package com.xiaoqian.common.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaoqian.common.domain.dto.RoleDTO;
import com.xiaoqian.common.domain.pojo.RoleMenu;
import com.xiaoqian.common.mapper.RoleMenuMapper;
import com.xiaoqian.common.service.IRoleMenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 角色和菜单关联表 服务实现类
 * </p>
 *
 * @author xiaoqian
 * @since 2024-03-13
 */
@Service
@Slf4j
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenu> implements IRoleMenuService {

    @Override
    public void saveRoleMenu(RoleDTO role) {
        List<Long> menuIds = role.getMenuIds();
        if (CollectionUtils.isEmpty(menuIds)) {
            return;
        }
        Long roleId = role.getRoleId();
        log.debug("roleId = {}", roleId);
        List<RoleMenu> roleMenuList = menuIds.stream()
                .map(mId -> new RoleMenu().setMenuId(mId).setRoleId(roleId))
                .collect(Collectors.toList());

        saveBatch(roleMenuList);
    }

    @Override
    public List<Long> queryMenuIdsByRoleId(Long roleId) {
        List<RoleMenu> roleMenuList = lambdaQuery().eq(RoleMenu::getRoleId, roleId).list();
        if (CollectionUtils.isEmpty(roleMenuList)) {
            return new ArrayList<>();
        }

        return roleMenuList.stream().map(RoleMenu::getMenuId).collect(Collectors.toList());
    }

    /**
     * 更细角色信息时，需要更新角色菜单表
     */
    @Override
    public void updateRoleMenu(RoleDTO roleDTO) {
        // 1. 获取需要的数据
        Long roleId = roleDTO.getId();
        List<Long> menuIds = roleDTO.getMenuIds();
        // 2. 删除原本的数据
        LambdaQueryWrapper<RoleMenu> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(RoleMenu::getRoleId, roleId);
        remove(wrapper);
        // 3. 新增数据
        List<RoleMenu> roleMenuList = menuIds.stream()
                .map(menuId -> new RoleMenu().setRoleId(roleId).setMenuId(menuId))
                .collect(Collectors.toList());
        saveBatch(roleMenuList);
    }
}
