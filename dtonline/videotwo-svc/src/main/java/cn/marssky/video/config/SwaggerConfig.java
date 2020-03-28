package cn.marssky.video.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    //设置监听的环境
    Profiles profiles = Profiles.of("dev");

    @Bean
    public ApiInfo apiInfo(){
        final Contact DEFAULT_CONTACT = new Contact("annian", "annian.ren", "annian7@foxmail.com");
        return new ApiInfo("Api 科目二视频服务",
                "科目二相关Api",
                "1.0",
                "urn:tos",
                DEFAULT_CONTACT,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList<VendorExtension>());
    }


    @Bean
    public Docket customerApi(Environment env){
        boolean flag = env.acceptsProfiles(profiles);
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("科目二")
                .enable(flag)
                .select()
                .apis(RequestHandlerSelectors.basePackage("cn.marssky.video.controller"))
                .paths(PathSelectors.any())
                .build();
    }
}
