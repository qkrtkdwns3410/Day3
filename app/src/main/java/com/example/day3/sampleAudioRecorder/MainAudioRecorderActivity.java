package com.example.day3.sampleAudioRecorder;

import static android.os.Environment.*;

import java.io.File;

import com.example.day3.R;
import com.pedro.library.AutoPermissions;
import com.pedro.library.AutoPermissionsListener;

import android.content.ContentValues;
import android.content.ContextWrapper;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainAudioRecorderActivity extends AppCompatActivity implements AutoPermissionsListener {
	  MediaRecorder mediaRecorder;
	  MediaPlayer mediaPlayer;
	  
	  String filename;
	  
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
			
			Button button2 = findViewById(R.id.button2);
			button2.setOnClickListener(new View.OnClickListener() {
				  @Override
				  public void onClick(View view) {
						stopRecording();
				  }
			});
			
			Button button3 = findViewById(R.id.button3);
			button3.setOnClickListener(new View.OnClickListener() {
				  @Override
				  public void onClick(View view) {
						startPlay();
				  }
			});
			
			Button button4 = findViewById(R.id.button4);
			button4.setOnClickListener(new View.OnClickListener() {
				  @Override
				  public void onClick(View view) {
						stopPlay();
				  }
			});
			
			String sdcard = Environment.getExternalStoragePublicDirectory(DIRECTORY_MUSIC).getPath();
			filename = sdcard + File.separator + "recorded.mp4";
			
			AutoPermissions.Companion.loadAllPermissions(this, 101);
	  }
	  
	  private void startPlay() {
			killMediaPlayer();
			
			try {
				  mediaPlayer = new MediaPlayer();
				  mediaPlayer.setDataSource("file://" + filename);
				  mediaPlayer.prepare();
				  mediaPlayer.start();
				  
			} catch (Exception e) {
				  e.printStackTrace();
			}
			
	  }
	  
	  private void killMediaPlayer() {
			if (mediaPlayer != null) {
				  try {
						mediaPlayer.release();
				  } catch (Exception e) {
						e.printStackTrace();
				  }
			}
			
	  }
	  
	  private void stopPlay() {
			if (mediaPlayer != null) {
				  mediaPlayer.stop();
			}
			
	  }
	  
	  private void stopRecording() {
			if (mediaRecorder == null) {
				  return;
			}
			
			mediaRecorder.stop();
			mediaRecorder.release();
			mediaRecorder = null;
			
			ContentValues values = new ContentValues();
			
			values.put(MediaStore.MediaColumns.TITLE, "Recorded");
			values.put(MediaStore.Audio.Media.ALBUM, "Audio Album");
			values.put(MediaStore.Audio.Media.ARTIST, "Mike");
			values.put(MediaStore.Audio.Media.DISPLAY_NAME, "Recorded Audio");
			values.put(MediaStore.Audio.Media.IS_RINGTONE, 1);
			values.put(MediaStore.Audio.Media.IS_MUSIC, 1);
			values.put(MediaStore.MediaColumns.DATE_ADDED, System.currentTimeMillis() / 1000);
			values.put(MediaStore.MediaColumns.MIME_TYPE, "audio/mp4");
			values.put(MediaStore.Audio.Media.DATA, filename);
			
			Uri audioUri = getContentResolver().insert(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, values);
			
			if (audioUri == null) {
				  Log.d("sampleAudioRecorder", "Audio insert failed.");
				  return;
			}
			
	  }
	  
	  private void startRecoding() {
			if (mediaRecorder == null) {
				  mediaRecorder = new MediaRecorder();
			}
			//mediaRecorder 설정
			mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
			mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
			mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
			mediaRecorder.setOutputFile(filename);
			
			//mediaRecorder 시작
			try {
				  mediaRecorder.prepare();
				  mediaRecorder.start();
			} catch (Exception e) {
				  e.printStackTrace();
			}
	  }
	  
	  @Override
	  public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
			super.onRequestPermissionsResult(requestCode, permissions, grantResults);
			
			AutoPermissions.Companion.parsePermissions(this, requestCode, permissions, this);
	  }
	  
	  @Override
	  public void onGranted(int i, String[] permissons) {
			Toast.makeText(this, "permissions onGranted : " + permissons.length, Toast.LENGTH_SHORT).show();
			
	  }
	  
	  @Override
	  public void onDenied(int i, String[] permissons) {
			Toast.makeText(this, "permissions denied : " + permissons.length, Toast.LENGTH_SHORT).show();
	  }
	  
}