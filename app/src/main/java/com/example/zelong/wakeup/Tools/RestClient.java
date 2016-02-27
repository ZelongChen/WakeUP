package com.example.zelong.wakeup.Tools;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

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
    private static final String CONTROL_BASE_URL = "";

    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void get(Modules modules,String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.get(getAbsoluteUrl(modules, url), params, responseHandler);
    }

    public static void post(Modules modules, String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.post(getAbsoluteUrl(modules, url), params, responseHandler);
    }

    private static String getAbsoluteUrl(Modules modules, String relativeUrl) {
        switch (modules) {
            case AGENDA:
                return AGENDA_BASE_URL + relativeUrl;
            case NEWS:
                return NEWS_BASE_URL + relativeUrl;
            case CONTROL:
                return CONTROL_BASE_URL + relativeUrl;
            default:
                return WEATHER_BASE_URL + relativeUrl;
        }
    }
}
