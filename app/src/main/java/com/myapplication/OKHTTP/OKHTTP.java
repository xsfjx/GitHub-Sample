package com.myapplication.OKHTTP;

import android.os.AsyncTask;

import com.myapplication.task.TaskHelper;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import okhttp3.*;

public class OKHTTP {

    // ******************** Constants ***********************
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    // ******************** Basic Functions *****************
    private static OkHttpClient getClient() {
        return new OkHttpClient().newBuilder()
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS).build();
    }


    // ******************** Post ****************************


    private static OKHTTPResponse post(String url,
                                       JSONObject json,
                                       ArrayList<OKHTTPPairValue> parts,
                                       ArrayList<OKHTTPPairValue> headers) {

        RequestBody body = null;
        if (parts != null) {
            FormBody.Builder builder = new FormBody.Builder();
            for (OKHTTPPairValue pair : parts)
                builder.addEncoded(pair.getKey(), pair.getValue());
            body = builder.build();
        }/* else if (json != null)
            body = RequestBody.create(JSON, json.toString());
        else body = RequestBody.create(JSON, "{}");
*/

        Request request =
                addHeaderToRequestBuilder(
                        new Request.Builder()
                                .url(url)
                                .post(body),
                        headers
                ).build();

        return callRequest(request);

    }


    public static OKHTTPResponse postInBackground(final String url,
                                                  final JSONObject json,
                                                  final OKHTTPRunnable runnable,
                                                  final ArrayList<OKHTTPPairValue> parts,
                                                  final ArrayList<OKHTTPPairValue> headers) {
        class Operation extends AsyncTask<Void, Void, OKHTTPResponse> {

            @Override
            protected OKHTTPResponse doInBackground(Void... voids) {
                return OKHTTP.post(url, json, parts, headers);
            }

            @Override
            protected void onPostExecute(OKHTTPResponse result) {
                super.onPostExecute(result);
                startRunnable(runnable, result);
            }
        }

        TaskHelper.execute(new Operation());

        return null;

    }

    // ******************** Get ****************************************
    private static OKHTTPResponse get(
            String url,
            ArrayList<OKHTTPPairValue> headers) {

        Request request =
                addHeaderToRequestBuilder(new Request
                                .Builder()
                                .url(url)
                                .get()
                        , headers).build();

        return callRequest(request);
    }

    public static OKHTTPResponse getInBackground(final String url,
                                                 final OKHTTPRunnable runnable,
                                                 final ArrayList<OKHTTPPairValue> headers) {
        class Operation extends AsyncTask<Void, Void, OKHTTPResponse> {

            @Override
            protected OKHTTPResponse doInBackground(Void... voids) {
                return OKHTTP.get(url, headers);
            }

            @Override
            protected void onPostExecute(OKHTTPResponse result) {
                super.onPostExecute(result);
                startRunnable(runnable, result);
            }
        }

        TaskHelper.execute(new Operation());

        return null;

    }


    // ******************** Add Header Functions ****************************
    private static Request.Builder addHeaderToRequestBuilder(Request.Builder builder,
                                                             ArrayList<OKHTTPPairValue> headers) {
        if (headers != null)
            for (OKHTTPPairValue header : headers)
                builder = builder.addHeader(header.getKey(), header.getValue());

        return builder;
    }


    // ******************** Call  Functions ****************************

    private static OKHTTPResponse callRequest(Request request){
        Response response = null;
        try {
            Call call = getClient().newCall(request);
            response = call.execute();
            return new OKHTTPResponse(response.code(), response.body().string());
        } catch (IOException e) {
            try {
                return new OKHTTPResponse();
            } catch (JSONException e1) {
                e1.printStackTrace();
            }
        }
        return null;
    }


    // ******************  Helper Function *******************************

    private static void startRunnable(OKHTTPRunnable runnable, OKHTTPResponse response) {
        if (runnable != null)
            runnable.run(response);
    }


}
