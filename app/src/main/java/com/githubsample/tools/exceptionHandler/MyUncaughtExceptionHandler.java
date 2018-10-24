package com.githubsample.tools.exceptionHandler;

import android.content.Context;
import android.util.Log;

import com.crashlytics.android.Crashlytics;

public class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {

    private Context context;

    @Override
    public void uncaughtException(Thread thread, Throwable throwable) {
        Log.i("Log", "uncaughtException: ");
        Crashlytics.log(Log.getStackTraceString(throwable));
        Crashlytics.logException(throwable);
        Crashlytics.setUserName("Saeed");
        Crashlytics.log(Log.DEBUG, "MMM", "Zert");
        Log.i("Log", "uncaughtException: ");
    }

    public MyUncaughtExceptionHandler(Context context) {
        this.context = context;
    }
}
