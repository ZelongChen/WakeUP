package com.example.zelong.wakeup.Tools;

import android.content.Context;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import cz.msebera.android.httpclient.HttpEntity;

/**
 * Created by zelong on 2/27/16.
 */
public class RestClient {

    public enum Modules {
        AGENDA,
        NEWS,
        WEATHER,
        CONTROL

    }
    
    // // TODO: Add url for each module
    private static final String AGENDA_BASE_URL = "";
    private static final String NEWS_BASE_URL = "";
    private static final String WEATHER_BASE_URL = "http://api.openweathermap.org/data/2.5/weather?q=";
    private static final String CONTROL_BASE_URL = "http://10.77.5.84:8080/UpnpServer/rest/server/";

    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void get(Modules modules,String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.get(getAbsoluteUrl(modules, url), params, responseHandler);
    }

    public static void post(Modules modules, Context context, String url, HttpEntity entity, String contentType, AsyncHttpResponseHandler responseHandler) {
        client.post(context, getAbsoluteUrl(modules, url), entity, contentType, responseHandler);
    }

    private static String getAbsoluteUrl(Modules modules, String relativeUrl) {

        if (modules == Modules.AGENDA) {
            return AGENDA_BASE_URL + relativeUrl;
        } else if (modules == Modules.NEWS) {
            return  NEWS_BASE_URL + relativeUrl;
        } else if (modules == Modules.CONTROL){
            return CONTROL_BASE_URL + relativeUrl;
        } else {
            return WEATHER_BASE_URL + relativeUrl;
        }
    }
}
