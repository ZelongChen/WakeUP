package com.example.zelong.wakeup.Tools;

import android.app.Activity;
import android.content.SharedPreferences;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by zelong on 2/28/16.
 */
public class HashTagPreference {

    SharedPreferences prefs;
    private Activity activity;
    Set<String> defaultHashTags = new HashSet<>();
    public HashTagPreference(Activity activity){
        this.activity = activity;
        prefs = activity.getPreferences(Activity.MODE_PRIVATE);
        defaultHashTags.add(new CityPreference(activity).getCity());
    }

    // If the user has not chosen a city yet, return
    // Sydney as the default city
    public Set<String> getHashTags(){
        return prefs.getStringSet("hashTags", defaultHashTags);
    }

    public void setHashTags(Set<String> hashTags){
        prefs.edit().putStringSet("hashTags", hashTags).commit();
    }

    public void addHashTag(String hashtag) {
        Set<String> hashtags = new HashSet<String>(getHashTags());
        hashtags.add(hashtag);
        setHashTags(hashtags);
    }
}
