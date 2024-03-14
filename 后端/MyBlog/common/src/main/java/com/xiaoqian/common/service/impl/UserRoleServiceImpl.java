package com.xiaoqian.common.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xiaoqian.common.domain.dto.UserDTO;
import com.xiaoqian.common.domain.pojo.UserRole;
import com.xiaoqian.common.mapper.UserRoleMapper;
import com.xiaoqian.common.service.IUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户和角色关联表 服务实现类
 * </p>
 *
 * @author xiaoqian
 * @since 2024-03-14
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {

    @Override
    public void addUserRole(UserDTO userDTO) {
        Long userId = userDTO.getId();
        List<Long> roleIds = userDTO.getRoleIds();
        if (CollectionUtils.isEmpty(roleIds)) {
            return;
        }
        List<UserRole> userRoleList = roleIds.stream()
                .map(roleId -> new UserRole().setUserId(userId).setRoleId(roleId))
                .collect(Collectors.toList());
        saveBatch(userRoleList);
    }

    @Override
    public void removeUserRoleByUserIds(List<Long> userIds) {
        LambdaQueryWrapper<UserRole> wrapper = new LambdaQueryWrapper<>();
        wrapper.in(UserRole::getUserId, userIds);
        remove(wrapper);
    }

    /**
     * 根据用户id查询对应角色id集合
     */
    @Override
    public List<Long> queryRolesByUserId(Long userId) {
        List<UserRole> userRoleList = lambdaQuery()
                .eq(UserRole::getUserId, userId)
                .list();
        if (CollectionUtils.isEmpty(userRoleList)) {
            return new ArrayList<>();
        }
        return userRoleList.stream()
                .map(UserRole::getRoleId)
                .collect(Collectors.toList());
    }

    /**
     * 更新用户角色关联表
     */
    @Override
    public void updateUserRole(UserDTO userDTO) {
        Long userId = userDTO.getId();
        List<Long> roleIds = userDTO.getRoleIds();
        LambdaQueryWrapper<UserRole> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserRole::getUserId, userId);
        remove(wrapper);
        List<UserRole> userRoleList = roleIds.stream()
                .map(roleId -> new UserRole().setRoleId(roleId).setUserId(userId))
                .collect(Collectors.toList());
        saveBatch(userRoleList);
    }
}
