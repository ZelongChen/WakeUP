package com.example.zelong.wakeup;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.zelong.wakeup.Tools.MyCtrlPoint;

import org.cybergarage.upnp.ControlPoint;


public class ControlFragment extends Fragment {

    public ControlFragment() {
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
        View view = inflater.inflate(R.layout.fragment_control, container, false);
        Button buttonOn = (Button) view.findViewById(R.id.button_light_on);
        Button buttonOff = (Button) view.findViewById(R.id.button_light_off);
        buttonOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LightOn job = new LightOn();
                job.execute();
            }
        });

        buttonOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LightOff job = new LightOff();
                job.execute();
            }
        });

        return  view;
    }

    private class LightOn extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            ControlPoint controlPoint = new MyCtrlPoint();
            controlPoint.start();
            //controlPoint.openLight();
            return null;
        }
    }

    private class LightOff extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            MyCtrlPoint controlPoint = new MyCtrlPoint();
            controlPoint.start();
            controlPoint.closeLight();
            return null;
        }
    }

}
