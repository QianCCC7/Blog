package com.xiaoqian.common.exception;

import com.xiaoqian.common.enums.RegisterCodeEnum;

public class RegisterException extends SystemException {

    public RegisterException(RegisterCodeEnum registerCodeEnum) {
        super(registerCodeEnum);
    }
}
