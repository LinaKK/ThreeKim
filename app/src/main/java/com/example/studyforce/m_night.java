package com.example.studyforce;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.widget.ImageView;

import androidx.annotation.Nullable;

public class m_night extends Service{
    MediaPlayer mp;
    @Nullable
    @Override
    public IBinder onBind(Intent intent){
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mp =mp.create(this, R.raw.night_bug);
        mp.setLooping(true);
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        mp.start();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        mp.stop();
    }
}
