package com.karan.musicwiki.utils;

public interface NewApiCompletionHandlerInterface<T> {

    void completed(T resource);
    void errorMessage(T resource);
    void failed(int code);
    void failed(Throwable t);
}
