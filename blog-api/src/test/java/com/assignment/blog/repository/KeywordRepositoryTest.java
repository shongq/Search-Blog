package com.assignment.blog.repository;

import com.assignment.blog.domain.Keyword;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(value = false)
class KeywordRepositoryTest {

    @Autowired
    KeywordRepository keywordRepository;
    
    @Test
    void testKeyword(){
        //given
        Keyword keyword = new Keyword("나이키", 1);

        //when
        Keyword savedKeyword = keywordRepository.save(keyword);
        Keyword findKeyword = keywordRepository.findById(savedKeyword.getId()).get();

        //then
        assertThat(findKeyword.getId()).isEqualTo(keyword.getId());
        assertThat(findKeyword.getWord()).isEqualTo(keyword.getWord());
        assertThat(findKeyword.getSearchCount()).isEqualTo(keyword.getSearchCount());
        assertThat(findKeyword).isEqualTo(keyword);
    }
}