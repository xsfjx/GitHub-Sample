package com.githubsample.API;

import com.githubsample.OKHTTP.OKHTTPPairValue;

import java.util.ArrayList;

public class ApiConfig {

    // *************** URL ************


    // **************************** HEADER *****************************

    static ArrayList<OKHTTPPairValue> getHeaders() {
        ArrayList<OKHTTPPairValue> headers = new ArrayList<>();
        headers.add(new OKHTTPPairValue("Content-Type", "application/x-www-form-urlencoded"));
        headers.add(new OKHTTPPairValue("Accept-Charset", "unicode"));
        headers.add(new OKHTTPPairValue("Charset", "unicode"));
        return headers;
    }

}
