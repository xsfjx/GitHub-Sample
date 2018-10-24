package com.githubsample.tools;

import android.content.Context;
import android.content.Intent;

import com.githubsample.main.MainActivity;

public class PageCall {

    public static void startSecondActivity(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
    public static void startSecondActivity(Context context , String name) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(Tags.F_NAME , name);
        context.startActivity(intent);
    }




}
