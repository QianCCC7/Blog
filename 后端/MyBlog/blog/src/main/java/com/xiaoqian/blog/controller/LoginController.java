package com.xiaoqian.blog.controller;

import com.xiaoqian.common.domain.ResponseResult;
import com.xiaoqian.common.domain.dto.UserDTO;
import com.xiaoqian.common.domain.vo.LoginUserVo;
import com.xiaoqian.common.service.ILoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = "登录相关接口")
@RequiredArgsConstructor
public class LoginController {
    private final ILoginService loginService;

    @ApiOperation("用户登录接口")
    @PostMapping("/login")
    public ResponseResult<LoginUserVo> login(@RequestBody UserDTO userDTO) {
        return loginService.login(userDTO);
    }

    @ApiOperation("用户退出登录接口")
    @PostMapping("/logout")
    public ResponseResult<Object> logout() {
        return loginService.logout();
    }
}
