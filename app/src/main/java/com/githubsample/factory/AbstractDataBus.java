package com.githubsample.factory;

import com.githubsample.factory.interfaces.IGithubDataProvider;

public abstract class AbstractDataBus {

    private IGithubDataProvider dataProviderLover;

    public AbstractDataBus() {
    }

    void init(IGithubDataProvider dataProviderLover) {
        add(dataProviderLover);
    }

    public void add(IGithubDataProvider lover) {
        this.dataProviderLover = lover;
    }

    protected abstract void restart();
}
