package com.xiaoqian.common.exception;

import com.xiaoqian.common.enums.HttpCodeEnum;

public class CommentException extends SystemException {

    public CommentException() {
        super(HttpCodeEnum.COMMENT_NOT_NULL);
    }

}
