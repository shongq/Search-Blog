package com.assignment.blog.service.naver;

import com.assignment.blog.dto.naver.NaverSearchBlogRequest;
import com.assignment.blog.common.config.NaverFeignConfig;
import com.assignment.blog.dto.naver.NaverSearchBlogResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "naver-blog-api", url = "${external.api.naver.url}", configuration = {NaverFeignConfig.class})
public interface NaverFeignClient {

    @GetMapping(value = "/v1/search/blog.json")
    NaverSearchBlogResponse getBlogs(@SpringQueryMap NaverSearchBlogRequest request);
}
