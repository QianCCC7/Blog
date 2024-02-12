package com.xiaoqian.blog.controller;

import com.xiaoqian.common.domain.ResponseResult;
import com.xiaoqian.common.domain.dto.LoginUserDTO;
import com.xiaoqian.common.domain.dto.RegisterUserDTO;
import com.xiaoqian.common.domain.dto.UserDTO;
import com.xiaoqian.common.domain.vo.UserDetailVo;
import com.xiaoqian.common.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Api(tags = "用户相关接口")
@RequiredArgsConstructor
public class UserController {
    private final IUserService userService;

    @ApiOperation("查询当前登录用户信息")
    @GetMapping("/userInfo")
    public ResponseResult<UserDetailVo> queryUserInfo() {
        return userService.queryUserInfo();
    }

    @ApiOperation("更新当前登录用户信息")
    @PutMapping("/userInfo")
    public ResponseResult<Object> updateUserInfo(@RequestBody UserDTO user) {
        return userService.updateUserInfo(user);
    }

    @ApiOperation("用户注册")
    @PostMapping("/register")
    public ResponseResult<Object> register(@RequestBody RegisterUserDTO user) {
        return userService.register(user);
    }
}
