package com.example.zelong.wakeup;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.example.zelong.wakeup.Tools.RestClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;


public class ControlFragment extends Fragment {

    public ControlFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);
    }

    private Button lightOn;
    private Button lightOff;
    private Button tvOn;
    private Button tvOff;
    private Button viewerOn;
    private Button musicOn;
    private Button musicOff;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_control, container, false);

        lightOn = (Button) view.findViewById(R.id.light_on);
        lightOff = (Button) view.findViewById(R.id.light_off);
        tvOn = (Button) view.findViewById(R.id.tv_on);
        tvOff = (Button) view.findViewById(R.id.tv_off);
        viewerOn = (Button) view.findViewById(R.id.viewer_on);
        musicOn = (Button) view.findViewById(R.id.music_on);
        musicOff = (Button) view.findViewById(R.id.music_off);

        lightOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controlDevice("light", "on");
            }
        });

        lightOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controlDevice("light", "off");
            }
        });

        tvOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controlDevice("TV", "on");
            }
        });

        tvOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controlDevice("TV", "off");
            }
        });

        viewerOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controlDevice("viewer", "put");
            }
        });

        musicOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controlDevice("player", "play");
            }
        });

        musicOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controlDevice("player", "stop");
            }
        });

        return view;
    }

    private void controlDevice(String device, String action) {
        try {
            JSONObject json = new JSONObject();
            json.put("device", device);
            json.put("state", action);

            StringEntity stringEntity = new StringEntity(json.toString());
            RestClient.post(RestClient.Modules.CONTROL, getActivity(), "device", stringEntity, "application/json;charset=UTF-8", new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    Toast.makeText(getActivity(), "Success", Toast.LENGTH_SHORT).show();
                }

            });
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

}
