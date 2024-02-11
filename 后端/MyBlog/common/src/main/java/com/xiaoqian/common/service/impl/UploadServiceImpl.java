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
import com.xiaoqian.common.service.IUploadService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@Service
@Data
@Slf4j
@ConfigurationProperties(prefix = "oss")
public class UploadServiceImpl implements IUploadService {
    private String accessKey;
    private String secretKey;
    private String bucket;// 存储空间的名字

    /**
     * 上传文件
     */
    @Override
    public ResponseResult<Object> upload(MultipartFile multipartFile) {
        // 1. 判断文件类型或者文件大小

        // 2. 上传文件到OSS
        try {
            String url = uploadImgTOOSS(multipartFile);
            log.info("上传图片Url = {}", url);
            return ResponseResult.okResult(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String uploadImgTOOSS(MultipartFile file) throws IOException {
        //构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Region.autoRegion());
        cfg.resumableUploadAPIVersion = Configuration.ResumableUploadAPIVersion.V2;// 指定分片上传版本
        UploadManager uploadManager = new UploadManager(cfg);
        //默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = "cat3.png";
        InputStream inputStream = file.getInputStream();
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        try {
            Response response = uploadManager.put(inputStream, key, upToken, null, null);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            log.debug("putRet.key: {}", putRet.key);
            log.debug("putRet.hash: {}", putRet.hash);
            return "abc";
        } catch (QiniuException ex) {
            ex.printStackTrace();
            if (ex.response != null) {
                System.err.println(ex.response);
                try {
                    String body = ex.response.toString();
                    System.err.println(body);
                } catch (Exception ignored) {
                }
            }
        }
        return null;
    }
}
