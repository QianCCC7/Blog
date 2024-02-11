package com.xiaoqian.common.exception;

import com.xiaoqian.common.enums.HttpCodeEnum;

public class UploadException extends SystemException{

    public UploadException(HttpCodeEnum httpCodeEnum) {
        super(httpCodeEnum);
    }
}
