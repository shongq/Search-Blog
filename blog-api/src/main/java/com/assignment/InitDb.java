package com.assignment;

import com.assignment.blog.domain.Keyword;
import com.assignment.blog.repository.KeywordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class InitDb {
    private final KeywordRepository keywordRepository;

    @PostConstruct
    public void init() {
        dbInit();
    }

    private void dbInit() {
        saveKeyword("카카오뱅크", 10);
        saveKeyword("토스뱅크", 9);
        saveKeyword("국민은행", 8);
        saveKeyword("신한은행", 7);
        saveKeyword("하나은행", 6);
        saveKeyword("우리은행", 5);
        saveKeyword("SC제일은행", 4);
        saveKeyword("외환은행", 3);
        saveKeyword("한국시티은행", 2);
        saveKeyword("케이뱅크", 1);
    }

    private Keyword saveKeyword(String word, Integer searchCount) {
        Keyword keyword = new Keyword(word, searchCount);;
        keywordRepository.save(keyword);
        return keyword;
    }
}
