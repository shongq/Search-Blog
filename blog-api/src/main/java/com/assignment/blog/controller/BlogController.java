package com.assignment.blog.controller;

import com.assignment.blog.common.api.ResponseEntity;
import com.assignment.blog.domain.Keyword;
import com.assignment.blog.dto.SearchBlogRequest;
import com.assignment.blog.dto.SearchBlogResponse;
import com.assignment.blog.service.KeywordService;
import com.assignment.blog.service.interfaces.ISearchBlogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/blog")
@RestController
public class BlogController {

    private final ISearchBlogService iSearchBlogService;
    private final KeywordService keywordService;

    @GetMapping(value = "/search")
    public ResponseEntity<?> serachBlog(@Valid SearchBlogRequest request) {
        SearchBlogResponse searchBlogResponse;
        try {
            searchBlogResponse = iSearchBlogService.searchBlogs(request);
        } catch (Exception e) {
            return ResponseEntity.createError("블로그 검색에 실패했습니다.", 500);
        }

        return ResponseEntity.create(searchBlogResponse);
    }

    @GetMapping(value = "/list/keyword")
    public ResponseEntity<?> listTop10Keyword() {
        List<Keyword> top10Keywords;
        try {
            top10Keywords = keywordService.findTop10Keywords();
        } catch (Exception e) {
            return ResponseEntity.createError("인기 검색어 목록 조회에 실패했습니다.", 500);
        }

        return ResponseEntity.create(top10Keywords);
    }
}
