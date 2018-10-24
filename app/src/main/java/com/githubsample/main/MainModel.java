package com.githubsample.main;

import com.githubsample.tools.api.github.GitHubApi;
import com.squareup.picasso.Picasso;

class MainModel {

    private IDataProvider dataProvider;

    void init(IDataProvider dataProvider) {
        this.dataProvider = dataProvider;
    }

    void getGitHubModel() {
        GitHubApi.getProfile(response -> {
            dataProvider.getGitHubProfile(response);
        });
    }

    void getGithubAvatar(String url) {
        Picasso.get().load(url);
    }

}

