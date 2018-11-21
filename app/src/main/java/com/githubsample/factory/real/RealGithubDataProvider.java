package com.githubsample.factory.real;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

import com.githubsample.factory.interfaces.AsyncWorkerListener;
import com.githubsample.factory.interfaces.IGithubDataProvider;
import com.githubsample.tools.api.github.GitHubApi;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

public class RealGithubDataProvider implements IGithubDataProvider {

    private Context context;

    public RealGithubDataProvider(Context context) {
        this.context = context;
    }

    @Override
    public void getGithubProfileData(final AsyncWorkerListener listener) {
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

    @Override
    public void getGithubProfileAvatar(AsyncWorkerListener<Bitmap> listener, String url) {
        listener.onStart();
        Picasso.get().load(url).into(new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                listener.onComplete(bitmap);
                listener.onFinished();
            }

            @Override
            public void onBitmapFailed(Exception e, Drawable errorDrawable) {
                listener.onFinished();
                listener.onException(e.getMessage());
            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        });
    }

}
