package com.minerals.minerals_on_springboot.Config;

import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@Configurable(autowire = Autowire.BY_TYPE)
@EnableWebMvc
public class MvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.setOrder(-1);
        registry.addResourceHandler(
            "/css/**",
                "/js/**",
                "/img/**"
        ).addResourceLocations(
                "classpath:/static/css/",
                "classpath:/static/js/",
                "classpath:/static/img/"
        );
    }
}
