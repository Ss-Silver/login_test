package com.login.login_test.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Bean
    public Docket saggerApi() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(swaggerInfo()).select()
                .apis(RequestHandlerSelectors.basePackage("com.login.login_test.controller")).paths(PathSelectors.any()).build()
                .useDefaultResponseMessages(false); //기본으로 세팅되느 200, 401, 403, 404 메시지를 표시하지 않음
                //PathSelectors.ant(“/ v1/**”) v1으로 시작하는 resource들만 문서화 시킬수 있다.
    }

    private ApiInfo swaggerInfo() {
        return new ApiInfoBuilder().title("Spring API Documentation").description("앱 개발시 사용되는 서버 API에 대한 연동 문서입니다.")
                .license("sangeun.seong").licenseUrl("http://getter-setter.com").version("1").build();
    }

}
