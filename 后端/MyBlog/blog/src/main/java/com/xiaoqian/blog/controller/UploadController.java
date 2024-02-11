package com.xiaoqian.blog.controller;

import com.xiaoqian.common.domain.ResponseResult;
import com.xiaoqian.common.service.IUploadService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/upload")
@Api(tags = "文件上传相关接口")
@RequiredArgsConstructor
public class UploadController {
    private final IUploadService uploadService;

    @ApiOperation("上传文件")
    @PostMapping
    public ResponseResult<Object> upload(MultipartFile multipartFile) {
        return uploadService.upload(multipartFile);
    }
}
