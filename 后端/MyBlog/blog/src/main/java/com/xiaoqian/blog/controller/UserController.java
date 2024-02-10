package com.xiaoqian.blog.controller;

import com.xiaoqian.common.domain.ResponseResult;
import com.xiaoqian.common.domain.vo.UserDetailVo;
import com.xiaoqian.common.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@Api(tags = "用户信息相关接口")
@RequiredArgsConstructor
public class UserController {
    private final IUserService userService;

    @ApiOperation("查询当前登录用户信息")
    @GetMapping("/userInfo")
    public ResponseResult<UserDetailVo> queryUserInfo() {
        return userService.queryUserInfo();
    }
}
