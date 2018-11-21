package com.githubsample.main;

import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.util.Log;

import com.githubsample.factory.ModelFactory;
import com.githubsample.factory.interfaces.AsyncWorkerListener;
import com.githubsample.factory.interfaces.IGithubDataProvider;
import com.githubsample.tools.dto.MainDto;
import com.githubsample.tools.okhttp.OKHTTPResponse;

import org.json.JSONException;
import org.json.JSONObject;

import static com.githubsample.tools.Tags.MY_PERMISSIONS_REQUEST_READ_CONTACTS;

class MainPresenter {

    private IGithubDataProvider modelProvider;
    private GithubAsyncListener listener;
    private GithubAvatarAsyncListener avatarListener;

    private IMainView view;
    private MainDto dto;

    MainPresenter() {
        dto = new MainDto();
    }

    void init(IMainView view) {
        listener = new GithubAsyncListener();
        avatarListener = new GithubAvatarAsyncListener();
        this.view = view;
    }

    void viewIsReady() {
        modelProvider = ModelFactory.getInstance().createGithubDataProvider();
        modelProvider.getGithubProfileData(listener);
    }

    private void jsonParserToDto(OKHTTPResponse response) {
        try {
            JSONObject jsonObject = new JSONObject(response.getBody().toString());
            dto.setLogin(jsonObject.getString("login"));
            dto.setAvatar_url(jsonObject.getString("avatar_url"));
            dto.setName(jsonObject.getString("name"));
            dto.setLocation(jsonObject.getString("location"));
            dto.setBio(jsonObject.getString("bio"));
        } catch (JSONException e) {
            view.showMsg("error in json ");
        }
    }

    void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_CONTACTS: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.i("Log", "onRequestPermissionsResult: ");
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                } else {
                    Log.i("Log", "onRequestPermissionsResult: ");
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request.
        }

    }

    void viewIsGone() { }

    private class GithubAsyncListener implements AsyncWorkerListener<OKHTTPResponse> {
        @Override
        public void onStart() {
            view.showProgress();
        }

        @Override
        public void onComplete(OKHTTPResponse response) {
            jsonParserToDto(response);
            view.fillData(dto);
            modelProvider.getGithubProfileAvatar(avatarListener, dto.getAvatar_url());
            view.showMsg("DATA LOADED!!!");
        }

        @Override
        public void onFinished() {
            view.closeProgress();
        }

        @Override
        public void onException(String exceptionMsg) {
            view.closeProgress();
            view.showMsg(exceptionMsg);
        }


    }

    private class GithubAvatarAsyncListener implements AsyncWorkerListener<Bitmap> {
        @Override
        public void onStart() {
        }

        @Override
        public void onComplete(Bitmap bitmap) {
            view.setAvatarImageView(bitmap);
        }

        @Override
        public void onFinished() {
        }

        @Override
        public void onException(String exceptionMsg) {
            view.closeProgress();
            view.showMsg(exceptionMsg);
        }


    }
}
