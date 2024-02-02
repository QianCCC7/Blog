package com.xiaoqian.common.handle;

import com.alibaba.fastjson.JSON;
import com.xiaoqian.common.domain.ResponseResult;
import com.xiaoqian.common.enums.HttpCodeEnum;
import com.xiaoqian.common.utils.WebUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 认证异常处理器
 */
@Slf4j
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) {
        log.debug("认证异常处理器捕捉到认证异常:{}", authException.toString());
        String errorResultStr;
        if (authException instanceof BadCredentialsException) {
            errorResultStr = JSON.toJSONString(ResponseResult.errorResult(HttpCodeEnum.LOGIN_ERROR));
        } else if (authException instanceof InsufficientAuthenticationException) {
            errorResultStr = JSON.toJSONString(ResponseResult.errorResult(HttpCodeEnum.NEED_LOGIN));
        } else {
            errorResultStr = JSON.toJSONString(ResponseResult.errorResult(HttpCodeEnum.SYSTEM_ERROR.getCode(), authException.getMessage()));
        }
        WebUtils.renderString(response, errorResultStr);
    }
}
