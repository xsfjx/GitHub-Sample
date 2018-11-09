package com.githubsample.main;

import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.util.Log;

import com.githubsample.factory.IDataProvider;
import com.githubsample.factory.models.MainModel;
import com.githubsample.tools.dto.MainDto;
import com.githubsample.tools.okhttp.OKHTTPResponse;

import org.json.JSONException;
import org.json.JSONObject;

import static com.githubsample.tools.Tags.MY_PERMISSIONS_REQUEST_READ_CONTACTS;

class MainPresenter implements IDataProvider {

    private IMainView view;
    private MainDto dto;
    private MainModel model;

    MainPresenter() {
        dto = new MainDto();
        model = new MainModel();
    }

    void init(IMainView view) {
        this.view = view;
        this.model.init(this);
    }

    void viewIsReady() {
        view.showProgress();
        this.getGithubProfile();
    }

    private void getGithubProfile() {
        model.getGitHubModel();
    }


    @Override
    public void getGithubProfile(OKHTTPResponse response) {
        jsonParserToDto(response);
        view.fillData(dto);
        model.getGithubAvatar(dto.getAvatar_url());

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

    @Override
    public void getGitHubAvatar(Bitmap bitmap) {
        view.setAvatarImageView(bitmap);
        view.closeProgress();
        view.showMsg("Data is loaded!!!");
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

    void viewIsGone() {
    }
}
