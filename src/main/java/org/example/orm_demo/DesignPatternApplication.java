package org.example.orm_demo;

import org.example.orm_demo.service.ILoggingService;
import org.example.orm_demo.service.LoggingService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@SpringBootApplication
@Configuration
@EnableRetry
@EnableAsync
public class DesignPatternApplication {

    public static void main(String[] args) {
        SpringApplication.run(DesignPatternApplication.class, args);
    }

    @Bean(name = "taskExecutor")
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(10);
        executor.setQueueCapacity(100);
        executor.initialize();
        return executor;
    }


    @Bean
    @Scope(value ="request", proxyMode = ScopedProxyMode.INTERFACES)
    public ILoggingService firstLoggingService() {
        return new LoggingService();
    }

    @Bean
    @Scope(value = "request", proxyMode = ScopedProxyMode.INTERFACES)
    public ILoggingService secondLoggingService() {
        return new LoggingService();
    }
}
