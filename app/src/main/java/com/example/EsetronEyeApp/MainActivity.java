package com.example.EsetronEyeApp;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import org.videolan.libvlc.LibVLC;
import org.videolan.libvlc.Media;
import org.videolan.libvlc.MediaPlayer;
import org.videolan.libvlc.util.VLCVideoLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity  {
    private static final boolean USE_TEXTURE_VIEW = false;
    private static final boolean ENABLE_SUBTITLES = false;

    VLCVideoLayout mVideoLayout = null;
    LinearLayout linearLayout;
    LibVLC mLibVLC = null;
    MediaPlayer mMediaPlayer = null;
    ImageButton imageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ArrayList<String> args = new ArrayList<>();

        mLibVLC = new LibVLC(this, args);
//        mLibVLC2=new LibVLC(this,args);

        mMediaPlayer = new MediaPlayer(mLibVLC);

        set();

        mMediaPlayer.attachViews(mVideoLayout, null, ENABLE_SUBTITLES, USE_TEXTURE_VIEW);
//        mMediaPlayer2.attachViews(mVideoLayout, null, ENABLE_SUBTITLES, USE_TEXTURE_VIEW);
//        String url = "rtsp://admin:esetron00@192.168.1.25/cam/realmonitor?channel=1&subtype=1";
//        String url="rtsp://wowzaec2demo.streamlock.net/vod/mp4:BigBuckBunny_115k.mov";
//        String url = "rtsp://192.168.2.158:8554/";
//        String url="rtsp://freja.hiof.no:1935/rtplive/definst/hessdalen03.stream";
        String url = "udp://@5.11.133.3:5500";
//        String url2="udp://@192.168.2.55:5800";

        final Media media = new Media(mLibVLC, Uri.parse(url));

        media.addOption(":network-caching=250");

        mMediaPlayer.setMedia(media);

        media.release();

        mMediaPlayer.play();

        imageButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                android.os.Process.killProcess(android.os.Process.myPid());
            }
        });
    }
        public void set(){

            imageButton = findViewById(R.id.close);
            mVideoLayout = findViewById(R.id.video_layout);
            linearLayout=findViewById(R.id.linearlayout);

        }

}
