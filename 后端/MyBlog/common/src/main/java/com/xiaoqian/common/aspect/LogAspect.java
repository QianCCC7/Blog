package com.xiaoqian.common.aspect;

import com.alibaba.fastjson.JSON;
import com.xiaoqian.common.annotation.SystemLog;
import com.xiaoqian.common.enums.HttpCodeEnum;
import com.xiaoqian.common.exception.SystemException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

// 1. 引入 aop依赖
// 2. 添加 @Component注入Spring容器
// 3. 添加 @Aspect声明为切面类
@Component
@Aspect
@Slf4j
public class LogAspect {

    /**
     * 切点
     */
    @Pointcut("@annotation(com.xiaoqian.common.annotation.SystemLog)")
    public void pt() {}

    /**
     * 增强
     */
    @Around("pt()")
    public Object printLog(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object res;
        try {
            handleBefore(proceedingJoinPoint);
            res = proceedingJoinPoint.proceed();// 执行目标方法并返回结果
            handleAfter(res);
        } finally {
            // System.lineSeparator()为系统换行符(适用于不同版本)
            log.info("=======================end=======================" + System.lineSeparator());
        }
        return res;
    }

    /**
     * 目标方法执行之前的日志打印
     */
    private void handleBefore(ProceedingJoinPoint proceedingJoinPoint) {
        ServletRequestAttributes requestAttributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (Objects.isNull(requestAttributes)) {
            throw new SystemException(HttpCodeEnum.SYSTEM_ERROR);
        }
        HttpServletRequest request = requestAttributes.getRequest();
        log.info("======================Start======================");
        // 打印请求 URL
        log.info("请求URL   : {}", request.getRequestURL());
        // 打印描述信息
        // 获取被增强方法的接口描述信息
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
        String businessDescription = methodSignature.getMethod().getAnnotation(SystemLog.class).businessDescription();
        log.info("接口描述   : {}", businessDescription);
        // 打印 Http method
        log.info("请求方式   : {}", request.getMethod());
        // 打印调用 controller 的全类名以及方法名
        log.info("请求类名   : {}.{}", proceedingJoinPoint.getSignature().getDeclaringTypeName(),
                                    proceedingJoinPoint.getSignature().getName());
        // 打印请求的 IP
        log.info("访问IP    : {}", request.getRemoteHost());
        // 打印请求入参
        log.info("传入参数   : {}", JSON.toJSONString(proceedingJoinPoint.getArgs()));
    }

    /**
     * 目标方法执行之后的日志打印
     */
    private void handleAfter(Object res) {
        log.info("返回参数   : {}",JSON.toJSONString(res));
    }
}
