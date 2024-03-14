package com.xiaoqian.admin.controller;


import com.xiaoqian.common.domain.ResponseResult;
import com.xiaoqian.common.domain.vo.PageVo;
import com.xiaoqian.common.domain.vo.UserDetailVo;
import com.xiaoqian.common.query.PageQuery;
import com.xiaoqian.common.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "用户相关接口")
@RequestMapping("/system/user")
@RequiredArgsConstructor
public class UserController {
    private final IUserService userService;

    @ApiOperation("分页查询所有用户")
    @GetMapping("/list")
    public ResponseResult<PageVo<UserDetailVo>> queryUserInfoPage(PageQuery pageQuery,
                                                                  @RequestParam(value = "userName", required = false) String username,
                                                                  @RequestParam(value = "phonenumber", required = false) String phonenumber,
                                                                  @RequestParam(value = "status", required = false) String status) {
        return userService.queryUserInfoPage(pageQuery, username, phonenumber, status);
    }
}
