package com.lucian.lucian_blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Configuration
public class SwaggerConfiguration {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.OAS_30)
                .securitySchemes(Collections.singletonList(securitySchemes()))
//                .securityContexts(Collections.singletonList(SecurityContext.builder()
//                        .securityReferences(Collections.singletonList(SecurityReference.builder()
//                                .scopes(new AuthorizationScope[0])
//                                .reference("JWT")
//                                .build()))
//                        // 声明作用域
//                        .operationSelector(o -> o.requestMappingPattern().matches("/admin/.*"))
//                        .build()))
                .securityContexts(Collections.singletonList(securityContexts()))
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.lucian.lucian_blog.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("lucian blog 接口文档")
                .description("个人blog开发接口信息")
                .contact(new Contact("Lucian。", "https://luciandeng.com", "17607003651@163.com"))
                .version("1.0")
                .build();
    }

    private SecurityScheme securitySchemes() {
        return new ApiKey("Authorization", "Authorization", "header");
    }

    private SecurityContext securityContexts() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.any())
                .build();
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("xxx", "描述信息");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Arrays.asList(new SecurityReference("Authorization", authorizationScopes));
    }

}
