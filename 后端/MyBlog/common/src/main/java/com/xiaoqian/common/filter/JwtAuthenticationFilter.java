package com.xiaoqian.common.filter;

import com.alibaba.fastjson.JSON;
import com.xiaoqian.common.domain.ResponseResult;
import com.xiaoqian.common.domain.pojo.LoginUser;
import com.xiaoqian.common.enums.HttpCodeEnum;
import com.xiaoqian.common.utils.JwtUtils;
import com.xiaoqian.common.utils.WebUtils;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@SuppressWarnings({"rawtypes"})
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final RedisTemplate redisTemplate;

    @Override
    protected void doFilterInternal(HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull FilterChain filterChain) throws ServletException, IOException {
        // 1. 获取请求头中的 token
        String token = request.getHeader("token");
        if (!StringUtils.hasText(token)) {
            // 该接口不需要登录即可访问，直接放行
            filterChain.doFilter(request, response);
            return;
        }
        // 2. 解析 token中的 userId
        Claims claims = null;
        try {
            claims = JwtUtils.parseJWT(token);
        } catch (Exception e) {
            // 出现异常，说明 token过期或者被篡改，即 token非法，响应错误信息给前端
            String errorResultStr = JSON.toJSONString(ResponseResult.errorResult(HttpCodeEnum.NEED_LOGIN));
            WebUtils.renderString(response, errorResultStr);
            e.printStackTrace();
        }
        Assert.notNull(claims, "claims不能为空");
        String userId = claims.getSubject();
        // 3. 从 redis中获取对应用户信息
        String redisLoginUserPrefix = "login-user:";
        LoginUser loginUser = (LoginUser) redisTemplate.opsForValue().get(redisLoginUserPrefix + userId);
        if (Objects.isNull(loginUser)) {
            // 登录过期
            String errorResultStr = JSON.toJSONString(ResponseResult.errorResult(HttpCodeEnum.NEED_LOGIN));
            WebUtils.renderString(response, errorResultStr);
            return;
        }
        // 4. 存入 SecurityContextHolder
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginUser, null, null);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        // 5. 注意放行，让其访问目标接口
        filterChain.doFilter(request, response);
    }
}
