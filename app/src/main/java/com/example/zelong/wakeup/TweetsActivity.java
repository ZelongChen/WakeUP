package com.example.zelong.wakeup;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.twitter.sdk.android.tweetui.SearchTimeline;
import com.twitter.sdk.android.tweetui.TweetTimelineListAdapter;
import com.twitter.sdk.android.tweetui.UserTimeline;

public class TweetsActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweets);

        Intent intent = getIntent();
        String key = intent.getStringExtra("SEARCH_KEY");

        SearchTimeline timeline = new SearchTimeline.Builder()
                .query("#" + key)
                .build();
        final TweetTimelineListAdapter adapter = new TweetTimelineListAdapter.Builder(this)
                .setTimeline(timeline)
                .build();
        setListAdapter(adapter);
    }
}
