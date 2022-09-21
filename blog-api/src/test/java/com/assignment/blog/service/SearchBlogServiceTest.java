package com.assignment.blog.service;

import com.assignment.blog.domain.Keyword;
import com.assignment.blog.dto.HandlerResponse;
import com.assignment.blog.dto.SearchBlogRequest;
import com.assignment.blog.dto.SearchBlogResponse;
import com.assignment.blog.handler.KakaoFeignHandler;
import com.assignment.blog.handler.NaverSearchBlogAdapterHandler;
import com.assignment.blog.repository.KeywordRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback
class SearchBlogServiceTest {

    @Autowired
    KakaoFeignHandler kakaoFeignHandler;
    @Autowired
    NaverSearchBlogAdapterHandler naverSearchBlogAdapterHandler;
    @Autowired
    SearchBlogService searchBlogService;
    @Autowired
    KeywordRepository keywordRepository;
    
    @Test
    @DisplayName("카카오 블로그 검색 기본 테스트")
    void kakaoSearchBlogApiCall(){
        int size = 20;
        //given
        SearchBlogRequest searchBlogRequest = new SearchBlogRequest("카카오톡", "accuracy", size, 1);

        //when
        HandlerResponse response = kakaoFeignHandler.resolve(searchBlogRequest, new HandlerResponse());

        //then
        assertThat(response.isSuccess()).isTrue();
        if(!response.getSearchBlogResponse().getMeta().getIsEnd())
            assertThat(response.getSearchBlogResponse().getDocuments().size()).isEqualTo(size);
        else
            assertThat(response.getSearchBlogResponse().getDocuments().size()).isNotEqualTo(size);
    }

    @Test
    @DisplayName("네이버 블로그 검색 기본 테스트")
    void naverSearchBlogApiCall(){
        int size = 35;
        //given
        SearchBlogRequest searchBlogRequest = new SearchBlogRequest("라인", "recency", size, 3);

        //when
        HandlerResponse response = naverSearchBlogAdapterHandler.resolve(searchBlogRequest, new HandlerResponse(true, false, null));

        //then
        assertThat(response.isSuccess()).isTrue();
        if(!response.getSearchBlogResponse().getMeta().getIsEnd())
            assertThat(response.getSearchBlogResponse().getDocuments().size()).isEqualTo(size);
        else
            assertThat(response.getSearchBlogResponse().getDocuments().size()).isNotEqualTo(size);
    }

    @Test
    @DisplayName("블로그 검색 통합 테스트")
    void searchBlog(){
        //given
        int size = 50;
        String word = "슬랙";
        //given
        SearchBlogRequest searchBlogRequest = new SearchBlogRequest(word, "accuracy", size, 3);

        //when
        SearchBlogResponse response = searchBlogService.searchBlogs(searchBlogRequest);

        //then
        if(!response.getMeta().getIsEnd())
            assertThat(response.getDocuments().size()).isEqualTo(size);
        else
            assertThat(response.getDocuments().size()).isNotEqualTo(size);

        Optional<Keyword> keyword = keywordRepository.findByWord(word);
        assertThat(keyword.isPresent()).isTrue();
        assertThat(keyword.get().getWord()).isEqualTo(word);
        assertThat(keyword.get().getSearchCount()).isEqualTo(1);
    }
}