package com.xiaoqian.common.service.impl;

import com.xiaoqian.common.utils.UserContext;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class PermissionService {

    public boolean hasPermission(String permission) {
        // 1. 如果是超级管理员，直接放行
        if (UserContext.getUserId() == 1) {
            return true;
        }
        // 2. 获取当前登录用户的权限
        Set<String> permissions = new HashSet<>(UserContext.getLoginUser().getPermissions());
            return permissions.contains(permission);
    }
}
