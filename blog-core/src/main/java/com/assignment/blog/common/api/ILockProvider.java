package com.assignment.blog.common.api;

import java.util.concurrent.atomic.AtomicBoolean;

public interface ILockProvider {
    AtomicBoolean isLock(String word);
    void updateIsLock(String word, AtomicBoolean isLock);
}
