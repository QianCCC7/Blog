package com.xiaoqian.admin.controller;

import com.xiaoqian.common.domain.ResponseResult;
import com.xiaoqian.common.domain.pojo.Menu;
import com.xiaoqian.common.domain.vo.MenuVo;
import com.xiaoqian.common.service.IMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
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

}
