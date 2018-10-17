package com.githubsample.tools.okhttp;

import com.githubsample.tools.dto.MainDto;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class OKHTTPResponse {

    // *********************** Constants ***************************/
    private static final int UNKNOWN_CODE = 0;

    // *********************** Variables ***************************/
    private JSONArray bodyJsonArray;
    private String bodyString = "";
    private int statusCode;
    private boolean responseBodyIsJson;
    private JSONObject jsonObject;
    private static MainDto mainDto = new MainDto();

    // *********************** Constructor ***************************/
    OKHTTPResponse() throws JSONException {
        this(UNKNOWN_CODE, "{}");
    }

    OKHTTPResponse(int statusCode, String body) {
        this.statusCode = statusCode;
        try {
            this.bodyJsonArray = new JSONArray(body);
            jsonObject = new JSONObject(body);
            fillMainDto();
            responseBodyIsJson = true;
        } catch (JSONException e) {
            responseBodyIsJson = false;
            bodyString = body;
            try {
                jsonObject = new JSONObject(body);
                fillMainDto();
            } catch (JSONException e1) {
                e1.printStackTrace();
            }
        }
    }

    public OKHTTPResponse(int statusCode, JSONArray body) {
        this.bodyJsonArray = bodyJsonArray;
        this.statusCode = statusCode;
        responseBodyIsJson = (body != null);
    }


    // *********************** Getter/Setter ***************************/
    public String getBodyString() {
        return bodyString;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public JSONArray getBody () throws JSONException {
        if (bodyJsonArray == null){
            try {
                this.bodyJsonArray = new JSONArray(bodyString);
                responseBodyIsJson = true;
            } catch (JSONException e) {
                responseBodyIsJson = false;
                bodyString = "";
                throw e;
            }
        }
        return bodyJsonArray;
    }

    public static MainDto getMainDto() {
        return mainDto;
    }

    // **************************  Methods  ********************************

    private void fillMainDto() throws JSONException {
        mainDto.setLogin(jsonObject.getString("login"));
        mainDto.setAvatar_url(jsonObject.getString("avatar_url"));
        mainDto.setName(jsonObject.getString("name"));
        mainDto.setLocation(jsonObject.getString("location"));
        mainDto.setBio(jsonObject.getString("bio"));
    }

    public boolean isSuccess() {
        return getStatusCode() == 200 || getStatusCode() == 201;
    }

    public boolean isBodyEmpty() {
        if (responseBodyIsJson)
            return bodyJsonArray.toString().isEmpty() || bodyJsonArray.toString().length() == 2;
        else return (bodyString.isEmpty() || bodyString.length() == 2) && bodyString.contains("{");
    }

    public boolean isBodyHasValue() {
        return !bodyString.isEmpty() || bodyString.length() != 0;
    }

    public boolean isTimeOut() {
        return bodyString.equals("time out");

    }

    public boolean isErrorData() {
        return bodyString.equals("error data");
    }

    public boolean isExist() {
        return bodyString.equals("exist");
    }

    public boolean isNotExist() {
        return bodyString.equals("not exist");
    }

    public boolean isOk() {
        return bodyString.equals("ok");
    }

    public boolean isErrorExist() {
        return bodyString.equals("error exist");
    }


    public boolean isServiceUnavailable() {
        return getStatusCode() != 200;
    }


    public boolean isResponseBodyJson() {
        return responseBodyIsJson;
    }

    /*public String getErrorMessage() {
        if (!bodyIsSuccess()) {
            try {
                return getBody().getString("errorMessage");
            } catch (JSONException e) {
                // TODO: 1/9/2018 improve
                return "لطفا کمی بعد مجددا تلاش کنید";
            }
        }
        return "";
    }*/


}

