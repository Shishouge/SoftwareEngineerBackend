package com.example.backend.Configuration;

import cn.dev33.satoken.interceptor.SaRouteInterceptor;
import com.example.backend.Configuration.saTokenConfiguration.SaTokenUrlConfig;
import com.example.backend.Util.Token.AuthenticationInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
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

    //跨越配置
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("*");
            }
        };
    }

    //新增sa-Token拦截器
    @Autowired
    private SaTokenUrlConfig saTokenUrlConfig;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册拦截器
        registry.addInterceptor(new SaRouteInterceptor())
                .addPathPatterns(this.saTokenUrlConfig.getInterceptUrlList())
                .excludePathPatterns(this.saTokenUrlConfig.getOpenUrlList());
    }
    //


    // @Override
   // public void addInterceptors(InterceptorRegistry registry) {
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
   // }

//    @Bean
//    public AuthenticationInterceptor authenticationInterceptor() {
//        return new AuthenticationInterceptor();
//    }
}
