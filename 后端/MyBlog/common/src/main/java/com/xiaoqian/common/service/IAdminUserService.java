package com.xiaoqian.common.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaoqian.common.domain.ResponseResult;
import com.xiaoqian.common.domain.pojo.User;
import com.xiaoqian.common.domain.vo.AdminUserInfo;
import com.xiaoqian.common.domain.vo.RouterVo;


public interface IAdminUserService extends IService<User> {
    ResponseResult<AdminUserInfo> queryAdminInfo();

    ResponseResult<RouterVo> getRouters();
}
