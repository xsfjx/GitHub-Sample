package com.githubsample;

import android.app.Application;
import android.content.Context;

public class MyApplication extends Application {

    public static Context busContext;
    public static String dataDir, versionName;
    public static int versionCode;


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
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        busContext = getApplicationContext();
        Thread.setDefaultUncaughtExceptionHandler(
                new AdabaziUncaughtExceptionHandler(busContext));
        getAppDataDir();
        getAppPackageInfo();
        initGoogleAnalytics();
        initOneSignal();
        initTapsell();
        Pushe.initialize(this,true);

    }

    private void initGoogleAnalytics() {
        AnalyticsTrackers.initial(this);
        AnalyticsTrackers.getInstance().getTracker();
    }

    private void initOneSignal() {
        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(false)
                .setNotificationReceivedHandler(new AdabaziNotificationReceivedHandler())
                .init();
    }


    private void initTapsell() {
        TapsellConfiguration config =
                new TapsellConfiguration(BusApplication.busContext);
        config.setDebugMode(false);
        config.setPermissionHandlerMode(TapsellConfiguration.PERMISSION_HANDLER_AUTO);
        Tapsell.initialize(BusApplication.busContext, "rmtpmgsaphnnnmfrlbhfnpnrqmbrmgdjtsitgshckjeiqkerljfllngtqbtrqodncfpdih");

    }

}
