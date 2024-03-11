package com.xiaoqian.common.enums;

import lombok.Getter;

@Getter
public enum HttpCodeEnum {
    SUCCESS(200,"操作成功"),
    NEED_LOGIN(401,"需要登录后操作"),
    NO_OPERATOR_AUTH(403,"无权限操作"),
    SYSTEM_ERROR(500,"出现错误"),

    REQUIRE_USERNAME_AND_PASSWORD(504, "必需填写用户名和密码"),
    LOGIN_ERROR(505,"用户名或密码错误"),
    COMMENT_NOT_NULL(506, "评论内容不能为空"),

    FILE_NOT_NULL(507, "上传的文件不能为空"),
    FILE_TYPE_ERROR(508, "上传的文件类型错误，请上传.png文件或.jpg文件"),
    FILE_UPLOAD_ERROR(509, "文件上传失败"),

    TAG_NOT_NULL(510, "标签名称不能为空"),

    ARTICLE_NOT_NULL(511, "博文出现异常"),

    EXCEL_DOWNLOAD_ERROR(512, "excel文件下载出现异常")
    ;
    int code;
    String msg;

    HttpCodeEnum(int code, String errorMessage){
        this.code = code;
        this.msg = errorMessage;
    }
}
