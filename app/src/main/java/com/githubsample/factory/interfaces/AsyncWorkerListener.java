package com.githubsample.factory.interfaces;

public interface AsyncWorkerListener<E> {

    void onStart();
    void onComplete(E e);
    void onFinished();
    void onException(String exceptionMsg);
}
