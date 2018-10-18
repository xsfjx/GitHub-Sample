package com.githubsample.tools.internet;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;

import com.githubsample.BusApplication;

public class InternetTools {
    private static NetworkInfo networkInfo;

    public static boolean isOnline() {
        return getNetworkInfoState();
    }

    private static boolean getNetworkInfoState() {
        boolean internetConnectionIsOn = false;
        WifiManager wifiManager = (WifiManager) BusApplication.busContext.getSystemService(Context.WIFI_SERVICE);
        if (wifiManager.isWifiEnabled()) {
            internetConnectionIsOn = true;
        }

        ConnectivityManager cm = (ConnectivityManager) BusApplication.busContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        networkInfo = cm.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            internetConnectionIsOn = true;
        }

        return internetConnectionIsOn;
    }

    private  static NetworkInfo getNetworkInfo() {
        ConnectivityManager cm = (ConnectivityManager) BusApplication.busContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        networkInfo = cm.getActiveNetworkInfo();

        return networkInfo;
    }

}
