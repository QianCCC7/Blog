package com.xiaoqian.common.exception;

import com.xiaoqian.common.enums.HttpCodeEnum;

public class TagException extends SystemException{
    public TagException(HttpCodeEnum httpCodeEnum) {
        super(httpCodeEnum);
    }
}
