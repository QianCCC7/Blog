package com.xiaoqian.admin;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.ConfigurableEnvironment;

@SpringBootApplication
@MapperScan("com.xiaoqian.common.mapper")
@ComponentScan({"com.xiaoqian.admin", "com.xiaoqian.common"})
@Slf4j
public class AdminApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(AdminApplication.class, args);
        ConfigurableEnvironment environment = context.getEnvironment();
        String port = environment.getProperty("server.port");
        log.info("后端后台项目文档地址: http://localhost:{}/doc.html", port);
    }
}
