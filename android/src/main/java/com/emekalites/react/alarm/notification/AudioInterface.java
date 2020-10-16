package com.emekalites.react.alarm.notification;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.provider.Settings;
import android.util.Log;
import android.os.Environment;
import android.media.AudioManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class AudioInterface {
    private static final String TAG = AudioInterface.class.getSimpleName();

    private static MediaPlayer player;
    private static AudioInterface ourInstance = new AudioInterface();
    private Context mContext;
    private Uri uri;

    private AudioInterface() {
    }

    private static Context get() {
        return getInstance().getContext();
    }

    static synchronized AudioInterface getInstance() {
        return ourInstance;
    }

    void init(Context context) {
        uri = Settings.System.DEFAULT_ALARM_ALERT_URI;

        if (mContext == null) {
            this.mContext = context;
        }
    }

    private Context getContext() {
        return mContext;
    }

     MediaPlayer getSingletonMedia(String soundName, String soundNames) {
        try {
            this.uri = Uri.parse("file://" + soundName);
            player = new MediaPlayer();
            player.setAudioStreamType(AudioManager.STREAM_MUSIC);
            player.setDataSource(getContext(), this.uri);
        } catch (Exception e) {
            Log.e(TAG, "There was an error");
        }

        return player;
    }

    void stopPlayer() {
        try {
            player.stop();
            player.reset();
            player.release();

            player = null;
        } catch (Exception e) {
            Log.e(TAG, "player not found");
        }
    }
}
