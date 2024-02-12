package com.xiaoqian.common.utils;

import com.xiaoqian.common.domain.pojo.LoginUser;
import com.xiaoqian.common.domain.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Slf4j
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
        // 当用户处于注册状态时，仍然需要字段填充 createBy字段，该字段会从 principal中获取
        // 而注册的用户的 principal为字符串 "anonymousUser"，无法转换为 LoginUser对象，所以会爆错
        // 此时只需要在字段填充时捕获一下异常，并且重新赋值 userId即可
        log.debug("principal:{}", getAuthentication().getPrincipal());
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
