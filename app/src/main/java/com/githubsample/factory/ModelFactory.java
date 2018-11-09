package com.githubsample.factory;

import android.content.Context;

import com.githubsample.factory.interfaces.IGithubDataProvider;
import com.githubsample.factory.models.realModel.RealGithubDataProvider;

public class ModelFactory {

    private static ModelFactory instance = null;
    private AbstractDataBus bus = null;
    private IGithubDataProvider githubDataProvider;
    private Context context;

    private ModelFactory() {
    }

    public static ModelFactory getInstance() {
        if (instance == null) {
            instance = new ModelFactory();
        }
        return instance;
    }

    private void reset() {
        instance = null;
        bus = null;
        githubDataProvider = null;
        context = null;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public AbstractDataBus createDataBus(){
        if (bus == null){
            //bus = RealDataBus().getInstance();
            // TODO: 11/9/2018
        }
        return null;
    }

    public IGithubDataProvider createGithubDataProvider(){
        if (githubDataProvider == null){
            githubDataProvider = new RealGithubDataProvider(this.context);
        }
        return githubDataProvider;
    }

}
