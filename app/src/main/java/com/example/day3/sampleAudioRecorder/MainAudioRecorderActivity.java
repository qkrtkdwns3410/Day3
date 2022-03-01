package com.example.day3.sampleAudioRecorder;

import com.example.day3.R;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainAudioRecorderActivity extends AppCompatActivity {
	  MediaRecorder mediaRecorder;
	  MediaPlayer mediaPlayer;

	  @Override
	  protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_main_audio_recorder);

			Button button = findViewById(R.id.button);
			button.setOnClickListener(new View.OnClickListener() {
				  @Override
				  public void onClick(View view) {
						startRecoding();
				  }
			});
	  }

	  private void startRecoding() {
			
	  }
}