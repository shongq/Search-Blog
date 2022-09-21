package com.assignment.blog.service;

import com.assignment.blog.common.LocalLockProvider;
import com.assignment.blog.domain.Keyword;
import com.assignment.blog.repository.KeywordRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class KeywordService {

    private final KeywordRepository keywordRepository;
    private final LocalLockProvider localLockProvider;

    @Cacheable(value = "findTop10Keywords")
    public List<Keyword> findTop10Keywords() {
        return keywordRepository.findTop10ByOrderBySearchCountDesc();
    }

    public Keyword findByWord(String word) {
        return keywordRepository.findByWord(word).get();
    }

    /**
     * 블로그 검색 시 검색키워드의 SearchCount를 업데이트
     *
     * @param word
     */
    @Transactional
    public void updateKeywordSearchCount(String word) {
//        while(!localLockProvider.tryAcquireLock(word)){};

        Optional<Keyword> keyword = keywordRepository.findByWord(word);
        if (keyword.isPresent())
            keyword.get().plusSearchCount();
        else {
            keywordRepository.save(new Keyword(word, 1));
        }

//        localLockProvider.releaseLock(word);
    }
}

