package com.example.studyforce;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.widget.ImageView;

import androidx.annotation.Nullable;

public class m_service extends Service{
    MediaPlayer mediaPlayer;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    //처음시작될때 초기화
    @Override
    public void onCreate() {
        super.onCreate();

        mediaPlayer=MediaPlayer.create(this,R.raw.rain);
        mediaPlayer.setLooping(true);
        super.onCreate();
    }

    //시작
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
            mediaPlayer.start();
            return super.onStartCommand(intent, flags, startId);
    }

    //끝날때
    @Override
    public void onDestroy() {
        super.onDestroy();
        mediaPlayer.stop();
    }
}
