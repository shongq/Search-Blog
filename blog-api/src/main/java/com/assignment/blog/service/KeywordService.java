package com.assignment.blog.service;

import com.assignment.blog.common.api.pattern.AbstractHandlerBase;
import com.assignment.blog.common.api.pattern.HandlerOrder;
import com.assignment.blog.domain.Keyword;
import com.assignment.blog.dto.HandlerResponse;
import com.assignment.blog.dto.SearchBlogRequest;
import com.assignment.blog.repository.KeywordRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class KeywordService {

    private final KeywordRepository keywordRepository;

    public List<Keyword> findTop10Keywords() {
        return keywordRepository.findTop10ByOrderBySearchCountDesc();
    }

    /**
     * 블로그 검색 시 검색키워드의 SearchCount를 업데이트
     *
     * @param word
     *
     */
    @Transactional
    public void updateKeywordSearchCount(String word) {
        Optional<Keyword> keyword = keywordRepository.findByWord(word);
        if (keyword.isPresent())
            keyword.get().plusSearchCount();
        else {
            keywordRepository.save(new Keyword(word, 1));
        }
    }
}

