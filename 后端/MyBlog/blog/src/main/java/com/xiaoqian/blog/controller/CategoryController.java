package com.xiaoqian.blog.controller;


import com.xiaoqian.common.domain.ResponseResult;
import com.xiaoqian.common.domain.vo.CategoryVo;
import com.xiaoqian.common.service.ICategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 分类表 前端控制器
 * </p>
 *
 * @author QianCCC
 * @since 2024-01-17
 */
@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
@Api(tags = "文章分类相关接口")
public class CategoryController {
    private final ICategoryService categoryService;

    @ApiOperation("查询所有文章分类")
    @GetMapping("/getCategoryList")
    public ResponseResult<List<CategoryVo>> getCategoryList() {
        return categoryService.getCategoryList();
    }
}
