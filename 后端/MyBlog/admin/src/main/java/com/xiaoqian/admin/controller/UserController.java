package com.xiaoqian.admin.controller;


import com.xiaoqian.common.domain.ResponseResult;
import com.xiaoqian.common.domain.dto.UserDTO;
import com.xiaoqian.common.domain.vo.PageVo;
import com.xiaoqian.common.domain.vo.UserDetailVo;
import com.xiaoqian.common.domain.vo.UserRoleVo;
import com.xiaoqian.common.query.PageQuery;
import com.xiaoqian.common.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @ApiOperation("新增用户")
    @PostMapping
    public ResponseResult<Object> addUser(@RequestBody UserDTO userDTO) {
        return userService.addUser(userDTO);
    }

    @ApiOperation("删除用户")
    @DeleteMapping("/{userIds}")
    public ResponseResult<Object> removeUserById(@PathVariable("userIds") List<Long> userIds) {
        return userService.removeUserByIds(userIds);
    }

    @ApiOperation("根据用户id查询用户信息")
    @GetMapping("/{userId}")
    public ResponseResult<UserRoleVo> queryUserInfoById(@PathVariable("userId") Long userId) {
        return userService.queryUserInfoById(userId);
    }

    @ApiOperation("修改用户信息")
    @PutMapping
    public ResponseResult<Object> updateUser(@RequestBody UserDTO userDTO) {
        return userService.updateUser(userDTO);
    }

    @ApiOperation("直接修改用户状态")
    @PutMapping("/changeStatus")
    public ResponseResult<Object> updateUserStatus(@RequestBody UserDTO userDTO) {
        return userService.updateUserStatus(userDTO);
    }
}
