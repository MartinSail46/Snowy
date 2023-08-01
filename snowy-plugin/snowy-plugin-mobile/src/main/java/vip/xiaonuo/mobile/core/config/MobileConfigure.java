package vip.xiaonuo.mobile.core.config;

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
import vip.xiaonuo.common.pojo.CommonResult;

import javax.annotation.Resource;

/**
 * 业务相关配置
 *
 * @author xuyuxiang yubaoshan
 * @date 2022/7/7 16:18
 **/
@Configuration
public class MobileConfigure {

    @Resource
    private OpenApiExtensionResolver openApiExtensionResolver;

    /**
     * API文档分组配置
     *
     * @author xuyuxiang yubaoshan
     * @date 2022/7/7 16:18
     **/
    @Bean(value = "mobileDocApi")
    public Docket mobileDocApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .title("移动端功能MOBILE")
                        .description("移动端功能MOBILE")
                        .termsOfServiceUrl("https://www.xiaonuo.vip")
                        .contact(new Contact("SUPPORT_TEAM","https://www.xiaonuo.vip", "xiaonuobase@qq.com"))
                        .version("2.0.0")
                        .build())
                .globalResponseMessage(RequestMethod.GET, CommonResult.responseList())
                .globalResponseMessage(RequestMethod.POST, CommonResult.responseList())
                .groupName("移动端功能MOBILE")
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .apis(RequestHandlerSelectors.basePackage("vip.xiaonuo.mobile"))
                .paths(PathSelectors.any())
                .build().extensions(openApiExtensionResolver.buildExtensions("移动端功能MOBILE"));
    }
}
