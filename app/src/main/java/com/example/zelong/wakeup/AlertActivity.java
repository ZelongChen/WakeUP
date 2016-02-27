package com.example.zelong.wakeup;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class AlertActivity extends AppCompatActivity {

    private MediaPlayer alarmMusic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            Uri alert = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
            alarmMusic = new MediaPlayer();
            alarmMusic.setDataSource(this, alert);
            final AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
            if (audioManager.getStreamVolume(AudioManager.STREAM_RING) != 0) {
                alarmMusic.setAudioStreamType(AudioManager.STREAM_RING);
                alarmMusic.setLooping(true);
                alarmMusic.prepare();
                alarmMusic.start();
            }
        } catch (Exception e) {

        }

        new AlertDialog.Builder(AlertActivity.this).setTitle("Alarm")
                .setMessage("Wake up! Wake up!")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        alarmMusic.stop();

                        AlertActivity.this.finish();
                        Intent intent = new Intent(AlertActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                }).show();
    }
}
