package com.xiaoqian.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 允许跨域的路径
                .allowedOriginPatterns("http://localhost:8080")// 允许跨域请求的域名
                .allowedOriginPatterns("http://localhost:8081")// 允许跨域请求的域名
                .allowedMethods("GET", "POST", "PUT", "DELETE") // 允许的请求方式
                .allowedHeaders("*") // 允许的 Header属性
                .allowCredentials(true) // 允许 cookie
                .maxAge(3600); // 允许的跨域时间
    }
}
