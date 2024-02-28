package com.xiaoqian.common.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaoqian.common.domain.ResponseResult;
import com.xiaoqian.common.domain.pojo.User;
import com.xiaoqian.common.domain.vo.AdminUserInfo;
import com.xiaoqian.common.domain.vo.LoginUserInfo;
import com.xiaoqian.common.mapper.UserMapper;
import com.xiaoqian.common.service.IAdminUserService;
import com.xiaoqian.common.service.IMenuService;
import com.xiaoqian.common.service.IRoleService;
import com.xiaoqian.common.utils.BeanCopyUtils;
import com.xiaoqian.common.utils.UserContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}
