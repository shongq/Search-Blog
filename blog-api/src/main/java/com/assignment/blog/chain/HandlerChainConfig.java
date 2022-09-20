package com.assignment.blog.chain;

import com.assignment.blog.service.kakao.KakaoFeignClient;
import com.assignment.blog.service.kakao.KakaoFeignService;
import com.assignment.blog.service.naver.NaverSearchBlogAdapterService;
import com.assignment.blog.service.naver.NaverFeignClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class HandlerChainConfig {

    private final KakaoFeignClient kakaoFeignClient;
    private final NaverFeignClient naverFeignClient;

    @Bean
    public SearchBlogChain searchBlogChain() {
        return new SearchBlogChain(
                new KakaoFeignService(kakaoFeignClient),
                new NaverSearchBlogAdapterService(naverFeignClient)
        );
    }
}
