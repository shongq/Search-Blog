package com.assignment.blog.chain;

import com.assignment.blog.common.api.pattern.AbstractHandlerBase;
import com.assignment.blog.common.api.pattern.AbstractHandlerChain;
import com.assignment.blog.dto.HandlerResponse;
import com.assignment.blog.dto.SearchBlogRequest;

public class SearchBlogChain extends AbstractHandlerChain<SearchBlogRequest, HandlerResponse> {
    public SearchBlogChain(AbstractHandlerBase... handlers) {
        super(handlers);
    }
}
