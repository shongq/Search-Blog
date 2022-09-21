package com.assignment.blog.common;

import com.assignment.blog.common.api.ILockProvider;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

@Component
public class LocalLockProvider implements ILockProvider {

    public ConcurrentHashMap<String, AtomicBoolean> concurrentHashMap = new ConcurrentHashMap<>();

    @Override
    public AtomicBoolean isLock(String word) {
        return concurrentHashMap.get(word) == null? new AtomicBoolean():concurrentHashMap.get(word);
    }

    public void updateIsLock(String word, AtomicBoolean isLock) {
        concurrentHashMap.put(word, isLock);
    }
}
