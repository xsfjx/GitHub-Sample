package com.githubsample.factory.real;

import android.content.Context;
import android.graphics.Bitmap;

import com.githubsample.factory.AbstractDataBus;
import com.githubsample.factory.interfaces.AsyncWorkerListener;
import com.githubsample.factory.interfaces.IGithubDataProvider;

public class RealDataBus extends AbstractDataBus {

    private final static Object lockObj = new Object();
    private static RealDataBus instance;

    public RealDataBus() {
        super();
    }

    public static RealDataBus getInstance() {
        if (instance == null) {
            synchronized (lockObj) {
                instance = new RealDataBus();
            }
        }
        return instance;
    }

    public void initail(Context context) {
        add(new IGithubDataProvider() {
            @Override
            public void getGithubProfileData(AsyncWorkerListener listener) {

            }

            @Override
            public void getGithubProfileAvatar(AsyncWorkerListener<Bitmap> listener, String url) {

            }
        });
    }


    @Override
    protected void restart(){
        instance = null;
        instance = getInstance();
    }
}
