package com.xiaoqian.blog.controller;


import com.xiaoqian.common.domain.ResponseResult;
import com.xiaoqian.common.domain.pojo.Article;
import com.xiaoqian.common.domain.vo.HotArticleVo;
import com.xiaoqian.common.service.IArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * <p>
 * 文章表 前端控制器
 * </p>
 *
 * @author QianCCC
 * @since 2023-12-24
 */
@RestController
@Api(tags = "文章相关接口")
@RequestMapping("/article")
@RequiredArgsConstructor
public class ArticleController {
    private final IArticleService articleService;

    @ApiOperation("查询热门文章的接口")
    @GetMapping("/hotArticleList")
    public ResponseResult<List<HotArticleVo>> hotArticleList() {
        return articleService.hotArticleList();
    }
}
