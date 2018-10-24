package com.githubsample.helper.base;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.githubsample.tools.exceptionHandler.MyUncaughtExceptionHandler;
import com.google.firebase.analytics.FirebaseAnalytics;

public class BusApplication extends Application {

    public static Context busContext;
    public static String dataDir, versionName;
    public static int versionCode;
    private static FirebaseAnalytics mFirebaseAnalytics;

    public static FirebaseAnalytics getmFirebaseAnalytics() {
        return mFirebaseAnalytics;
    }

    public static void getAppDataDir() {
        PackageManager m = BusApplication.busContext.getPackageManager();
        String name = BusApplication.busContext.getPackageName();
        try {
            PackageInfo p = m.getPackageInfo(name, 0);
            name = p.applicationInfo.dataDir;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        BusApplication.dataDir = name;
    }

    public static void getAppPackageInfo() {
        try {
            versionName = busContext.getPackageManager()
                    .getPackageInfo(getAppPackageName(), 0).versionName;
            versionCode = busContext.getPackageManager()
                    .getPackageInfo(getAppPackageName(), 0).versionCode;

        } catch (PackageManager.NameNotFoundException e) {
            versionCode = 0;
            versionName = "";
        }
    }

    private static String getAppPackageName() {
        return busContext.getPackageName();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        busContext = getApplicationContext();
        Thread.setDefaultUncaughtExceptionHandler(new MyUncaughtExceptionHandler(busContext));
        getAppDataDir();
        getAppPackageInfo();
        initFirebaseAnalytics();
    }

    private void initFirebaseAnalytics() {
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
    }


}
