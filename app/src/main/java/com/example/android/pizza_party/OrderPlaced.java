package com.example.android.pizza_party;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

public class OrderPlaced extends AppCompatActivity {
VideoView videoView ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_placed);
        videoView = findViewById(R.id.OP_videoView);
        String path = "android.resource://com.example.android.pizza_party/"+R.raw.orderplaced;
        Uri u = Uri.parse(path);
        videoView.setVideoURI(u);
        videoView.start();
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp)
            {
                mp.setLooping(true);
            }
        });
    }

    @Override
    protected void onDestroy() {
        videoView.stopPlayback();
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        videoView.resume();
        super.onResume();
    }

    @Override
    protected void onPause() {
        videoView.pause();
        super.onPause();
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(OrderPlaced.this,second_Page.class));
        super.onBackPressed();
    }
}