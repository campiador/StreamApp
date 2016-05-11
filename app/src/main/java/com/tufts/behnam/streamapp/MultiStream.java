package com.tufts.behnam.streamapp;

import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.VideoView;


public class MultiStream extends AppCompatActivity {

    private VideoView video1;
    private VideoView video2;

    private final static String SAMPLE_HLS_URL =
            "http://qthttp.apple.com.edgesuite.net/1010qwoeiuryfg/sl.m3u8";
    private final static String SAMPLE_RTSP_URL =
            "rtsp://184.72.239.149/vod/mp4:BigBuckBunny_115k.mov";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_stream);
        createVideoStream();
//        createVideoStream();
//        createVideoStream();
//
//        video1=(VideoView)findcViewById(R.id.videoView2);
//        video1.setVideoURI(Uri.parse(SAMPLE_RTSP_URL));
////        video1.setMediaController(new MediaController(this));
////        video1.requestFocus();
//        video1.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//            @Override
//            public void onPrepared(MediaPlayer mp) {
//                mp.start();
//            }
//        });
//
//        video2=(VideoView)findViewById(R.id.videoView3);
//        video2.setVideoURI(Uri.parse(SAMPLE_HLS_URL));
//        video2.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//            @Override
//            public void onPrepared(MediaPlayer mp) {
//                mp.start();
//            }
//        });
////        video2.setMediaController(new MediaController(this));
////        video2.requestFocus();
//
//        Thread view1=new Thread(new Runnable() {
//            @Override
//            public void run() {
//                android.os.Process.setThreadPriority(android.os.Process.THREAD_PRIORITY_DISPLAY);
//                video1.start();
//            }
//        });
//
//        Thread view2=new Thread(new Runnable() {
//            @Override
//            public void run() {
//                android.os.Process.setThreadPriority(android.os.Process.THREAD_PRIORITY_DISPLAY);
//                video2.start();
//            }
//        });

    }

    private int mVideoCount = 0;

    private void createVideoStream() {
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linear_frame);
        mVideoCount++;
        VideoView videoView = new VideoView(this);
        linearLayout.addView(videoView);
        videoView.setVideoURI(Uri.parse((SAMPLE_HLS_URL)));
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.start();
            }
        });


    }
}
