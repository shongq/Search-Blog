package com.assignment.blog.service.interfaces;

import com.assignment.blog.dto.SearchBlogRequest;
import com.assignment.blog.dto.SearchBlogResponse;

public interface ISearchBlogService {
    SearchBlogResponse searchBlogs(SearchBlogRequest request);
}
