package com.xiaoqian.common.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaoqian.common.domain.ResponseResult;
import com.xiaoqian.common.domain.pojo.User;
import com.xiaoqian.common.domain.vo.AdminUserInfo;
import com.xiaoqian.common.domain.vo.MenuVo;
import com.xiaoqian.common.domain.vo.RouterVo;

import java.util.List;

public interface IAdminUserService extends IService<User> {
    ResponseResult<AdminUserInfo> queryAdminInfo();

    ResponseResult<RouterVo> getRouters();
}
