package com.assignment.blog.service;

import com.assignment.blog.dto.HandlerResponse;
import com.assignment.blog.dto.SearchBlogRequest;
import com.assignment.blog.handler.UpdateKeywordHandler;
import com.assignment.blog.repository.KeywordRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback
class KeywordServiceTest {
    @Autowired
    KeywordService keywordService;
    @Autowired
    UpdateKeywordHandler updateKeywordHandler;
    @Autowired
    KeywordRepository keywordRepository;

    @Test
    @DisplayName("키워드 SearchCount 업데이트 기본 테스트")
    void plusKeywordSearchCount(){
        //given
        String word = "카카오뱅크";

        //when
        HandlerResponse resolve = updateKeywordHandler.resolve(
                SearchBlogRequest.builder().query(word).build(), new HandlerResponse());

        //then
        assertThat(resolve.isSuccess()).isTrue();
        assertThat(keywordRepository.findByWord(word)).isPresent();
        assertThat(keywordRepository.findByWord(word).get().getWord()).isEqualTo(word);
        assertThat(keywordRepository.findByWord(word).get().getSearchCount()).isEqualTo(1);

    }

    @Test
    @DisplayName("키워드 SearchCount 업데이트 시 동시성 이슈에 대한 처리")
    void plusKeywordSearchCountForMultiThreadTest() throws InterruptedException {
        //given
        int numberOfExcute = 100;
        ExecutorService service = Executors.newFixedThreadPool(5);
        CountDownLatch latch = new CountDownLatch(numberOfExcute);
        String word = "상상뱅크";

        //when
        for(int i=0 ; i<numberOfExcute ; i++) {
            service.execute(() -> {
                try {
                    updateKeywordHandler.resolve(SearchBlogRequest.builder().query(word).build(), null);
                } catch(Exception e) {
                    System.out.println(e.getMessage());
                }
                latch.countDown();
            });
        }
        latch.await();

        //then
        assertThat(keywordService.findByWord(word).getSearchCount()).isEqualTo(100);
    }

}