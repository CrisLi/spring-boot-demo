package com.example.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("home");
    }

//    @Bean
//    public ObjectMapper jsonObjectMapper() {
//        return Jackson2ObjectMapperBuilder.json()
//                .serializationInclusion(JsonInclude.Include.NON_NULL) // Don’t include null values
//                .featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS) //ISODate
//                .modules(new JavaTimeModule())
//                .build();
//    }
}
