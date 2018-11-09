package com.githubsample.factory.interfaces;

import android.graphics.Bitmap;

import com.githubsample.tools.okhttp.OKHTTPResponse;

public interface IDataProvider {

    void getGithubProfile(OKHTTPResponse response);

    void getGitHubAvatar(Bitmap bitmap);
}
