package com.assignment.blog.service.kakao;

import com.assignment.blog.common.api.pattern.AbstractHandlerBase;
import com.assignment.blog.common.api.pattern.HandlerOrder;
import com.assignment.blog.dto.SearchBlogRequest;
import com.assignment.blog.dto.SearchBlogResponse;
import com.assignment.blog.dto.HandlerResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
@HandlerOrder(order = 10)
public class KakaoFeignService extends AbstractHandlerBase<SearchBlogRequest, HandlerResponse> {
    private final KakaoFeignClient kakaoFeignClient;

    @Override
    protected HandlerResponse resolve(SearchBlogRequest param, HandlerResponse result) {

        try {
            SearchBlogResponse searchBlogResponse = kakaoFeignClient.getBlogs(param);
            result.setSearch(true);
            result.setSearchBlogResponse(searchBlogResponse);
        } catch (Exception e) {
            log.warn("Kakao Blog Search Invalid : " + e.getMessage());
            result.setSearch(false);
        }

        result.setSuccess(true);
        return result;
    }
}
