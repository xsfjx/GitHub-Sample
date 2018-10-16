package com.githubsample;

import android.util.Log;

public class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread thread, Throwable throwable) {
        Log.i("Log", "uncaughtException: ");
    }
}
