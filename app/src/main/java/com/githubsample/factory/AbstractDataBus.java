package com.githubsample.factory;

import com.githubsample.factory.interfaces.IDataProvider;

public abstract class AbstractDataBus {

    private IDataProvider dataProviderLover;

    protected abstract void restart();


    public void add(IDataProvider lover) {
        this.dataProviderLover = lover;

    }

    public abstract void gitHubProfileDataIsReady();

    public abstract void gitHubAvatarIsReady();
}
