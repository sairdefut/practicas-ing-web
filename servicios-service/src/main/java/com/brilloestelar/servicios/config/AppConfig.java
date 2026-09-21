package com.brilloestelar.servicios.config;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Collections;

@Configuration
public class AppConfig {
    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setInterceptors(Collections.singletonList((ClientHttpRequestInterceptor) (request, body, execution) -> {
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if (attributes != null) {
                HttpServletRequest servletRequest = attributes.getRequest();
                String authHeader = servletRequest.getHeader("Authorization");
                if (authHeader != null) {
                    request.getHeaders().add("Authorization", authHeader);
                }
            }
            return execution.execute(request, body);
        }));
        return restTemplate;
    }
}
