package com.xiaoqian.common.handle;

import com.alibaba.fastjson.JSON;
import com.xiaoqian.common.domain.ResponseResult;
import com.xiaoqian.common.enums.HttpCodeEnum;
import com.xiaoqian.common.utils.WebUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 授权异常处理器
 */
@Slf4j
@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) {
        accessDeniedException.printStackTrace();// 用户捕捉是什么类型的异常
        log.debug("授权异常处理器【{}】捕捉到授权异常", "AccessDeniedHandlerImpl");
        String errorResultStr = JSON.toJSONString(ResponseResult.errorResult(HttpCodeEnum.NO_OPERATOR_AUTH));
        WebUtils.renderString(response, errorResultStr);
    }
}
