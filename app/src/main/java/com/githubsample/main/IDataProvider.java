package com.githubsample.main;

import com.githubsample.tools.okhttp.OKHTTPResponse;
import com.squareup.picasso.RequestCreator;

interface IDataProvider {

    void getGitHubProfile(OKHTTPResponse response);

    void getGitHubAvatar(RequestCreator creator);
}
