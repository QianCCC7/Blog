package com.xiaoqian.admin.controller;

import com.xiaoqian.common.domain.ResponseResult;
import com.xiaoqian.common.domain.vo.CategoryVo;
import com.xiaoqian.common.domain.vo.TagVo;
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

    @PostMapping("/upload")
    public ResponseResult<Object> upload(@RequestPart("img")MultipartFile multipartFile) {
        return uploadService.upload(multipartFile);
    }
}
