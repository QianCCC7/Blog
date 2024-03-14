package com.xiaoqian.common.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xiaoqian.common.domain.dto.UserDTO;
import com.xiaoqian.common.domain.pojo.UserRole;
import com.xiaoqian.common.mapper.UserRoleMapper;
import com.xiaoqian.common.service.IUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
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
}
