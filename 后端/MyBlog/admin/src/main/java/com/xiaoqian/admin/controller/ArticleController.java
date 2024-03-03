package com.xiaoqian.admin.controller;

import com.xiaoqian.common.domain.ResponseResult;
import com.xiaoqian.common.domain.vo.CategoryVo;
import com.xiaoqian.common.domain.vo.TagVo;
import com.xiaoqian.common.service.IArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "写博文相关接口")
@RestController
@RequestMapping("/content")
@RequiredArgsConstructor
public class ArticleController {
    private final IArticleService articleService;

    @ApiOperation("查询可用的文章分类")
    @GetMapping("/category/listAllCategory")
    public ResponseResult<List<CategoryVo>> queryAllCategories() {
        return articleService.queryAllCategories();
    }

    @ApiOperation("查询可用的文章标签")
    @GetMapping("/tag/listAllTag")
    public ResponseResult<List<TagVo>> queryAllTags() {
        return articleService.queryAllTags();
    }
}
