package com.example.day3.sampleAudioPlayer;

import java.net.URL;

import com.example.day3.R;

import android.media.MediaPlayer;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainAudioPlayerActivity extends AppCompatActivity {
	  public static final String AUDIO_URL = "https://sites.google.com/site/ubiaccessmobile/sample_audio.mp3";
	  MediaPlayer mediaPlayer;
	  int position = 0;
	  
	  @Override
	  protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_main_audio_player);
			
			Button button = findViewById(R.id.button);
			button.setOnClickListener(new View.OnClickListener() {
				  @Override
				  public void onClick(View view) {
						playAudio(AUDIO_URL);
						Toast.makeText(getApplicationContext(), "음악 파일 재생 시작됨.", Toast.LENGTH_SHORT).show();
						
				  }
			});
			
			Button button2 = findViewById(R.id.button2);
			button2.setOnClickListener(new View.OnClickListener() {
				  @Override
				  public void onClick(View view) {
						if (mediaPlayer != null) {
							  mediaPlayer.stop();
							  Toast.makeText(getApplicationContext(), "음악 파일 재생 중지됨.", Toast.LENGTH_SHORT).show();
						}
				  }
			});
			
			Button button3 = findViewById(R.id.button3);
			button3.setOnClickListener(new View.OnClickListener() {
				  @Override
				  public void onClick(View view) {
						position = mediaPlayer.getCurrentPosition();
						mediaPlayer.pause();
						Toast.makeText(getApplicationContext(), "음악 파일 재생 일시정지됨.", Toast.LENGTH_SHORT).show();
				  }
			});
			
			Button button4 = findViewById(R.id.button4);
			button4.setOnClickListener(new View.OnClickListener() {
				  @Override
				  public void onClick(View view) {
						if (mediaPlayer != null && !mediaPlayer.isPlaying()) {
							  mediaPlayer.start();
							  mediaPlayer.seekTo(position);
							  Toast.makeText(getApplicationContext(), "음악 파일 재생 재시작됨.", Toast.LENGTH_SHORT).show();
						}
				  }
			});
	  }
	  
	  private void playAudio(String audioUrl) {
			killMediaPlayer();
			try {
				  mediaPlayer = new MediaPlayer();
				  mediaPlayer.setDataSource(audioUrl);
				  mediaPlayer.prepare();
				  mediaPlayer.start();
			} catch (Exception e) {
				  e.printStackTrace();
			}
	  }
	  
	  @Override
	  protected void onDestroy() {
			super.onDestroy();
			killMediaPlayer();
	  }
	  
	  private void killMediaPlayer() {
			if (mediaPlayer != null) {
				  try {
						//리소스 해제하기
						mediaPlayer.release();
						
				  } catch (Exception e) {
						e.printStackTrace();
				  }
			}
	  }
	  
}




























