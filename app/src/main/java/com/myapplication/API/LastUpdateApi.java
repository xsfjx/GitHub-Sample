package com.myapplication.API;

import com.myapplication.OKHTTP.OKHTTPRunnable;

public class LastUpdateApi {

    // ****************** URL *************************
    private static final String LAST_UPDATE_URL = "http://arianroid.com/OutPut/Services/Updates/";

    // ****************** API ********************
    public static  void getLastUpdate(OKHTTPRunnable runnable){
        String url = LAST_UPDATE_URL  + 2;
        GeneralApi.getItemList(null,url,runnable);
    }

}
