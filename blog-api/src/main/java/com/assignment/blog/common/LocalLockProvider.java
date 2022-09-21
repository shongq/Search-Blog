package com.assignment.blog.common;

import com.assignment.blog.common.api.ILockProvider;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

@Component
public class LocalLockProvider implements ILockProvider {

    public ConcurrentHashMap<String, AtomicBoolean> concurrentHashMap = new ConcurrentHashMap<>();

    @Override
    public Boolean tryAcquireLock(String word) {
        AtomicBoolean lockUsed = concurrentHashMap.computeIfAbsent(word, k -> new AtomicBoolean(false));
        if (!lockUsed.get()) {
            lockUsed.set(true);
        }
        return lockUsed.get();
    }

    @Override
    public void releaseLock(String word) {
        concurrentHashMap.get(word).set(false);
    }
}
