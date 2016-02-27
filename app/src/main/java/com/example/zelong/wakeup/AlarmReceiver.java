package com.example.zelong.wakeup;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by zelong on 2/27/16.
 */
public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub
        Toast.makeText(context, "Alarm received!", Toast.LENGTH_LONG).show();
        Intent i = new Intent();
        i.setClassName("com.example.zelong.wakeup", "com.example.zelong.wakeup.AlertActivity");
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);



    }

}
