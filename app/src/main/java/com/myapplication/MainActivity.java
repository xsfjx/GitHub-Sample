package com.myapplication;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.myapplication.API.LastUpdateApi;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setMessage("Wait!!!");
        progressDialog.show();

        LastUpdateApi.getLastUpdate(response -> {
            Log.i("Log", "run: ");
            progressDialog.dismiss();
        });

    }
}
