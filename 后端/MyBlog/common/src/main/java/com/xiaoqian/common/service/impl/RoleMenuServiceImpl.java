package com.xiaoqian.common.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaoqian.common.domain.dto.RoleDTO;
import com.xiaoqian.common.domain.pojo.RoleMenu;
import com.xiaoqian.common.mapper.RoleMenuMapper;
import com.xiaoqian.common.service.IRoleMenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

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
}
