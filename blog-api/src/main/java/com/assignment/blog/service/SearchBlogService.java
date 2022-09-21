package com.assignment.blog.service;

import com.assignment.blog.dto.SearchBlogRequest;
import com.assignment.blog.dto.SearchBlogResponse;
import com.assignment.blog.dto.HandlerResponse;
import com.assignment.blog.handler.chain.SearchBlogChain;
import com.assignment.blog.service.interfaces.ISearchBlogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SearchBlogService implements ISearchBlogService {

    private final SearchBlogChain searchBlogChain;

    public SearchBlogResponse searchBlogs(SearchBlogRequest request){

        HandlerResponse handlerResponse = searchBlogChain.start(request, new HandlerResponse());

        return handlerResponse.getSearchBlogResponse();
    }

}
