package com.xiaoqian.common.handle;

import com.xiaoqian.common.domain.ResponseResult;
import com.xiaoqian.common.enums.HttpCodeEnum;
import com.xiaoqian.common.exception.LoginException;
import com.xiaoqian.common.exception.RegisterException;
import com.xiaoqian.common.exception.UploadException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class MyExceptionHandler {

    /**
     * 登录异常处理器
     */
    @ExceptionHandler(LoginException.class)
    public ResponseResult<Object> loginExceptionHandler(LoginException ex) {
        log.error("MyExceptionHandler捕捉到登录异常：{}", ex.toString());
        return ResponseResult.errorResult(ex.getCode(), ex.getMsg());
    }

    /**
     * 上传文件异常处理器
     */
    @ExceptionHandler(UploadException.class)
    public ResponseResult<Object> uploadExceptionHandler(UploadException ex) {
        log.error("MyExceptionHandler捕捉到文件上传异常：{}", ex.toString());
        return ResponseResult.errorResult(ex.getCode(), ex.getMsg());
    }

    /**
     * 用户注册异常处理器
     */
    @ExceptionHandler(RegisterException.class)
    public ResponseResult<Object> registerExceptionHandler(RegisterException ex) {
        log.error("MyExceptionHandler捕捉到用户注册异常：{}", ex.toString());
        return ResponseResult.errorResult(ex.getCode(), ex.getMsg());
    }

    /**
     * 其他异常处理器
     */
    @ExceptionHandler(Exception.class)
    public ResponseResult<Object> otherExceptionHandler(Exception ex) {
        log.error("MyExceptionHandler捕捉到其他异常：{}", ex.toString());
        return ResponseResult.errorResult(HttpCodeEnum.SYSTEM_ERROR.getCode(), ex.getMessage());
    }
}
