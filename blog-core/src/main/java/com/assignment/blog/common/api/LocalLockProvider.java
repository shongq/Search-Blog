package com.assignment.blog.common.api;

import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

@Component
public class LocalLockProvider implements ILockProvider {

    private ConcurrentHashMap<String, AtomicBoolean> concurrentHashMap = new ConcurrentHashMap<>();

    @Override
    public Boolean tryAcquireLock(String word) {
        AtomicBoolean lockUsed = concurrentHashMap.computeIfAbsent(word, k -> new AtomicBoolean(false));
        return lockUsed.compareAndSet(false, true);
    }

    @Override
    public void releaseLock(String word) {
        concurrentHashMap.get(word).set(false);
    }
}
