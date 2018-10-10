package com.myapplication.API;

import com.myapplication.OKHTTP.OKHTTPPairValue;

import java.util.ArrayList;

public class ApiConfig {

    // *************** URL ************
    public static final String TYPE_SERVICE = "http://arianroid.com/OutPut/Services/Type";
    public static final String GROUP_TYPE_SERVICE = "http://arianroid.com/OutPut/Services/GroupType";
    public static final String WORDS_SERVICE = "http://arianroid.com/OutPut/Services/Words";


    // **************************** HEADER *****************************

    static ArrayList<OKHTTPPairValue> getHeaders() {
        ArrayList<OKHTTPPairValue> headers = new ArrayList<>();
        headers.add(new OKHTTPPairValue("Content-Type", "application/x-www-form-urlencoded"));
        headers.add(new OKHTTPPairValue("Accept-Charset", "unicode"));
        headers.add(new OKHTTPPairValue("Charset", "unicode"));
        return headers;
    }

}
