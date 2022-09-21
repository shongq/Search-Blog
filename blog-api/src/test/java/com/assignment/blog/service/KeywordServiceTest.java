package com.assignment.blog.service;

import com.assignment.blog.common.api.LocalLockProvider;
import com.assignment.blog.dto.SearchBlogRequest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootTest
@Transactional
@Rollback(value = false)
class KeywordServiceTest {

    @Autowired
    KeywordService keywordService;

    @Autowired
    LocalLockProvider localLockProvider;

    @Autowired UpdateKeywordHandler updateKeywordHandler;

    @Test
    @DisplayName("키워드 검색 수 업데이트 시 동시성 이슈에 대한 처리")
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
        Assertions.assertThat(keywordService.findByWord(word).getSearchCount()).isEqualTo(100);
    }
}