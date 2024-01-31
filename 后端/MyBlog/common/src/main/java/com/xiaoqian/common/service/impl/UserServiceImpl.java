package com.xiaoqian.common.service.impl;

import com.xiaoqian.common.domain.pojo.User;
import com.xiaoqian.common.mapper.UserMapper;
import com.xiaoqian.common.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author QianCCC
 * @since 2024-01-28
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
}
