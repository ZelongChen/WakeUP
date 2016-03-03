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

    private Switch lightSwitch;
    private Switch tvSwitch;
    private Switch speakerSwitch;
    private Switch musicSwitch;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_control, container, false);
        lightSwitch = (Switch) view.findViewById(R.id.light_switch);
        tvSwitch = (Switch) view.findViewById(R.id.tv_switch);
        speakerSwitch = (Switch) view.findViewById(R.id.speaker_switch);
        musicSwitch = (Switch) view.findViewById(R.id.music_switch);

        lightSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                controlDevice("light", isChecked);
            }
        });

        tvSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                controlDevice("TV", isChecked);
            }
        });

        speakerSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                controlViewer("viewer", isChecked);
            }
        });

        musicSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                controlMusic("player", isChecked);
            }
        });

        return  view;
    }

    private void controlDevice(String device, boolean isChecked) {
        try {
            JSONObject json = new JSONObject();
            json.put("device", device);
            if (isChecked) {
                json.put("state", "on");
            } else {
                json.put("state", "off");
            }
            StringEntity stringEntity = new StringEntity(json.toString());
            RestClient.post(RestClient.Modules.CONTROL, getActivity(), "device", stringEntity, "application/json;charset=UTF-8", new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    Toast.makeText(getActivity(), "Success", Toast.LENGTH_SHORT).show();
                }

            });
        }catch (JSONException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    private void controlMusic(String device, boolean isChecked) {
        try {
            JSONObject json = new JSONObject();
            json.put("device", device);
            if (isChecked) {
                json.put("state", "play");
            } else {
                json.put("state", "stop");
            }
            StringEntity stringEntity = new StringEntity(json.toString());
            RestClient.post(RestClient.Modules.CONTROL, getActivity(), "device", stringEntity, "application/json;charset=UTF-8", new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    Toast.makeText(getActivity(), "Success", Toast.LENGTH_SHORT).show();
                }

            });
        }catch (JSONException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    private void controlViewer(String device, boolean isChecked) {
        try {
            JSONObject json = new JSONObject();
            json.put("device", device);
            if (isChecked) {
                json.put("state", "put");
            } else {
                json.put("state", "put");
            }
            StringEntity stringEntity = new StringEntity(json.toString());
            RestClient.post(RestClient.Modules.CONTROL, getActivity(), "device", stringEntity, "application/json;charset=UTF-8", new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    Toast.makeText(getActivity(), "Success", Toast.LENGTH_SHORT).show();
                }

            });
        }catch (JSONException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

}
