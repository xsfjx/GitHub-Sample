package com.githubsample.factory.models.realModel;

import android.content.Context;

import com.githubsample.factory.interfaces.IGithubDataProvider;
import com.githubsample.tools.api.github.GitHubApi;
import com.githubsample.tools.okhttp.OKHTTPRunnable;

public class RealGithubDataProvider implements IGithubDataProvider {

    private Context context;
    private IGithubDataProvider dataProvider;

    public RealGithubDataProvider(Context context) {
        this.context = context;
    }

    @Override
    public void getGithubProfile(OKHTTPRunnable runnable) {
        GitHubApi.getProfile(runnable);
    }


}
