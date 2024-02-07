package com.xiaoqian.common.service;

import com.xiaoqian.common.domain.ResponseResult;
import com.xiaoqian.common.domain.dto.UserDTO;
import com.xiaoqian.common.domain.vo.LoginUserVo;

public interface ILoginService {
    ResponseResult<LoginUserVo> login(UserDTO userDTO);

    ResponseResult<Object> logout();
}
