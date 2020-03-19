package demo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2 {
    @Bean
    public Docket restApi() {
        return new Docket(DocumentationType.SWAGGER_2).
                apiInfo(new ApiInfoBuilder()
                        //页面标题
                        .title("Spring Boot Swagger2 构建RESTful API")
                        //条款地址
                        .termsOfServiceUrl("https://github.com/javakangpan/coffeeSystem")
                        .contact("kangpan")
                        .version("1.0")
                        //描述
                        .description("API 描述")
                        .build())
                .select()
                .paths(PathSelectors.any())
                .apis(RequestHandlerSelectors.basePackage("demo"))
                .build();
    }
}
