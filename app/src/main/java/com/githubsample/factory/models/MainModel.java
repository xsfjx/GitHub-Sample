package com.githubsample.factory.models;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

import com.githubsample.factory.interfaces.IDataProvider;
import com.githubsample.tools.api.github.GitHubApi;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

public class MainModel {

    private IDataProvider dataProvider;

    public void init(IDataProvider dataProvider) {
        this.dataProvider = dataProvider;
    }

    public void getGitHubModel() {
        GitHubApi.getProfile(response -> {
            dataProvider.getGithubProfile(response);
        });
    }

    public void getGithubAvatar(String url) {
        Picasso.get().load(url).into(new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                dataProvider.getGitHubAvatar(bitmap);
            }

            @Override
            public void onBitmapFailed(Exception e, Drawable errorDrawable) {
            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {
            }
        });
    }

}

