package com.xiaoqian.admin.controller;

import com.xiaoqian.common.annotation.SystemLog;
import com.xiaoqian.common.domain.ResponseResult;
import com.xiaoqian.common.domain.dto.LoginUserDTO;
import com.xiaoqian.common.domain.vo.LoginUserVo;
import com.xiaoqian.common.service.ILoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "管理员登录相关接口")
@RequiredArgsConstructor
public class LoginController {
    private final ILoginService loginService;

    @ApiOperation("管理员登录")
    @PostMapping("/user/login")
    @SystemLog(businessDescription = "管理员登录接口")
    public ResponseResult<LoginUserVo> login(@RequestBody LoginUserDTO loginUserDTO) {
        return loginService.login(loginUserDTO);
    }

    @ApiOperation("管理员退出登录")
    @PostMapping("/user/logout")
    public ResponseResult<Object> logout() {
        return loginService.logout();
    }

}
