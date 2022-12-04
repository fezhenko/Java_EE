package org.example.socialnetwork.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
@AllArgsConstructor
public class MvcConfig implements WebMvcConfigurer {

//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new MappedInterceptor(new String[]{"/*"}, new AuthInterceptor(authContext)))
//                .excludePathPatterns("/login", "/registration", "/access-denied", "/api/v1/users", "/api/v1/users/{userId}");
//    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/access-denied").setViewName("accessDenied");
        registry.addViewController("/login").setViewName("login");
    }
}
