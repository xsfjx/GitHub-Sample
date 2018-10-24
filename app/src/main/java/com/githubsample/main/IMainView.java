package com.githubsample.main;

import android.graphics.Bitmap;

import com.githubsample.tools.dto.MainDto;

public interface IMainView {

    void showMsg (String msg);

    void showProgress ();

    void closeProgress ();

    void fillData(MainDto dto);

    void setAvatarImageView(Bitmap bitmap);

}
