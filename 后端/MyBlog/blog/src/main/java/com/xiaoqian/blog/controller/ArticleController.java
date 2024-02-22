package com.xiaoqian.blog.controller;


import com.xiaoqian.common.domain.ResponseResult;
import com.xiaoqian.common.domain.vo.ArticleDetailVo;
import com.xiaoqian.common.domain.vo.ArticleVo;
import com.xiaoqian.common.domain.vo.HotArticleVo;
import com.xiaoqian.common.domain.vo.PageVo;
import com.xiaoqian.common.query.PageQuery;
import com.xiaoqian.common.service.IArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    @ApiOperation("分页查询文章列表")
    @GetMapping("/articleList")
    public ResponseResult<PageVo<ArticleVo>> articleList(PageQuery pageQuery,
                                                         @RequestParam(value = "categoryId", required = false) Long categoryId) {
        return articleService.articleList(pageQuery, categoryId);
    }

    @ApiOperation("根据文章id查询文章详情")
    @GetMapping("/{id}")
    public ResponseResult<ArticleDetailVo> getArticleDetailById(@PathVariable("id") Long id) {
        return articleService.getArticleDetailById(id);
    }

    @ApiOperation("更新文章浏览量")
    @PutMapping("/updateViewCount/{id}")
    public ResponseResult<Object> updateViewCount(@PathVariable("id") Long id) {
        return articleService.updateViewCount(id);
    }
}
