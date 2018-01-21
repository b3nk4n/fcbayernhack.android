package fcb.de.fcbayernemotions;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.VideoView;

import java.util.ArrayList;
import java.util.List;

public class TopEmotionsActivity extends AppCompatActivity {

    private VideoView videoView;

    private int playingIndex = 0;
    private List<Uri> videoUriList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_emotions);
        setTitle("Top Fan Emotions");

        videoView = findViewById(R.id.videoView);
        videoUriList.add(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.fan01));
        videoUriList.add(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.fan02));
        videoUriList.add(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.fan03));
        videoUriList.add(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.fan04));
        videoUriList.add(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.fan05));

        videoView.setDrawingCacheEnabled(true);
        videoView.setVideoURI(videoUriList.get(playingIndex));
        videoView.requestFocus();
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setVolume(0, 0);
            }
        });
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                playingIndex++;

                if (playingIndex == videoUriList.size()) {
                    // we are done, navigate to the reaction list.
                    Intent intent = new Intent(TopEmotionsActivity.this,
                            EmotionListActivity.class);
                    startActivity(intent);
                } else {
                    // show the next video
                    videoView.stopPlayback();
                    videoView.setDrawingCacheEnabled(true);
                    videoView.setVideoURI(videoUriList.get(playingIndex));
                    videoView.start();
                }

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        videoView.start();
    }
}
