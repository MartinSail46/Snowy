package com.amarsoft.gen.core.config;


import com.github.xiaoymin.knife4j.spring.extension.OpenApiExtensionResolver;
import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import com.amarsoft.common.pojo.CommonResult;

import javax.annotation.Resource;

/**
 * 代码生成相关配置
 *
 * @author yubaoshan
 * @date 2022/10/25 22:33
 **/
@Configuration
public class GenConfigure {

    @Resource
    private OpenApiExtensionResolver openApiExtensionResolver;

    /**
     * API文档分组配置
     *
     * @author xuyuxiang
     * @date 2022/7/7 16:18
     **/
    @Bean(value = "genDocApi")
    public Docket sysDocApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .title("代码生成GEN")
                        .description("代码生成GEN")
                        .termsOfServiceUrl("https://www.xiaonuo.vip")
                        .contact(new Contact("SUPPORT_TEAM","https://www.xiaonuo.vip", "xiaonuobase@qq.com"))
                        .version("2.0.0")
                        .build())
                .globalResponseMessage(RequestMethod.GET, CommonResult.responseList())
                .globalResponseMessage(RequestMethod.POST, CommonResult.responseList())
                .groupName("代码生成GEN")
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .apis(RequestHandlerSelectors.basePackage("com.amarsoft.gen"))
                .paths(PathSelectors.any())
                .build().extensions(openApiExtensionResolver.buildExtensions("代码生成GEN"));
    }
}
