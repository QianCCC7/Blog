package com.xiaoqian.admin.controller;

import com.xiaoqian.common.domain.ResponseResult;
import com.xiaoqian.common.domain.vo.CategoryVo;
import com.xiaoqian.common.domain.vo.PageVo;
import com.xiaoqian.common.query.PageQuery;
import com.xiaoqian.common.service.ICategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@Api(tags = "分类相关接口")
@RestController
@RequestMapping("/content/category")
@RequiredArgsConstructor
public class CategoryController {
    private final ICategoryService categoryService;

    @PreAuthorize("@permissionService.hasPermission('content:category:export')")
    @ApiOperation("excel文件导出")
    @GetMapping("/export")
    public void exportExcel(HttpServletResponse response) {
        categoryService.exportExcel(response);
    }

    @ApiOperation("分页查询分页列表")
    @GetMapping("/list")
    public ResponseResult<PageVo<CategoryVo>> queryCategoryPage(PageQuery pageQuery,
                                                                @RequestParam(value = "name", required = false) String name,
                                                                @RequestParam(value = "status", required = false) String status) {
        return categoryService.queryCategoryPage(pageQuery, name, status);
    }
}
