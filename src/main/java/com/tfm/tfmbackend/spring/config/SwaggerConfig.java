package com.tfm.tfmbackend.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@Import(value = {WebConfig.class})
public class SwaggerConfig extends WebMvcConfigurationSupport {

    ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Fleet Management Swagger")
                .version("0.0.1")
                .contact(new Contact(
                        "metin oztunc",
                        "https://github.com/metinztnc",
                        "metin.ztnc@gmail.com"))
                .build();
    }

    @Bean
    public Docket allApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("All APIs")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.tfm.tfmbackend.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

}
