package com.xiaoqian.common.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaoqian.common.domain.ResponseResult;
import com.xiaoqian.common.domain.dto.RoleDTO;
import com.xiaoqian.common.domain.pojo.Role;
import com.xiaoqian.common.domain.vo.PageVo;
import com.xiaoqian.common.domain.vo.RoleVo;
import com.xiaoqian.common.mapper.RoleMapper;
import com.xiaoqian.common.query.PageQuery;
import com.xiaoqian.common.service.IRoleMenuService;
import com.xiaoqian.common.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaoqian.common.utils.BeanCopyUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <p>
 * 角色信息表 服务实现类
 * </p>
 *
 * @author QianCCC
 * @since 2024-02-27
 */
@Service
@RequiredArgsConstructor
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {
    private final IRoleMenuService roleMenuService;

    /**
     * 查询用户的角色信息
     */
    @Override
    public List<String> queryRoleInfoByUserId(Long userId) {
        // 1. 判断是否为超级管理员
        if (userId == 1L) {
            // 2. 如果是超级管理员，只需要返回 admin
            List<String> list = new ArrayList<>(1);
            list.add("admin");
            return list;
        }
        // 3. 查询普通用户的角色信息
        List<Role> roles = this.getBaseMapper().queryRoleInfoByUserId(userId);
        if (CollectionUtils.isEmpty(roles)) {
            return new ArrayList<>();
        }
        // 4. 返回 roleKey信息
        return roles.stream()
                .map(Role::getRoleKey)
                .collect(Collectors.toList());
    }

    /**
     * 分页查询所有角色信息
     */
    @Override
    public ResponseResult<PageVo<RoleVo>> queryRolePage(PageQuery query, String roleName, String status) {
        Page<Role> page = lambdaQuery()
                .like(StringUtils.hasText(roleName), Role::getRoleName, roleName)
                .eq(StringUtils.hasText(status), Role::getStatus, status)
                .page(query.toPage(query.getPageNo(), query.getPageSize()));

        List<Role> records = page.getRecords();
        if (CollectionUtils.isEmpty(records)) {
            return ResponseResult.okEmptyResult();
        }
        records.sort(Comparator.comparingInt(Role::getRoleSort));
        List<RoleVo> roleVoList = BeanCopyUtils.copyBeanList(records, RoleVo.class);
        return ResponseResult.okResult(new PageVo<>(roleVoList, records.size()));
    }

    /**
     * 修改角色的停启用状态
     */
    @Override
    public ResponseResult<Object> updateRoleStatus(RoleDTO role) {
        lambdaUpdate()
                .eq(Role::getId, role.getRoleId())
                .set(Role::getStatus, role.getStatus())
                .update();
        return ResponseResult.okResult();
    }

    /**
     * 新增角色
     */
    @Transactional
    @Override
    public ResponseResult<Object> addRole(RoleDTO role) {
        // 1. 更新角色表
        Role r = BeanCopyUtils.copyBean(role, Role.class);
        save(r);
        // 2. 更新角色菜单表
        role.setRoleId(r.getId());
        roleMenuService.saveRoleMenu(role);
        return ResponseResult.okResult();
    }

    /**
     * 角色id查询对应的角色
     */
    @Override
    public ResponseResult<RoleVo> queryRoleInfoById(Long roleId) {
        Role role = getById(roleId);
        if (Objects.isNull(role)) {
            return ResponseResult.okEmptyResult();
        }
        return ResponseResult.okResult(BeanCopyUtils.copyBean(role, RoleVo.class));
    }

    /**
     * 更新角色信息
     */
    @Transactional
    @Override
    public ResponseResult<Object> updateRoleInfo(RoleDTO roleDTO) {
        // 1. 更新基本信息
        Role role = BeanCopyUtils.copyBean(roleDTO, Role.class);
        updateById(role);
        // 2. 更新角色菜单表
        if (!CollectionUtils.isEmpty(roleDTO.getMenuIds())) {
            roleMenuService.updateRoleMenu(roleDTO);
        }
        return ResponseResult.okResult();
    }

    /**
     * 删除角色
     */
    @Transactional
    @Override
    public ResponseResult<Object> removeRoleById(Long roleId) {
        // 1. 删除基本信息
        removeById(roleId);
        // 2. 删除角色菜单表
        roleMenuService.removeRoleMenu(roleId);
        return ResponseResult.okResult();
    }

    /**
     * 新增角色时需要查询角色列表
     */
    @Override
    public ResponseResult<List<RoleVo>> queryRoleList() {
        List<Role> roleList = list();
        if (CollectionUtils.isEmpty(roleList)) {
            return ResponseResult.okEmptyResult();
        }
        List<RoleVo> roleVoList = BeanCopyUtils.copyBeanList(roleList, RoleVo.class);
        return ResponseResult.okResult(roleVoList);
    }

    @Override
    public List<Role> queryRoles() {
        return list();
    }
}
