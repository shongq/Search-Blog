package com.assignment.blog.common.api;

public interface ILockProvider {

    Boolean tryAcquireLock(String word);
    void releaseLock(String word);
}
