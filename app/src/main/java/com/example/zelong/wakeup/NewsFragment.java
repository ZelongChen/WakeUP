package com.example.zelong.wakeup;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.example.zelong.wakeup.Tools.HashTagPreference;
import com.twitter.sdk.android.tweetui.SearchTimeline;
import com.twitter.sdk.android.tweetui.TweetTimelineListAdapter;

import java.util.Set;


public class NewsFragment extends Fragment {


    private ListView listView;
    private ArrayAdapter<String> adapter;
    private String[] tags;
    private HashTagPreference preference;

    public NewsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);
        preference = new HashTagPreference(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        listView = (ListView) view.findViewById(R.id.hashtag_listview);
        updateTags();
        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, tags);
        listView.setAdapter(adapter);

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showInputDialog();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String key = tags[position];
                Intent intent = new Intent(getActivity(), TweetsActivity.class);
                intent.putExtra("SEARCH_KEY", key);
                startActivity(intent);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                showDeleteDialog(tags[position]);
                return true;
            }
        });


        return view;
    }

    private void showDeleteDialog(final String hashtag) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Do you want to delete it?");
        builder.setMessage(hashtag);
        builder.setNegativeButton("Cancel", null);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                preference.removeHashTag(hashtag);
                updateTags();
                adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, tags);
                listView.setAdapter(adapter);
            }
        });
        builder.show();

    }
    private void showInputDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Add new hashtag");
        final EditText input = new EditText(getActivity());
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);
        builder.setPositiveButton("Go", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                preference.addHashTag(input.getText().toString());
                updateTags();
                adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, tags);
                listView.setAdapter(adapter);
            }
        });
        builder.show();
    }

    private void updateTags() {
        Set<String> hashtags = preference.getHashTags();
        tags = hashtags.toArray(new String[hashtags.size()]);
    }

}
