package com.example.zelong.wakeup;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zelong.wakeup.Tools.RestClient;


public class NewsFragment extends Fragment {

    final static String CONSUMER_KEY = "JUwUAjWimjkSQGUHbcf8hKMvz";
    final static String CONSUMER_SECRET = "PSO4jVUCbCCnXVktRFKBjUzSEuMZW8QGmfMha8yKKwgcvHp4lw";
    final static String TwitterTokenURL = "https://api.twitter.com/oauth2/token";
    final static String TwitterStreamURL = "https://api.twitter.com/1.1/statuses/user_timeline.json?screen_name=";

    public NewsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news, container, false);
    }
    
}
