package com.xiaoqian.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// 2. 添加元注解
@Retention(RetentionPolicy.RUNTIME) // 注解的保留策略，表示该注解在运行时仍然可用，可以通过反射来访问。
@Target(ElementType.METHOD) // 指定注解可以应用的目标类型，表示该注解可以应用到方法上。
public @interface SystemLog { // 1. 使用 @interface声明为一个注解类
    /**
     * controller接口的描述信息
     */
    String businessDescription() default ""; // 定义一个属性，默认为空串
}
