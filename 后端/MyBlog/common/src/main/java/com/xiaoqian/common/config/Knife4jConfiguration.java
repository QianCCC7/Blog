package com.xiaoqian.common.config;

import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@SuppressWarnings("all")
public class Knife4jConfiguration {
    // 创建Docket存入容器，Docket代表一个接口文档
    @Bean
    public Docket webApiConfig() {
        return new Docket(DocumentationType.SWAGGER_2)
                // 创建接口文档的具体信息
                .apiInfo(webApiInfo())
                // 创建选择器，控制哪些接口被加入文档
                .select()
                // 指定@ApiOperation标注的接口被加入文档
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .build();
    }

    // 创建接口文档的具体信息，会显示在接口文档页面中
    private ApiInfo webApiInfo() {
        return new ApiInfoBuilder()
                // 文档标题
                .title("我的博客后端API接口文档")
                // 文档描述
                .description("文档记录了服务端所有的接口的入参，出参等等信息")
                // 版本
                .version("3.0")
                // 联系人信息
                .contact(new Contact("小钱", "https://blog.csdn.net/xiaoqian7758258?type=blog", "1805521303@qq.com"))
                .build();
    }
}
