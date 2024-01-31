package com.xiaoqian.common.service.impl;

import com.xiaoqian.common.domain.pojo.LoginUser;
import com.xiaoqian.common.domain.pojo.User;
import com.xiaoqian.common.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {
    private final IUserService userService;
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
        // TODO 2.3 查询用户权限信息
        return new LoginUser(user);
    }
}
