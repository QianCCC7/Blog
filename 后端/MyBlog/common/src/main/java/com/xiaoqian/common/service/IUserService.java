package com.xiaoqian.common.service;

import com.xiaoqian.common.domain.ResponseResult;
import com.xiaoqian.common.domain.dto.RegisterUserDTO;
import com.xiaoqian.common.domain.dto.UserDTO;
import com.xiaoqian.common.domain.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaoqian.common.domain.vo.PageVo;
import com.xiaoqian.common.domain.vo.UserDetailVo;
import com.xiaoqian.common.query.PageQuery;

import java.util.List;

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

    ResponseResult<PageVo<UserDetailVo>> queryUserInfoPage(PageQuery pageQuery, String username, String phonenumber, String status);

    ResponseResult<Object> addUser(UserDTO userDTO);

    ResponseResult<Object> removeUserByIds(List<Long> userIds);
}
