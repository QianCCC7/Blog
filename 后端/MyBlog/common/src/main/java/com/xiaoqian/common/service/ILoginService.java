package com.xiaoqian.common.service;

import com.xiaoqian.common.domain.ResponseResult;
import com.xiaoqian.common.domain.dto.LoginUserDTO;
import com.xiaoqian.common.domain.vo.LoginUserVo;

public interface ILoginService {
    ResponseResult<LoginUserVo> login(LoginUserDTO loginUserDTO);

    ResponseResult<Object> logout();
}
