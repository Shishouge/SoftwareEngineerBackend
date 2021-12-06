package com.example.backend.Configuration;

import com.example.backend.Util.Token.AuthenticationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MVCConfig implements WebMvcConfigurer {
    /**
     * 静态资源映射
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(authenticationInterceptor())
////                表示拦截所有请求
//                .addPathPatterns("/**")
////                表示取消对特定路径的拦截
//                .excludePathPatterns("/login")
//                .excludePathPatterns("/swagger-ui.html")
//                .excludePathPatterns("/loginCheck")
// //               .excludePathPatterns("/check")
//                .excludePathPatterns("/swagger-ui/#/*")
////                取消对static目录下静态资源的拦截
//                .excludePathPatterns("/static/**");
    }

    @Bean
    public AuthenticationInterceptor authenticationInterceptor() {
        return new AuthenticationInterceptor();
    }
}
