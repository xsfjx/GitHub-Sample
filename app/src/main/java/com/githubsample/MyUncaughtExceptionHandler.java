package com.githubsample;

import android.content.Context;
import android.util.Log;

public class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {

    private Context context;

    @Override
    public void uncaughtException(Thread thread, Throwable throwable) {
        Log.i("Log", "uncaughtException: ");
        
    }

    public MyUncaughtExceptionHandler(Context context) {
        this.context = context;
    }
}
