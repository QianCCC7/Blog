package com.xiaoqian.common.utils;

import com.xiaoqian.common.domain.pojo.LoginUser;
import com.xiaoqian.common.domain.pojo.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserContext {
    /**
     * 获取认证对象
     */
    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    /**
     * 获取当前登录用户(含权限信息等)
     */
    public static LoginUser getLoginUser() {
        return (LoginUser) getAuthentication().getPrincipal();
    }

    /**
     * 获取当前登录用户(不含权限信息等)
     */
    public static User getUser() {
        return getLoginUser().getUser();
    }

    /**
     * 获取当前登录用户的 id
     */
    public static Long getUserId() {
        return getUser().getId();
    }
}
