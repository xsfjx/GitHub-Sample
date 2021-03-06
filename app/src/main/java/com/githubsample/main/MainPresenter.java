package com.githubsample.main;

import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.util.Log;

import com.githubsample.factory.ModelFactory;
import com.githubsample.factory.interfaces.AsyncWorkerListener;
import com.githubsample.factory.interfaces.IGithubDataProvider;
import com.githubsample.helper.jsonParser.JsonParser;
import com.githubsample.tools.dto.GithubDto;
import com.githubsample.tools.enums.TypeEnum;
import com.githubsample.tools.okhttp.OKHTTPResponse;

import org.json.JSONException;

import static com.githubsample.tools.Tags.MY_PERMISSIONS_REQUEST_READ_CONTACTS;

class MainPresenter {

    private IGithubDataProvider modelProvider;
    private GithubAsyncListener listener;
    private GithubAvatarAsyncListener avatarListener;

    private IMainView view;
    private GithubDto dto;
    private TypeEnum typeEnum;
    private JsonParser jsonParser;

    MainPresenter() {
        dto = new GithubDto();
        jsonParser = new JsonParser();
    }

    void init(IMainView view) {
        listener = new GithubAsyncListener();
        avatarListener = new GithubAvatarAsyncListener();
        typeEnum = TypeEnum.status;
        this.view = view;
    }

    void viewIsReady() {
        modelProvider = ModelFactory.getInstance().createGithubDataProvider();
        modelProvider.getGithubProfileData(listener);
        switch (typeEnum) {
            case status:
                break;
            case gallery:
                break;
            case pinCode:
                break;
        }
    }

    private void jsonParserToDto(OKHTTPResponse response) {
        try {
            dto = jsonParser.parse(response);
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
            view.showMsg("Data Loaded!!!");
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
