package com.xiaoqian.common.service;

import com.xiaoqian.common.domain.ResponseResult;
import com.xiaoqian.common.domain.pojo.User;
import com.xiaoqian.common.domain.vo.LoginUserVo;

public interface ILoginService {
    ResponseResult<LoginUserVo> login(User user);
}
