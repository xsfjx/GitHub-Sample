package com.githubsample.factory.real;

import android.content.Context;

import com.githubsample.factory.interfaces.AsyncWorkerListener;
import com.githubsample.factory.interfaces.IGithubDataProvider;
import com.githubsample.tools.api.github.GitHubApi;

public class RealGithubDataProvider implements IGithubDataProvider {

    private Context context;

    public RealGithubDataProvider(Context context) {
        this.context = context;
    }

    @Override
    public void getGithubProfile(final AsyncWorkerListener listener) {
        listener.onStart();
        GitHubApi.getProfile(response -> {
            if (response.isServiceUnavailable()) {
                listener.onFinished();
                listener.onException(response.getErrorMessage());
            } else if (response.isSuccess() && response.isBodyHasValue()) {
                listener.onComplete(response);
                listener.onFinished();
            }

        });
    }

}
