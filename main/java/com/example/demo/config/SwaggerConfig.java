package com.example.demo.config;

import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
@Configuration
public class SwaggerConfig {
/*

    // 定义分隔符 用于多包扫描
    private static final String splitor = ";";

    */
/**
     * 创建API应用
     * apiInfo() 增加API相关信息
     * 通过select()函数返回一个ApiSelectorBuilder实例,用来控制哪些接口暴露给Swagger来展现，
     * 本例采用指定扫描的包路径来定义指定要建立API的目录。
     *
     * @return
     *//*

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(basePackage("com.example.demo.controller"+splitor+
                        "com.example.demo.frontend"))
                .paths(PathSelectors.any())
                .build();
    }

    */
/**
     * 创建API应用
     * apiInfo() 增加API相关信息
     * 通过select()函数返回一个ApiSelectorBuilder实例,用来控制哪些接口暴露给Swagger来展现，
     * 指定扫描的包路径来定义指定要建立API的目录。
     *
     * @return
     *//*

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("税务代开平台API")
                .description("简单优雅的restfun风格，")
                .termsOfServiceUrl("")
                .version("2.0")
                .build();
    }

    */
/**
     * 重写basePackage方法，使能够实现多包访问
     * @return com.google.common.base.Predicate<springfox.documentation.RequestHandler>
     *//*

    public static Predicate<RequestHandler> basePackage(final String basePackage) {
        return input -> declaringClass(input).transform(handlerPackage(basePackage)).or(true);
    }

    private static Function<Class<?>, Boolean> handlerPackage(final String basePackage)     {
        return input -> {
            // 循环判断匹配
            for (String strPackage : basePackage.split(splitor)) {
                boolean isMatch = input.getPackage().getName().startsWith(strPackage);
                if (isMatch) {
                    return true;
                }
            }
            return false;
        };
    }

    private static Optional<? extends Class<?>> declaringClass(RequestHandler input) {
        return Optional.fromNullable(input.declaringClass());
    }
*/

    @Bean
    public Docket docket() {
        return new Docket(new DocumentationType("SWAGGER", "2.0"))
                .apiInfo(getApiInfo())
//                .host("http://localhost:8080")
                .groupName("何灿")
                //.globalResponseMessage(RequestMethod.GET, Lists.newArrayList())

                //.pathMapping("AAAAA")// 上下文
                .enableUrlTemplating(true)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.demo.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo getApiInfo() {
        Contact contact = new Contact("何灿", "https://gitee.com/hecanhc/dashboard/projects", "1020205116@qq.com");
        return new ApiInfo("swagger文档", "描述", "2.0", "https://gitee.com/hecanhc/dashboard/projects",
                contact, "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0", new ArrayList());

    }

}