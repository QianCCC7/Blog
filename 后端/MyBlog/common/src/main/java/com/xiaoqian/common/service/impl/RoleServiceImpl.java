package com.xiaoqian.common.service.impl;

import com.xiaoqian.common.domain.pojo.Role;
import com.xiaoqian.common.mapper.RoleMapper;
import com.xiaoqian.common.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
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
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

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
}
