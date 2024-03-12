package com.xiaoqian.common.service.impl;

import com.xiaoqian.common.constants.SystemConstants;
import com.xiaoqian.common.domain.pojo.LoginUser;
import com.xiaoqian.common.domain.pojo.User;
import com.xiaoqian.common.service.IMenuService;
import com.xiaoqian.common.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {
    private final IUserService userService;
    private final IMenuService menuService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 1. 根据用户名查询用户信息
        User user = userService.lambdaQuery()
                .eq(User::getUserName, username)
                .one();
        // 2. 判断是否查到用户信息
        // 2.1 如果没有查询到，则抛出异常
        if (Objects.isNull(user)) {
            throw new RuntimeException("用户不存在！");
        }
        // 2.2 如果查询到，则返回用户信息为一个 UserDetails对象
        // 2.3 查询用户权限信息并封装(后台用户才需要查询)
        if (user.getType().equals(SystemConstants.IS_ADMIN)) {
            List<String> permissions = menuService.queryPermissionsInfoByUserId(user.getId());
            return new LoginUser(user, CollectionUtils.isEmpty(permissions) ? new ArrayList<>() : permissions);
        }
        return new LoginUser(user, new ArrayList<>());
    }
}
