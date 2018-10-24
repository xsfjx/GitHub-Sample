package com.githubsample.helper.permission;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.githubsample.helper.messages.MessageHelper;

import static com.githubsample.tools.Tags.MY_PERMISSIONS_REQUEST_READ_CONTACTS;

public class PermissionHelper {

    public static void getPermission(Activity context) {
        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {
            // Should we show an explanation?
            Log.i("Log", "getPermission: ");

            if (ActivityCompat.shouldShowRequestPermissionRationale((context),
                    Manifest.permission.READ_CONTACTS)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                Log.i("Log", "getPermission: ");
                MessageHelper.showMessage((context), "should dead");
            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(context,
                        new String[]{Manifest.permission.READ_CONTACTS},
                        MY_PERMISSIONS_REQUEST_READ_CONTACTS);
                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
                Log.i("Log", "getPermission: ");
            }
        } else {
            Log.i("Log", "getPermission: ");
            //permission granted. do your stuff
        }
    }

}
