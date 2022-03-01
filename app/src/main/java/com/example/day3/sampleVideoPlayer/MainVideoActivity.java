package com.example.day3.sampleVideoPlayer;

import com.example.day3.R;

import android.net.Uri;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainVideoActivity extends AppCompatActivity {
	  public static final String VIDEO_URL = "https://sites.google.com/site/ubiaccessmobile/sample_video.mp4";
	  VideoView videoView;

	  @Override
	  protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_main_video);

			videoView = findViewById(R.id.videoView);

			//VideoView 에 MediaController 설정하기
			MediaController mc = new MediaController(this);
			videoView.setMediaController(mc);

			Button button = findViewById(R.id.button);
			button.setOnClickListener(new View.OnClickListener() {
				  @Override
				  public void onClick(View view) {
						videoView.setVideoURI(Uri.parse(VIDEO_URL));
						videoView.requestFocus();
						videoView.start();

				  }
			});
	  }
}