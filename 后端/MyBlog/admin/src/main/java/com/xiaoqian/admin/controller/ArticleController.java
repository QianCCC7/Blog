package com.xiaoqian.admin.controller;

import com.xiaoqian.common.domain.ResponseResult;
import com.xiaoqian.common.domain.dto.ArticleDTO;
import com.xiaoqian.common.domain.pojo.Article;
import com.xiaoqian.common.domain.vo.CategoryVo;
import com.xiaoqian.common.domain.vo.PageVo;
import com.xiaoqian.common.domain.vo.TagVo;
import com.xiaoqian.common.query.PageQuery;
import com.xiaoqian.common.service.IArticleService;
import com.xiaoqian.common.service.IUploadService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Api(tags = "写博文相关接口")
@RestController
@RequiredArgsConstructor
public class ArticleController {
    private final IArticleService articleService;
    private final IUploadService uploadService;

    @ApiOperation("查询可用的文章分类")
    @GetMapping("/content/category/listAllCategory")
    public ResponseResult<List<CategoryVo>> queryAllCategories() {
        return articleService.queryAllCategories();
    }

    @ApiOperation("查询可用的文章标签")
    @GetMapping("/content/tag/listAllTag")
    public ResponseResult<List<TagVo>> queryAllTags() {
        return articleService.queryAllTags();
    }

    @ApiOperation("上传文章缩略图")
    @PostMapping("/upload")
    public ResponseResult<Object> upload(@RequestPart("img")MultipartFile multipartFile) {
        return uploadService.upload(multipartFile);
    }

    @ApiOperation("新增博文")
    @PostMapping("/content/article")
    public ResponseResult<Object> postArticle(@RequestBody ArticleDTO article) {
        return articleService.postArticle(article);
    }

    @ApiOperation("分页查询文章列表")
    @GetMapping("/content/article/list")
    public ResponseResult<PageVo<Article>> queryArticlePage(PageQuery query,
                                                            @RequestParam(value = "title", required = false) String title,
                                                            @RequestParam(value = "summary", required = false) String summary) {
        return articleService.queryArticlePageAdmin(query, title, summary);
    }

    @ApiOperation("根据id查询文章")
    @GetMapping("/content/article/{id}")
    public ResponseResult<Article> getArticleById(@PathVariable("id") Long id) {
        return articleService.getArticleById(id);
    }

    @ApiOperation("修改文章信息")
    @PutMapping("/content/article")
    public ResponseResult<Object> updateArticle(@RequestBody Article article) {
        return articleService.updateArticle(article);
    }
}
