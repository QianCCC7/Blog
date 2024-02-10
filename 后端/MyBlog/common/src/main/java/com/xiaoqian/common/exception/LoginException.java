package com.xiaoqian.common.exception;

import com.xiaoqian.common.enums.HttpCodeEnum;
import lombok.Getter;

@Getter
public class LoginException extends SystemException {

    public LoginException() {
        super(HttpCodeEnum.REQUIRE_USERNAME_AND_PASSWORD);
    }

    public LoginException(HttpCodeEnum httpCodeEnum, String msg) {
        super(httpCodeEnum, msg);
    }

}
