package com.githubsample;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.githubsample.helper.base.BaseActivity;
import com.githubsample.helper.messages.MessageHelper;
import com.githubsample.tools.api.LastUpdateApi;
import com.githubsample.tools.dto.MainDto;
import com.githubsample.tools.okhttp.OKHTTPResponse;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends BaseActivity {

    private ImageView imageView;
    private TextView txtName;
    private TextView txtLogin;
    private TextView txtLocation;
    private TextView txtBio;
    private MainDto mainDto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initActivity();
        showWaitingDialog();

        LastUpdateApi.getLastUpdate(response -> {
            Log.i("Log", "run: ");
            fillMainDto(response);
            Picasso.get().load(mainDto.getAvatar_url()).into(imageView);
            txtName.setText(mainDto.getName());
            txtLogin.setText(mainDto.getLogin());
            txtBio.setText(mainDto.getBio());
            txtLocation.setText(mainDto.getLocation());
            dismissWaitingDialog();
            MessageHelper.showMessage(this, "Data is loaded!");
        });

    }

    private void initActivity() {
        mainDto = new MainDto();
        imageView = findViewById(R.id.imageView);
        txtName = findViewById(R.id.txtName);
        txtLogin = findViewById(R.id.txtLogin);
        txtBio = findViewById(R.id.txtBio);
        txtLocation = findViewById(R.id.txtLocation);
    }

    private MainDto fillMainDto(OKHTTPResponse response) {
        try {
            JSONObject jsonObject = new JSONObject(response.getBody().toString());
            mainDto.setLogin(jsonObject.getString("login"));
            mainDto.setAvatar_url(jsonObject.getString("avatar_url"));
            mainDto.setName(jsonObject.getString("name"));
            mainDto.setLocation(jsonObject.getString("location"));
            mainDto.setBio(jsonObject.getString("bio"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return mainDto;
    }
}
