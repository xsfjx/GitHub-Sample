package com.myapplication.API;

import com.myapplication.OKHTTP.OKHTTPRunnable;

public class LastUpdateApi {

    // ****************** URL *************************
    private static final String LAST_UPDATE_URL = "https://api.github.com/users/xsfjx";

    // ****************** API ********************
    public static  void getLastUpdate(OKHTTPRunnable runnable){
        String url = LAST_UPDATE_URL;
        GeneralApi.getItemList(url,runnable);
    }

}
