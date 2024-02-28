package com.xiaoqian.common.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaoqian.common.domain.ResponseResult;
import com.xiaoqian.common.domain.pojo.User;
import com.xiaoqian.common.domain.vo.AdminUserInfo;

public interface IAdminUserService extends IService<User> {
    ResponseResult<AdminUserInfo> queryAdminInfo();
}
