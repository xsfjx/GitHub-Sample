package com.githubsample.factory;

import com.githubsample.factory.interfaces.IDataProvider;
import com.githubsample.factory.models.MainModel;

public abstract class AbstractDataBus {

    private IDataProvider dataProviderLover;
    private MainModel model;

    AbstractDataBus() {
        model = new MainModel();
    }

    void init(IDataProvider dataProviderLover) {
        add(dataProviderLover);
        model.init(dataProviderLover);
    }

    public void add(IDataProvider lover) {
        this.dataProviderLover = lover;
    }

    void onGitHubProfileDataIsReady() {
        model.getGitHubModel();
    }

    void onGitHubAvatarIsReady(String url) {
        model.getGithubAvatar(url);
    }

    protected abstract void restart();
}
