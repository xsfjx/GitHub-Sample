package com.githubsample.tools.api;

import com.githubsample.tools.okhttp.OKHTTP;
import com.githubsample.tools.okhttp.OKHTTPPairValue;
import com.githubsample.tools.okhttp.OKHTTPRunnable;

import java.util.ArrayList;

public class GeneralApi {

    static <T> void getItemList(final Class<T> cls, String url, OKHTTPRunnable runnable, ArrayList<OKHTTPPairValue> pairs) {

        OKHTTP.postInBackground(url,
                null,
                runnable,
                pairs,
                ApiConfig.getHeaders());

    }

    static <T> void getItemList(final Class<T> cls, String url, OKHTTPRunnable runnable) {

        OKHTTP.postInBackground(url,
                null,
                runnable,
                new ArrayList<OKHTTPPairValue>(),
                ApiConfig.getHeaders());


        /*if (response.isSuccess()) {
            try {
                Type type = new ParameterizedType() {
                    @Override
                    public Type[] getActualTypeArguments() {
                        return new Type[]{cls};
                    }

                    @Override
                    public Type getOwnerType() {
                        return ArrayList.class;
                    }

                    @Override
                    public Type getRawType() {
                        return null;
                    }
                };
                return ModelParser.getGson().fromJson(response.getBody().getJSONArray(Tags.DATA).toString(), type);
            } catch (Exception e) {
                return null;
            }
        }
        return null;*/
    }


    public static <T> void getItemList(String url, OKHTTPRunnable runnable) {
        OKHTTP.getInBackground(url,
                runnable,
                ApiConfig.getHeaders());

    }

}
