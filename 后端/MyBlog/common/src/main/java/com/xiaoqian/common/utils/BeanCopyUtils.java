package com.xiaoqian.common.utils;

import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

public class BeanCopyUtils {
    public BeanCopyUtils() {}

    /**
     * 单个实体类的拷贝，第一个参数是要源对象，第二个参数是目标类的字节码对象。
     */
    public static <V> V copyBean(Object source, Class<V> clazz) {
        V v;
        try {
            v = clazz.newInstance();
            BeanUtils.copyProperties(source, v);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return v;
    }

    /**
     * 集合的拷贝，第一个参数是要源集合，第二个参数是 目标类的字节码对象。
     */
    public static <S, V> List<V> copyBeanList(List<S> source, Class<V> clazz) {
        return source.stream()
                .map(o -> copyBean(o, clazz))
                .collect(Collectors.toList());
    }
}
