package com.githubsample.main;

import android.graphics.Bitmap;

import com.githubsample.tools.dto.GithubDto;

public interface IMainView {

    void showMsg (String msg);

    void showProgress ();

    void closeProgress ();

    void fillData(GithubDto dto);

    void setAvatarImageView(Bitmap bitmap);

}
