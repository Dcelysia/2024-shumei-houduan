package com.laterya.hearing.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class UploadConfig implements WebMvcConfigurer{

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler("/uploadimg/**").addResourceLocations("file:/home/zzp/images/");
        /*registry.addResourceHandler("/uploadimg/**").addResourceLocations("file:D:/test/");*/
    }
}
