package com.xiaoqian.common.service;

import com.xiaoqian.common.domain.ResponseResult;
import org.springframework.web.multipart.MultipartFile;

public interface IUploadService {
    ResponseResult<Object> upload(MultipartFile multipartFile);
}
