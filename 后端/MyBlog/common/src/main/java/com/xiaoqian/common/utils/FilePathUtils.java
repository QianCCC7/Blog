package com.xiaoqian.common.utils;


import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class FilePathUtils {
    /**
     * 根据当前时间生成文件夹，使用 uuid作为新文件名
     */
    public static String generateFilePath(String fileName){
        // 1. 根据日期生成路径   2022/1/15/
        DateTimeFormatter sdf = DateTimeFormatter.ofPattern("yyyy/MM/dd/");
        String datePath = sdf.format(LocalDateTime.now());
        // 2. uuid作为文件名
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        // 3. 截取文件后缀
        int index = fileName.lastIndexOf(".");
        String fileType = fileName.substring(index);
        // 4. 生成新文件名
        return datePath + uuid + fileType;
    }
}
