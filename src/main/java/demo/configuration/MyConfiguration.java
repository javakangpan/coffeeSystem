package demo.configuration;

import demo.endpoint.MyEndpoint;
import demo.support.PerformanceInteceptor;
import org.springframework.boot.actuate.autoconfigure.endpoint.condition.ConditionalOnEnabledEndpoint;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.TimeZone;

/**
 * ==> SpringMVC 拦截器的配置方式
 * 常规方法
 *  WebMvcConfigurer.addInterceptors()
 * Spring Boot 中的配置
 *  创建一个带 @Configuration 的 WebMvcConfigurer 配置类
 *  不能带@EnableWebMvc (彻底控制MVC配置除外)
 */
@Configuration
public class MyConfiguration implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new PerformanceInteceptor()).addPathPatterns("/coffee/**").addPathPatterns("/static/**");
    }
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
        return jacksonObjectMapperBuilder -> {
            jacksonObjectMapperBuilder.indentOutput(true);
            jacksonObjectMapperBuilder.timeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        };
    }

    /**
     * application.properties 中增加了访问静态资源配置
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/demo/**")
                .addResourceLocations("classpath:/demo/");
        registry.addResourceHandler("swagger-ui.html").addResourceLocations(
                "classpath:/META-INF/resources/");
    }

    /**
     * 定义自己的 Endpoint
     */
    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnEnabledEndpoint
    public MyEndpoint myEndPoint() {
        return MyEndpoint.builder().build();
    }
}
