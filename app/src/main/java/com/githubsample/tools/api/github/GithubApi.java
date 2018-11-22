package com.githubsample.tools.api.github;

import com.githubsample.tools.api.GeneralApi;
import com.githubsample.tools.okhttp.OKHTTPRunnable;

public class GithubApi {

    // ****************** URL *************************
    private static final String LAST_UPDATE_URL = "https://api.github.com/users/xsfjx";

    // ****************** API ********************
    public static  void getProfile(OKHTTPRunnable runnable){
        String url = LAST_UPDATE_URL;
        GeneralApi.getItemList(url,runnable);
    }

}
