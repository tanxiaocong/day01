package com.vsc.website.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootConfiguration
public class MySpringMVCConfig implements WebMvcConfigurer {

    @Bean
    public TokenFilter tokenFilter() {
        return new TokenFilter();
    }

    //集成swagger 加入拦截器，需要忽略掉 swagger-resources下面的请求 以及忽略掉 v2下面的请求即可
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tokenFilter()).addPathPatterns("/backend/**","/commons/**")
                .excludePathPatterns("/backend/users/login**","/commons/attachments/download.do**")
                .excludePathPatterns("/commons/captcha/getCaptcha**","/commons/captcha/verifyCaptcha**")
                .excludePathPatterns("/swagger-resources/**","/error/**", "/webjars/**","/v2/**", "/swagger-ui.html/**","/doc.html");
    }
}
