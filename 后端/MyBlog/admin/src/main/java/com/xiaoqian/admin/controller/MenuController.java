package com.xiaoqian.admin.controller;

import com.xiaoqian.common.domain.ResponseResult;
import com.xiaoqian.common.domain.pojo.Menu;
import com.xiaoqian.common.domain.vo.MenuVo;
import com.xiaoqian.common.service.IMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "菜单相关接口")
@RestController
@RequestMapping("/system/menu")
@RequiredArgsConstructor
public class MenuController {
    private final IMenuService menuService;

    @ApiOperation("查询菜单列表")
    @GetMapping("/list")
    public ResponseResult<List<MenuVo>> queryMenuList(@RequestParam(value = "menuName", required = false) String menuName,
                                                      @RequestParam(value = "status", required = false) String status) {
        return menuService.queryMenuList(menuName, status);
    }

    @ApiOperation("新增菜单")
    @PostMapping
    public ResponseResult<Object> addMenu(@RequestBody Menu menu) {
        return menuService.addMenu(menu);
    }

    @ApiOperation("根据菜单id查询菜单")
    @GetMapping("/{menuId}")
    public ResponseResult<MenuVo> queryMenuById(@PathVariable("menuId") Long menuId) {
        return menuService.queryMenuById(menuId);
    }

    @ApiOperation("修改菜单")
    @PutMapping
    public ResponseResult<Object> updateMenu(@RequestBody Menu menu) {
        return menuService.updateMenu(menu);
    }

    @ApiOperation("删除菜单")
    @DeleteMapping("/{menuId}")
    public ResponseResult<Object> deleteMenu(@PathVariable("menuId") Long menuId) {
        return menuService.deleteMenu(menuId);
    }

}
