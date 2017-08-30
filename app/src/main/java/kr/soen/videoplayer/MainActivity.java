package kr.soen.videoplayer;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener{
    public final static String VIDEO_URL = "rtsp://ebsonair.ebs.co.kr/groundwavetablet500k/tablet500k";
    VideoView videoView;
    private ImageView play,stop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        videoView = (VideoView) findViewById(R.id.view);
        play=(ImageView)findViewById(R.id.img_play);
        stop=(ImageView)findViewById(R.id.img_stop);

        play.setOnClickListener(this);
        stop.setOnClickListener(this);

        MediaController controller = new MediaController(MainActivity.this);
        videoView.setMediaController(controller);

        videoView.requestFocus();
        videoView.setVideoURI(Uri.parse(VIDEO_URL));


        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {Toast.makeText(MainActivity.this,"동영상 준비완료", Toast.LENGTH_SHORT).show();
            }
        });

        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {Toast.makeText(MainActivity.this,"동영상 재생완료", Toast.LENGTH_SHORT).show();
            }
        });

    }
    @Override
    public void onClick(View v){
        switch (v.getId()) {
            case R.id.img_play:
                videoView.seekTo(0);
                videoView.start();
                break;
            case R.id.img_stop:
                videoView.pause();
                break;
        }
    }
}