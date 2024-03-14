package com.xiaoqian.common.service;

import com.xiaoqian.common.domain.dto.RoleDTO;
import com.xiaoqian.common.domain.pojo.RoleMenu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 角色和菜单关联表 服务类
 * </p>
 *
 * @author xiaoqian
 * @since 2024-03-13
 */
public interface IRoleMenuService extends IService<RoleMenu> {

    void saveRoleMenu(RoleDTO role);

    List<Long> queryMenuIdsByRoleId(Long roleId);

    void updateRoleMenu(RoleDTO roleDTO);

    void removeRoleMenu(Long roleId);
}
