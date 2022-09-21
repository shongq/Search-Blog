package com.assignment.blog.handler;

import com.assignment.blog.common.api.pattern.AbstractHandlerBase;
import com.assignment.blog.common.api.pattern.HandlerOrder;
import com.assignment.blog.dto.SearchBlogRequest;
import com.assignment.blog.dto.SearchBlogResponse;
import com.assignment.blog.dto.HandlerResponse;
import com.assignment.blog.service.kakao.KakaoFeignClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@HandlerOrder(order = 10)
public class KakaoFeignHandler extends AbstractHandlerBase<SearchBlogRequest, HandlerResponse> {
    private final KakaoFeignClient kakaoFeignClient;

    @Override
    protected HandlerResponse resolve(SearchBlogRequest param, HandlerResponse result) {

        try {
            SearchBlogResponse searchBlogResponse = kakaoFeignClient.getBlogs(param);
            result.setSearch(true);
            result.setSearchBlogResponse(searchBlogResponse);
        } catch (Exception e) {
            log.warn("Kakao Blog Search Failed : " + e.getMessage());
            result.setSearch(false);
        }

        result.setSuccess(true);
        return result;
    }
}
