package com.assignment.blog.controller;

import com.assignment.blog.common.api.ResponseEntity;
import com.assignment.blog.dto.SearchBlogRequest;
import com.assignment.blog.dto.SearchBlogResponse;
import com.assignment.blog.service.interfaces.ISearchBlogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/blog")
@RestController
public class BlogController {

    private final ISearchBlogService iSearchBlogService;

    @GetMapping(value = "/search")
    public ResponseEntity<?> serachBlog(@Valid SearchBlogRequest request) {
        SearchBlogResponse searchBlogResponse = iSearchBlogService.searchBlogs(request);

        return ResponseEntity.create(searchBlogResponse);
    }
}
