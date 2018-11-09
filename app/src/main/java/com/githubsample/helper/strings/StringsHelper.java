package com.githubsample.helper.strings;

import com.githubsample.factory.BusApplication;

public class StringsHelper {

    public static float parseToFloat(Object obj) {
        return Float.parseFloat(obj.toString());
    }

    public static int roundTheFloat(Object obj) {
        return Math.round(Float.parseFloat(obj.toString()));
    }

    public static String translateTimerProgress(int progress) {
        String timerValue;
        switch (progress) {
            case 1:
                timerValue = "45";
                break;
            case 2:
                timerValue = "60";
                break;
            case 3:
                timerValue = "90";
                break;
            case 4:
                timerValue = "120";
                break;
            case 5:
                timerValue = "150";
                break;
            default:
                timerValue = "";
                break;
        }
        timerValue += " ثانیه";
        return timerValue;

    }

    public static String getString(int id) {
        return BusApplication.busContext.getString(id);
    }

}
