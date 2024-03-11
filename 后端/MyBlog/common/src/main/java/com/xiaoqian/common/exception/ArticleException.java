package com.xiaoqian.common.exception;

import com.xiaoqian.common.enums.HttpCodeEnum;
import com.xiaoqian.common.enums.RegisterCodeEnum;

public class ArticleException extends SystemException{

    public ArticleException(HttpCodeEnum httpCodeEnum) {
        super(httpCodeEnum);
    }
}
