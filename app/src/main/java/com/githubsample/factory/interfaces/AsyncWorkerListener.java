package com.githubsample.factory.interfaces;

import com.githubsample.tools.okhttp.OKHTTPResponse;

public interface AsyncWorkerListener {
    void onStart();
    void onComplete(OKHTTPResponse response);
    void onFinished();
    void onException(String exceptionMsg);
}
