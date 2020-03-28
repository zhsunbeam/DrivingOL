package cn.marssky.dtf.controller.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class DtfWebSwaggerConfig {

    //配置swagger-ui
    @Bean
    public Docket api1() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiEndPointsInfo()).
                groupName("用户模块").
                select().
                //RequestHandlerSelectors配置扫描接口的方式,basePackage配置扫描的包
                apis(RequestHandlerSelectors.basePackage("cn.marssky.dtf.controller")).
                build();
    }

    private ApiInfo apiEndPointsInfo() {
        return new ApiInfoBuilder().title("在线服务API")
                .description("服务API")
                .license("The MIT License")
                .licenseUrl("https://opensource.org/licenses/MIT")
                .termsOfServiceUrl("服务条款")
                .version("V1")
                .build();
    }
}
