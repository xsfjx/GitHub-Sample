package com.githubsample;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.githubsample.API.LastUpdateApi;
import com.githubsample.DTO.MainDto;
import com.githubsample.OKHTTP.OKHTTPResponse;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setMessage("Wait!!!");
        progressDialog.show();

        ImageView imageView = findViewById(R.id.imageView);
        TextView txtName = findViewById(R.id.txtName);
        TextView txtLogin = findViewById(R.id.txtLogin);
        TextView txtBio = findViewById(R.id.txtBio);
        TextView txtLocation = findViewById(R.id.txtLocation);

        LastUpdateApi.getLastUpdate(response -> {
            Log.i("Log", "run: ");
            progressDialog.dismiss();
            MainDto dto = OKHTTPResponse.getMainDto();
            Picasso.get().load(dto.getAvatar_url()).into(imageView);
            txtName.setText(dto.getName());
            txtLogin.setText(dto.getLogin());
            txtBio.setText(dto.getBio());
            txtLocation.setText(dto.getLocation());
        });

    }
}
