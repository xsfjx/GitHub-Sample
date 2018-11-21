package com.githubsample.factory.interfaces;

import com.githubsample.tools.okhttp.OKHTTPRunnable;

public interface IGithubDataProvider {

    void getGithubProfile(OKHTTPRunnable runnable);

}
