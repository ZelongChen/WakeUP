package com.example.zelong.wakeup.Tools;

import android.content.Context;
import android.util.Log;

import com.example.zelong.wakeup.R;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import cz.msebera.android.httpclient.Header;

/**
 * Created by zelong on 26/02/16.
 */
public class RemoteFetch {

    private static JSONObject data;
    public static JSONObject getJSON(Context context, String city) {

            String relativeURL = city + "&appid=" + context.getString(R.string.open_weather_maps_app_id);

            RestClient.get(RestClient.Modules.WEATHER, relativeURL, null, new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    data = response;
                }
            });
        return data;
    }
}
