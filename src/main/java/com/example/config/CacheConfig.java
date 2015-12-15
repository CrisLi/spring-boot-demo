package com.example.config;

import org.springframework.cache.guava.GuavaCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CacheConfig {

    public static final String GREETINGS = "greetings";
    public static final String ACCOUNTS = "accounts";

    @Bean
    public GuavaCacheManager cacheManager() {
        return new GuavaCacheManager(GREETINGS, ACCOUNTS);
    }
}
