package com.githubsample.main;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.githubsample.R;
import com.githubsample.helper.base.BaseActivity;
import com.githubsample.helper.messages.MessageHelper;
import com.githubsample.helper.permission.PermissionHelper;
import com.githubsample.tools.dto.MainDto;

public class MainActivity extends BaseActivity implements IMainView {

    private ImageView imageView;
    private TextView txtName, txtLogin, txtLocation, txtBio;
    private MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initActivity();
        createPresenter();
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.viewIsReady();
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.viewIsGone();

    }

    private void createPresenter() {
        presenter = new MainPresenter();
        presenter.init(this);
    }

    private void initActivity() {
        PermissionHelper.getPermission(this);
        imageView = findViewById(R.id.imageView);
        txtName = findViewById(R.id.txtName);
        txtLogin = findViewById(R.id.txtLogin);
        txtBio = findViewById(R.id.txtBio);
        txtLocation = findViewById(R.id.txtLocation);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        presenter.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void showMsg(String msg) {
        MessageHelper.showMessage(this, msg);
    }

    @Override
    public void showProgress() {
        showWaitingDialog();
    }

    @Override
    public void closeProgress() {
        dismissWaitingDialog();
    }

    @Override
    public void fillData(MainDto dto) {
        txtName.setText(dto.getName());
        txtLogin.setText(dto.getLogin());
        txtBio.setText(dto.getBio());
        txtLocation.setText(dto.getLocation());
    }

    @Override
    public void setAvatarImageView(Bitmap bitmap) {
        imageView.setImageBitmap(bitmap);
    }
}
