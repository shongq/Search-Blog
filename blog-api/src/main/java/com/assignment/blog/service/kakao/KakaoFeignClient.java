package com.assignment.blog.service.kakao;

import com.assignment.blog.dto.SearchBlogRequest;
import com.assignment.blog.dto.SearchBlogResponse;
import com.assignment.blog.config.KakaoFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "kakao-blog-api", url = "${external.api.kakao.url}", configuration = {KakaoFeignConfig.class})
public interface KakaoFeignClient {

    @GetMapping(value = "/v2/search/blog")
    SearchBlogResponse getBlogs(@SpringQueryMap SearchBlogRequest request);
}
