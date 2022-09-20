package com.assignment.blog.service;

import com.assignment.blog.domain.Keyword;
import com.assignment.blog.repository.KeywordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class KeywordService {

    private final KeywordRepository keywordRepository;

    public List<Keyword> findTop10Keywords() {
        return keywordRepository.findTop10ByOrderBySearchCountDesc();
    }

    @Transactional
    public void updateSearchCount(String word) {
        Optional<Keyword> keyword = keywordRepository.findByWord(word);
        if(keyword.isPresent())
            keyword.get().plusSearchCount();
        else {
            keywordRepository.save(new Keyword(word, 1));
        }
    }
}
