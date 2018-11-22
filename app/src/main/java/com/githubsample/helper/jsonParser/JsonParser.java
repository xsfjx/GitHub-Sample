package com.githubsample.helper.jsonParser;

import com.githubsample.tools.dto.MainDto;
import com.githubsample.tools.okhttp.OKHTTPResponse;

import org.json.JSONException;
import org.json.JSONObject;

public class JsonParser {

    private MainDto dto;

    public JsonParser() {
        dto = new MainDto();
    }

    public MainDto parse(OKHTTPResponse response) throws JSONException {
        JSONObject jsonObject = new JSONObject(response.getBody().toString());
        //Set Strings On JSON Base
        dto.setLogin(jsonObject.getString("login"));
        dto.setAvatar_url(jsonObject.getString("avatar_url"));
        dto.setName(jsonObject.getString("name"));
        dto.setLocation(jsonObject.getString("location"));
        dto.setBio(jsonObject.getString("bio"));

        return dto;
    }


}
