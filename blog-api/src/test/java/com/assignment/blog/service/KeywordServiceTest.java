package com.assignment.blog.service;

import com.assignment.blog.common.LocalLockProvider;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

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

    @Test
    @DisplayName("키워드 검색 수 업데이트 시 동시성 이슈에 대한 처리")
    void plusKeywordSearchCountForMultiThreadTest() throws InterruptedException {
        //given
        int numberOfExcute = 100;
        ExecutorService service = Executors.newFixedThreadPool(5);
        CountDownLatch latch = new CountDownLatch(numberOfExcute);
        String word = "카카오뱅크";

        //when
        for(int i=0 ; i<numberOfExcute ; i++) {
            service.execute(() -> {
                /*try {
                    keywordService.updateKeywordSearchCount(word);
                    System.out.println("성공");
                } catch(ObjectOptimisticLockingFailureException oe) {
                    try {
                        Thread.sleep(Thread.currentThread().getId() * 100);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println("충돌감지 후 재시도");
                    keywordService.updateKeywordSearchCount(word);
                } catch(Exception e) {
                    System.out.println(e.getMessage());
                }*/

                try {
                    while(!localLockProvider.isLock(word).getAndSet(true)) {
                        keywordService.updateKeywordSearchCount(word);
                    }
                } catch(Exception e) {
                    System.out.println(e.getMessage());
                }
                latch.countDown();
            });
        }
        latch.await();

        //then
        Assertions.assertThat(keywordService.findByWord(word).getSearchCount()).isEqualTo(110);
    }
}