package com.assignment.blog.common.config;

import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

public class KakaoFeignConfig extends FeignConfig {

    @Value("${external.api.kakao.api-key}")
    private String apiKey;  //kakao rest api key

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            requestTemplate.header("Authorization", apiKey);    //header Authorization 세팅
        };
    }
}
