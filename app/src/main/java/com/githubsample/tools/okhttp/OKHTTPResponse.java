package com.githubsample.tools.okhttp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class OKHTTPResponse {

    // *********************** Constants ***************************/
    private static final int UNKNOWN_CODE = 0;

    // *********************** Variables ***************************/
    private JSONObject bodyJsonArray;
    private String bodyString = "";
    private int statusCode;
    private boolean responseBodyIsJson;

    // *********************** Constructor ***************************/
    OKHTTPResponse() {
        this(UNKNOWN_CODE, "{}");
    }

    OKHTTPResponse(int statusCode, String body) {
        this.statusCode = statusCode;
        try {
            this.bodyJsonArray = new JSONObject(body);
            responseBodyIsJson = true;
        } catch (JSONException e) {
            responseBodyIsJson = false;
            bodyString = body;
        }
    }

    public OKHTTPResponse(int statusCode, JSONObject body) {
        this.bodyJsonArray = body;
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

    public JSONObject getBody () throws JSONException {
        if (bodyJsonArray == null){
            try {
                this.bodyJsonArray = new JSONObject(bodyString);
                responseBodyIsJson = true;
            } catch (JSONException e) {
                responseBodyIsJson = false;
                bodyString = "";
                throw e;
            }
        }
        return bodyJsonArray;
    }

    // **************************  Methods  ********************************


    public boolean isSuccess() {
        return getStatusCode() == 200 || getStatusCode() == 201;
    }

    public boolean isBodyEmpty() {
        if (responseBodyIsJson)
            return bodyJsonArray.toString().isEmpty() || bodyJsonArray.toString().length() == 2;
        else return (bodyString.isEmpty() || bodyString.length() == 2) && bodyString.contains("{");
    }

    public boolean isBodyHasValue() {
        if(responseBodyIsJson){
            return !bodyJsonArray.toString().isEmpty() || bodyJsonArray.toString().length() != 2;
        }else
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

    public String getErrorMessage() {
        if (!isSuccess()) {
            try {
                return getBody().getString("errorMessage");
            } catch (JSONException e) {
                return "لطفا کمی بعد مجددا تلاش کنید";
            }
        }
        return "";
    }


}

