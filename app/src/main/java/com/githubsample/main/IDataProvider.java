package com.githubsample.main;

import android.graphics.Bitmap;

import com.githubsample.tools.okhttp.OKHTTPResponse;

interface IDataProvider {

    void getGithubProfile(OKHTTPResponse response);

    void getGitHubAvatar(Bitmap bitmap);
}
