package com.xiaoqian.common.service.impl;

import com.xiaoqian.common.domain.ResponseResult;
import com.xiaoqian.common.domain.pojo.User;
import com.xiaoqian.common.domain.vo.UserDetailVo;
import com.xiaoqian.common.enums.HttpCodeEnum;
import com.xiaoqian.common.exception.LoginException;
import com.xiaoqian.common.mapper.UserMapper;
import com.xiaoqian.common.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaoqian.common.utils.BeanCopyUtils;
import com.xiaoqian.common.utils.UserContext;
import org.springframework.stereotype.Service;

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
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
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
}
