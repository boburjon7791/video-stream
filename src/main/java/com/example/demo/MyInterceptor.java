package com.example.demo;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Iterator;

@Configuration
public class MyInterceptor implements HandlerInterceptor, WebMvcConfigurer {
    private static final Logger log = LoggerFactory.getLogger(MyInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Iterator<String> iterator = request.getHeaderNames().asIterator();
        log.info("request headers start");
        while (iterator.hasNext()) {
            String headerName = iterator.next();
            log.info("{}: {}", headerName, request.getHeader(headerName));
        }
        log.info("request headers end");
        return true;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(this);
    }
}
