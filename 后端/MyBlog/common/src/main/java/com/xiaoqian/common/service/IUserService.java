package com.xiaoqian.common.service;

import com.xiaoqian.common.domain.ResponseResult;
import com.xiaoqian.common.domain.dto.RegisterUserDTO;
import com.xiaoqian.common.domain.dto.UserDTO;
import com.xiaoqian.common.domain.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaoqian.common.domain.vo.UserDetailVo;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author QianCCC
 * @since 2024-01-28
 */
public interface IUserService extends IService<User> {

    ResponseResult<UserDetailVo> queryUserInfo();

    ResponseResult<Object> updateUserInfo(UserDTO user);

    ResponseResult<Object> register(RegisterUserDTO user);
}
