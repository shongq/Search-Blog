package com.assignment.blog.config;

import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

public class NaverFeignConfig extends FeignConfig {

    @Value("${external.api.naver.client-id}")
    private String clientId;    //naver rest api clientId

    @Value("${external.api.naver.client-secret}")
    private String clientSecret;    //naver rest api clientSecret

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            requestTemplate.header("X-Naver-Client-Id", clientId);    //header clientId 세팅
            requestTemplate.header("X-Naver-Client-Secret", clientSecret);    //header clientSecret 세팅
        };
    }
}
