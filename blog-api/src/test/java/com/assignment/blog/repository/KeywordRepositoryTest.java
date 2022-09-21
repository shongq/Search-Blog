package com.assignment.blog.repository;

import com.assignment.blog.domain.Keyword;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback
class KeywordRepositoryTest {

    @Autowired
    KeywordRepository keywordRepository;

    @Test
    @DisplayName("키워드 엔티티 기본 테스트")
    void basicKeyword(){
        //given
        Keyword keyword = new Keyword("테스트키워드", 1);

        //when
        Keyword savedKeyword = keywordRepository.save(keyword);
        Keyword findKeyword = keywordRepository.findByWord(savedKeyword.getWord()).get();

        //then
        assertThat(findKeyword.getId()).isEqualTo(keyword.getId());
        assertThat(findKeyword.getWord()).isEqualTo(keyword.getWord());
        assertThat(findKeyword.getSearchCount()).isEqualTo(keyword.getSearchCount());
        assertThat(findKeyword).isEqualTo(keyword);
    }
    
    @Test
    @DisplayName("키워드 엔티티 기본 CRUD 테스트")
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

    @Test
    @DisplayName("검색어로 키워드 가져오기")
    void findByWord(){
        //given
        String word = "네이버통장";
        Keyword keyword = new Keyword(word, 3);;
        keywordRepository.save(keyword);

        //when
        Optional<Keyword> findKeyword = keywordRepository.findByWord(word);

        //then
        assertThat(findKeyword.isPresent()).isTrue();
        assertThat(findKeyword.get().getWord()).isEqualTo(word);
        assertThat(findKeyword.get().getSearchCount()).isEqualTo(3);
    }

    @Test
    @DisplayName("검색어 수가 많은 순으로 10개 키워드 가져오기")
    void findTop10Keyword(){
        //given
        Keyword keyword1 = new Keyword("카카오뱅크", 10);;
        keywordRepository.save(keyword1);
        Keyword keyword2 = new Keyword("토스뱅크", 9);;
        keywordRepository.save(keyword2);
        Keyword keyword3 = new Keyword("국민은행", 8);;
        keywordRepository.save(keyword3);
        Keyword keyword4 = new Keyword("신한은행", 2);;
        keywordRepository.save(keyword4);
        Keyword keyword5 = new Keyword("하나은행", 1);;
        keywordRepository.save(keyword5);

        //when
        List<Keyword> top10Keywords = keywordRepository.findTop10ByOrderBySearchCountDesc();

        //then
        assertThat(top10Keywords.size()).isEqualTo(5);
        assertThat(top10Keywords.get(0).getWord()).isEqualTo("카카오뱅크");
        assertThat(top10Keywords.get(1).getWord()).isEqualTo("토스뱅크");
        assertThat(top10Keywords.get(2).getWord()).isEqualTo("국민은행");
        assertThat(top10Keywords.get(3).getWord()).isEqualTo("신한은행");
        assertThat(top10Keywords.get(4).getWord()).isEqualTo("하나은행");
    }
}