package com.assignment.blog.chain;

import com.assignment.blog.common.api.LocalLockProvider;
import com.assignment.blog.service.KeywordService;
import com.assignment.blog.service.UpdateKeywordHandler;
import com.assignment.blog.service.kakao.KakaoFeignClient;
import com.assignment.blog.service.kakao.KakaoFeignHandler;
import com.assignment.blog.service.naver.NaverSearchBlogAdapterHandler;
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
    private final KeywordService keywordService;
    private final LocalLockProvider localLockProvider;

    @Bean
    public SearchBlogChain searchBlogChain() {
        return new SearchBlogChain(
                new KakaoFeignHandler(kakaoFeignClient),
                new NaverSearchBlogAdapterHandler(naverFeignClient),
                new UpdateKeywordHandler(keywordService, localLockProvider)
        );
    }
}
