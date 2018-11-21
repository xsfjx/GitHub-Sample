package com.githubsample.factory.interfaces;

import android.graphics.Bitmap;

public interface IGithubDataProvider {

    void getGithubProfileData(AsyncWorkerListener listener);
    void getGithubProfileAvatar(AsyncWorkerListener<Bitmap> listener , String url);

}
