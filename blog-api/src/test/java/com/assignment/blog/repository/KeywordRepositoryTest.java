package com.assignment.blog.repository;

import com.assignment.blog.domain.Keyword;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
        Keyword keyword = new Keyword("테스트키워드", 1);

        //when
        Keyword savedKeyword = keywordRepository.save(keyword);
        Keyword findKeyword = keywordRepository.findById(savedKeyword.getId()).get();

        //then
        assertThat(findKeyword.getId()).isEqualTo(keyword.getId());
        assertThat(findKeyword.getWord()).isEqualTo(keyword.getWord());
        assertThat(findKeyword.getSearchCount()).isEqualTo(keyword.getSearchCount());
        assertThat(findKeyword).isEqualTo(keyword);
    }
    
    @Test
    void basicCRUD(){
        Keyword keyword1 = new Keyword("테스트키워드1", 1);
        Keyword keyword2 = new Keyword("테스트키워드2", 1);
        keywordRepository.save(keyword1);
        keywordRepository.save(keyword2);

        Keyword findMember1 = keywordRepository.findById(keyword1.getId()).get();
        Keyword findMember2 = keywordRepository.findById(keyword2.getId()).get();
        assertThat(findMember1).isEqualTo(keyword1);
        assertThat(findMember2).isEqualTo(keyword2);

        List<Keyword> all = keywordRepository.findAll();
        assertThat(all.size()).isEqualTo(2);

        long count = keywordRepository.count();
        assertThat(count).isEqualTo(2);

        keywordRepository.delete(keyword1);
        keywordRepository.delete(keyword2);
        long deletedCount = keywordRepository.count();
        assertThat(deletedCount).isEqualTo(0);
    }
}