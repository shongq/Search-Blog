package com.assignment.blog.service;

import com.assignment.blog.common.api.LocalLockProvider;
import com.assignment.blog.common.api.pattern.AbstractHandlerBase;
import com.assignment.blog.common.api.pattern.HandlerOrder;
import com.assignment.blog.dto.HandlerResponse;
import com.assignment.blog.dto.SearchBlogRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
@HandlerOrder(order = 30)
public class UpdateKeywordHandler extends AbstractHandlerBase<SearchBlogRequest, HandlerResponse> {

    private final KeywordService keywordService;
    private final LocalLockProvider localLockProvider;

    @Override
    protected HandlerResponse resolve(SearchBlogRequest param, HandlerResponse result) {
        try {
            String word = param.getQuery();
            while(!localLockProvider.tryAcquireLock(word)){};
            keywordService.updateKeywordSearchCount(word);
            localLockProvider.releaseLock(word);
            result.setSuccess(true);
        }  catch (Exception e) {
            log.warn("Keyword SerachCount Update Failed : " + e.getMessage());
            result.setSuccess(false);
        }

        return result;
    }
}