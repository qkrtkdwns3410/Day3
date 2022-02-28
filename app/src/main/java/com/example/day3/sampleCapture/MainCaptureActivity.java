package com.example.day3.sampleCapture;

import java.util.prefs.PreferencesFactory;

import javax.net.ssl.HandshakeCompletedEvent;

import com.example.day3.R;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ThemedSpinnerAdapter;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainCaptureActivity extends AppCompatActivity {
	  CameraSurfaceView cameraView;
	  
	  @Override
	  protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_main_capture);
			
			FrameLayout previewFrame = findViewById(R.id.previewFrame);
			
			cameraView = new CameraSurfaceView();
			previewFrame.addView(cameraView);
			
			Button button = findViewById(R.id.button);
			button.setOnClickListener(new View.OnClickListener() {
				  @Override
				  public void onClick(View view) {
						takePicture();
				  }
			});
	  }
	  
	  private void takePicture() {
			cameraView.capture(new Camera.PictureCallback() {
				  @Override
				  public void onPictureTaken(byte[] data, Camera camera) {
						try {
							  //전달받은 바이트 배열을 Bitmap 객체로 만들기
							  Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
							  String outUriStr = MediaStore.Images.Media.insertImage(getContentResolver(), bitmap, "Captured Image",
								  "Captured Image using Camera.");
							  
							  if (outUriStr == null) {
									Log.d("SampleCapture", "Image insert failed");
									return;
							  } else {
									Uri outUri = Uri.parse(outUriStr);
									sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, outUri));
							  }
							  camera.startPreview();
							  
						} catch (Exception e) {
							  e.printStackTrace();
						}
						
				  }
				  
			});
	  }
	  
	  private class CameraSurfaceView extends SurfaceView implements SurfaceHolder.Callback {
			private SurfaceHolder mHolder;
			private Camera camera = null;
			
			public CameraSurfaceView(Context context) {
				  super(context);
				  
				  mHolder = getHolder();
				  mHolder.addCallback(this);
			}
			
			public boolean capture(Camera.PictureCallback handler) {
				  if (camera != null) {
						camera.takePicture(null, null, handler);
						return true;
				  } else {
						return false;
				  }
			}
	  }
}



























