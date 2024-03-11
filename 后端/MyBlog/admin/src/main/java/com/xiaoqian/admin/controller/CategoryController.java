package com.xiaoqian.admin.controller;

import com.xiaoqian.common.service.ICategoryService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@Api(tags = "分类相关接口")
@RestController
@RequiredArgsConstructor
public class CategoryController {
    private final ICategoryService categoryService;

    @GetMapping("/content/category/export")
    public void exportExcel(HttpServletResponse response) {
        categoryService.exportExcel(response);
    }
}
