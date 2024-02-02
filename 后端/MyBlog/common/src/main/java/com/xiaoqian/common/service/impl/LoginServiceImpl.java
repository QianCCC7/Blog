package com.xiaoqian.common.service.impl;

import com.xiaoqian.common.domain.ResponseResult;
import com.xiaoqian.common.domain.pojo.LoginUser;
import com.xiaoqian.common.domain.pojo.User;
import com.xiaoqian.common.domain.vo.LoginUserInfo;
import com.xiaoqian.common.domain.vo.LoginUserVo;
import com.xiaoqian.common.service.ILoginService;
import com.xiaoqian.common.utils.BeanCopyUtils;
import com.xiaoqian.common.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
@SuppressWarnings({"rawtypes", "unchecked"})
public class LoginServiceImpl implements ILoginService {
    private final AuthenticationManager authenticationManager;
    private final RedisTemplate redisTemplate;

    /**
     * 用户登录
     */
    @Override
    public ResponseResult<LoginUserVo> login(User user) {
        // 1. 封装 UsernamePasswordAuthenticationToken
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword());
        // 2. 通过 AuthenticationManager进行认证
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        if (Objects.isNull(authentication)) {
            throw new RuntimeException("用户名或密码错误！");
        }
        // 3. 调用重写的 UserDetailsService实现类的 loadUserByUsername()方法封装用户信息以及用户权限信息
        // 4. 获取 userId生成 token
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long userId = loginUser.getUser().getId();
        String jwt = JwtUtils.createJWT(userId.toString());
        // 5. 把用户信息存入 redis
        String redisLoginUserPrefix = "login-user:";
        redisTemplate.opsForValue().set(redisLoginUserPrefix + userId, loginUser);
        // 6. 封装 token和用户信息返回
        LoginUserInfo userInfo = BeanCopyUtils.copyBean(loginUser.getUser(), LoginUserInfo.class);
        LoginUserVo loginUserVo = new LoginUserVo(jwt, userInfo);
        return ResponseResult.okResult(loginUserVo);
    }
}
