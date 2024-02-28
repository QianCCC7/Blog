package com.xiaoqian.admin.controller;

import com.xiaoqian.common.domain.ResponseResult;
import com.xiaoqian.common.domain.vo.AdminUserInfo;
import com.xiaoqian.common.domain.vo.MenuVo;
import com.xiaoqian.common.domain.vo.RouterVo;
import com.xiaoqian.common.service.IAdminUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(tags = "管理员相关接口")
@RequiredArgsConstructor
public class AdminUserController {
    private final IAdminUserService adminUserService;

    @ApiOperation("查询管理员用户信息")
    @GetMapping("/getInfo")
    public ResponseResult<AdminUserInfo> queryAdminInfo() {
        return adminUserService.queryAdminInfo();
    }

    @ApiOperation("查询管理员能访问的菜单数据以实现动态路由")
    @GetMapping("/getRouters")
    public ResponseResult<RouterVo> getRouters() {
        return adminUserService.getRouters();
    }
}
