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

    @ApiOperation("新增角色")
    @PostMapping
    public ResponseResult<Object> addRole(@RequestBody RoleDTO role) {
        return roleService.addRole(role);
    }

    @ApiOperation("根据角色id查询对应的角色")
    @GetMapping("/{roleId}")
    public ResponseResult<RoleVo> queryRoleInfoById(@PathVariable("roleId") Long roleId) {
        return roleService.queryRoleInfoById(roleId);
    }

    @ApiOperation("更新角色信息接口")
    @PutMapping
    public ResponseResult<Object> updateRoleInfo(@RequestBody RoleDTO roleDTO) {
        return roleService.updateRoleInfo(roleDTO);
    }

    @ApiOperation("删除角色")
    @DeleteMapping("/{id}")
    public ResponseResult<Object> removeRoleById(@PathVariable("id") Long roleId) {
        return roleService.removeRoleById(roleId);
    }

    @ApiOperation("查询角色列表")
    @GetMapping("/listAllRole")
    public ResponseResult<List<RoleVo>> queryRoleList() {
        return roleService.queryRoleList();
    }
}
