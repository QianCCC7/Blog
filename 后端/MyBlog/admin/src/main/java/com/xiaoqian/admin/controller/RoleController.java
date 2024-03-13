package com.xiaoqian.admin.controller;

import com.xiaoqian.common.domain.ResponseResult;
import com.xiaoqian.common.domain.dto.RoleDTO;
import com.xiaoqian.common.domain.vo.PageVo;
import com.xiaoqian.common.domain.vo.RoleVo;
import com.xiaoqian.common.query.PageQuery;
import com.xiaoqian.common.service.IRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "角色相关接口")
@RestController
@RequestMapping("/system/role")
@RequiredArgsConstructor
public class RoleController {
    private final IRoleService roleService;

    @ApiOperation("分页查询所有角色")
    @GetMapping("/list")
    public ResponseResult<PageVo<RoleVo>> queryRolePage(PageQuery query,
                                                              @RequestParam(value = "roleName", required = false) String roleName,
                                                              @RequestParam(value = "status", required = false) String status) {
        return roleService.queryRolePage(query, roleName, status);
    }

    @ApiOperation("修改角色的停启用状态")
    @PutMapping("/changeStatus")
    public ResponseResult<Object> updateRoleStatus(@RequestBody RoleDTO role) {
        return roleService.updateRoleStatus(role);
    }
}
