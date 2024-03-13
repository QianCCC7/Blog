package com.xiaoqian.common.exception;

import com.xiaoqian.common.enums.HttpCodeEnum;

public class MenuException extends SystemException{
    public MenuException(HttpCodeEnum httpCodeEnum) {
        super(httpCodeEnum);
    }
}
