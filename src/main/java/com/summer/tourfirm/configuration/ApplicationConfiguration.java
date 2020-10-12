package com.summer.tourfirm.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.summer.tourfirm.repository")
@EntityScan(basePackages = "com.summer.tourfirm.entity")
public class ApplicationConfiguration implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry
                .addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("OPTIONAL", "GET", "POST", "PUT", "DELETE");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/","classpath:/assets/image/");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addRedirectViewController("/", "/index.html");
    }
}
