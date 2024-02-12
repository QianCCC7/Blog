package com.xiaoqian.common.exception;

import com.xiaoqian.common.enums.HttpCodeEnum;
import com.xiaoqian.common.enums.RegisterCodeEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SystemException extends RuntimeException {
    private int code;
    private String msg;

    public SystemException(HttpCodeEnum httpCodeEnum) {
        super(httpCodeEnum.getMsg());
        this.code = httpCodeEnum.getCode();
        this.msg = httpCodeEnum.getMsg();
    }

    public SystemException(RegisterCodeEnum RegisterCodeEnum) {
        super(RegisterCodeEnum.getMsg());
        this.code = RegisterCodeEnum.getCode();
        this.msg = RegisterCodeEnum.getMsg();
    }

    public SystemException(HttpCodeEnum httpCodeEnum, String msg) {
        super(msg);
        this.code = httpCodeEnum.getCode();
        this.msg = msg;
    }

}
