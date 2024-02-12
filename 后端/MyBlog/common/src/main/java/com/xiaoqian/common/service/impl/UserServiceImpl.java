package com.xiaoqian.common.service.impl;

import com.xiaoqian.common.domain.ResponseResult;
import com.xiaoqian.common.domain.dto.RegisterUserDTO;
import com.xiaoqian.common.domain.dto.UserDTO;
import com.xiaoqian.common.domain.pojo.User;
import com.xiaoqian.common.domain.vo.UserDetailVo;
import com.xiaoqian.common.enums.HttpCodeEnum;
import com.xiaoqian.common.enums.RegisterCodeEnum;
import com.xiaoqian.common.exception.LoginException;
import com.xiaoqian.common.exception.RegisterException;
import com.xiaoqian.common.exception.SystemException;
import com.xiaoqian.common.mapper.UserMapper;
import com.xiaoqian.common.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaoqian.common.utils.BeanCopyUtils;
import com.xiaoqian.common.utils.UserContext;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Objects;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author QianCCC
 * @since 2024-01-28
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    private final PasswordEncoder passwordEncoder;

    /**
     * 查询当前登录用户信息
     */
    @Override
    public ResponseResult<UserDetailVo> queryUserInfo() {
        // 1. 获取当前登录用户
        Long userId = UserContext.getUserId();
        if (Objects.isNull(userId)) {
            throw new LoginException(HttpCodeEnum.NEED_LOGIN, "当前用户未登录或登录失效，请重新登录");
        }
        // 2. 查询用户信息
        User user = getById(userId);
        if (Objects.isNull(user)) {
            throw new LoginException(HttpCodeEnum.NEED_LOGIN, "当前用户未登录或登录失效，请重新登录");
        }
        // 3. 数据封装
        return ResponseResult.okResult(BeanCopyUtils.copyBean(user, UserDetailVo.class));
    }

    /**
     * 更新当前登录用户信息
     */
    @Override
    public ResponseResult<Object> updateUserInfo(UserDTO user) {
        User newUser = BeanCopyUtils.copyBean(user, User.class);
        newUser.setId(user.getId());
        updateById(newUser);
        return ResponseResult.okResult();
    }

    /**
     * 用户注册
     */
    @Override
    public ResponseResult<Object> register(RegisterUserDTO user) {
        // 1. 数据非空判断
        if (Objects.isNull(user)) {
            throw new SystemException(HttpCodeEnum.SYSTEM_ERROR);
        }
        // 1.1 用户名非空判断
        if (!StringUtils.hasText(user.getUserName())) {
            throw new RegisterException(RegisterCodeEnum.USERNAME_NOT_NULL);
        }
        // 1.2 用户昵称非空判断
        if (!StringUtils.hasText(user.getNickName())) {
            throw new RegisterException(RegisterCodeEnum.NICKNAME_NOT_NULL);
        }
        // 1.3 用户密码非空判断
        if (!StringUtils.hasText(user.getPassword())) {
            throw new RegisterException(RegisterCodeEnum.PASSWORD_NOT_NULL);
        }
        // 1.4 用户邮箱非空判断
        if (!StringUtils.hasText(user.getEmail())) {
            throw new RegisterException(RegisterCodeEnum.EMAIL_NOT_NULL);
        }
        // 2. 数据库数据判重(用户名，用户昵称，用户邮箱不能重复)
        // 2.1 用户名判重
        if (existUserName(user.getUserName())) {
            throw new RegisterException(RegisterCodeEnum.USERNAME_EXIST);
        }
        // 2.2 用户昵称判重
        if (existNickName(user.getNickName())) {
            throw new RegisterException(RegisterCodeEnum.NICKNAME_EXIST);
        }
        // 2.3 用户邮箱判重
        if (existEmail(user.getEmail())) {
            throw new RegisterException(RegisterCodeEnum.EMAIL_EXIST);
        }
        // 3. 密码加密
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        // 4. 存入数据
        save(BeanCopyUtils.copyBean(user, User.class));
        return ResponseResult.okResult();
    }

    /**
     * 用户注册时的用户名判重
     */
    private boolean existUserName(String username) {
       return lambdaQuery().eq(User::getUserName, username).count() > 0;
    }

    /**
     * 用户注册时的用户昵称判重
     */
    private boolean existNickName(String nickname) {
        return lambdaQuery().eq(User::getNickName, nickname).count() > 0;
    }

    /**
     * 用户注册时的用户邮箱判重
     */
    private boolean existEmail(String email) {
        return lambdaQuery().eq(User::getEmail, email).count() > 0;
    }
}
