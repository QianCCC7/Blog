package com.xiaoqian.common.service.impl;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.xiaoqian.common.domain.ResponseResult;
import com.xiaoqian.common.enums.HttpCodeEnum;
import com.xiaoqian.common.exception.UploadException;
import com.xiaoqian.common.service.IUploadService;
import com.xiaoqian.common.utils.FilePathUtils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

@Service
@Data
@Slf4j
@ConfigurationProperties(prefix = "oss")
public class UploadServiceImpl implements IUploadService {
    private String accessKey;
    private String secretKey;
    private String bucket;// 存储空间的名字
    private String externalDomainName; // 外链域名

    /**
     * 上传文件
     */
    @Override
    public ResponseResult<Object> upload(MultipartFile multipartFile) {
        // 1. 判断文件类型或者文件大小(文件大小在yaml文件已设置了校验)
        if (Objects.isNull(multipartFile)) {
            throw new UploadException(HttpCodeEnum.FILE_NOT_NULL);
        }
        String filename = multipartFile.getOriginalFilename();
        if (Objects.isNull(filename)) {
            throw new UploadException(HttpCodeEnum.FILE_NOT_NULL);
        }
        // 1.1 上传的文件类型只能是png或者jpg格式
        if (!filename.endsWith(".png") && !filename.endsWith(".jpg")) {
            throw new UploadException(HttpCodeEnum.FILE_TYPE_ERROR);
        }
        // 2. 上传文件到OSS
        try {
            String newFileName = FilePathUtils.generateFilePath(filename);
            String url = uploadImgTOOSS(multipartFile, newFileName);
            if (Objects.isNull(url)) {
                throw new UploadException(HttpCodeEnum.FILE_UPLOAD_ERROR);
            }
            // 3. 解析上传成功的结果，即新文件的路径
            return ResponseResult.okResult(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseResult.errorResult(HttpCodeEnum.SYSTEM_ERROR);
    }

    private String uploadImgTOOSS(MultipartFile file, String newFileName) throws IOException {
        Configuration cfg = new Configuration(Region.autoRegion());
        cfg.resumableUploadAPIVersion = Configuration.ResumableUploadAPIVersion.V2;// 指定分片上传版本
        UploadManager uploadManager = new UploadManager(cfg);
        InputStream inputStream = file.getInputStream();
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        try {
            Response response = uploadManager.put(inputStream, newFileName, upToken, null, null);
            // 解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            log.debug("putRet.key: {}, putRet.hash: {}", putRet.key, putRet.hash);
            return externalDomainName + "/" +newFileName;
        } catch (QiniuException ex) {
            ex.printStackTrace();
            if (ex.response != null) {
                log.error("ex.response:{}", ex.response);
            }
        }
        return null;
    }
}
