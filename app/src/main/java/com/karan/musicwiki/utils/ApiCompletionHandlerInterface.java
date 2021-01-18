package com.karan.musicwiki.utils;

public interface ApiCompletionHandlerInterface<T> {
    void completed(T resource);
    void failed(int code);
    void failed(Throwable t);
}
