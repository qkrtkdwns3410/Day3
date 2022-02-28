package com.example.day3.sampleCapture;

import java.util.prefs.PreferencesFactory;

import javax.net.ssl.HandshakeCompletedEvent;

import com.example.day3.R;
import com.pedro.library.AutoPermissions;
import com.pedro.library.AutoPermissionsListener;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ThemedSpinnerAdapter;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainCaptureActivity extends AppCompatActivity implements AutoPermissionsListener {
	  CameraSurfaceView cameraView;
	  
	  @Override
	  protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_main_capture);
			
			FrameLayout previewFrame = findViewById(R.id.previewFrame);
			
			cameraView = new CameraSurfaceView(this);
			previewFrame.addView(cameraView);
			
			Button button = findViewById(R.id.button);
			button.setOnClickListener(new View.OnClickListener() {
				  @Override
				  public void onClick(View view) {
						takePicture();
				  }
			});
			AutoPermissions.Companion.loadAllPermissions(this, 101);
	  }
	  
	  private void takePicture() {
			cameraView.capture(new Camera.PictureCallback() {
				  @Override
				  public void onPictureTaken(byte[] data, Camera camera) {
						try {
							  //전달받은 바이트 배열을 Bitmap 객체로 만들기s
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
	  
	  @Override
	  public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
			super.onRequestPermissionsResult(requestCode, permissions, grantResults);
			AutoPermissions.Companion.parsePermissions(this, requestCode, permissions, this);
	  }
	  
	  @Override
	  public void onDenied(int i, String[] permissions) {
			Toast.makeText(this, "permission denied : " + permissions.length, Toast.LENGTH_SHORT).show();
	  }
	  
	  @Override
	  public void onGranted(int i, String[] permissions) {
			Toast.makeText(this, "permission granted : " + permissions.length, Toast.LENGTH_SHORT).show();
	  }
	  
	  private class CameraSurfaceView extends SurfaceView implements SurfaceHolder.Callback {
			//SurfaceView클래스를 상속하고 // Callback 인터페이스를 구현하는 새로운 CameraSurface - View 클래스를 정의
			private SurfaceHolder mHolder;
			private Camera camera = null;
			
			//생성자에서 서피스 홀더 객체 참조 후 설정
			public CameraSurfaceView(Context context) {
				  super(context);
				  
				  mHolder = getHolder();
				  mHolder.addCallback(this);
			}
			
			//서피스뷰가 만들어질 때 카메라 객체를 참조한 후 미리보기 화면으로 홀더 객체를 설정
			@Override
			public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
				  //Cameara.open : 이용해서 카메라 오픈!
				  camera = Camera.open();
				  setCameraOrientation();
				  try {
						//이를 통해 참조한 카메라 객체에 서피스 홀더 객체 지정..
						camera.setPreviewDisplay(mHolder);
				  } catch (Exception e) {
						e.printStackTrace();
				  }
			}
			
			@Override
			public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {
				  //서피스뷰의 크기가 변경되거나 할 때 호출... > startPreview : 미리보기 시작.
				  camera.startPreview();
			}
			
			@Override
			public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {
				  camera.stopPreview();
				  camera.release();
				  camera = null;
			}
			
			public boolean capture(Camera.PictureCallback handler) {
				  if (camera != null) {
						camera.takePicture(null, null, handler);
						return true;
				  } else {
						return false;
				  }
			}
			
			private void setCameraOrientation() {
				  if (camera == null) {
						return;
				  }
				  Camera.CameraInfo info = new Camera.CameraInfo();
				  Camera.getCameraInfo(0, info);
				  
				  WindowManager manager = (WindowManager)getSystemService(Context.WINDOW_SERVICE);
				  //회전 정보 확인
				  int rotation = manager.getDefaultDisplay().getRotation();
				  
				  int degrees = 0;
				  switch (rotation) {
						case Surface
							.ROTATION_0:
							  degrees = 0;
							  break;
						case Surface.ROTATION_90:
							  degrees = 90;
							  break;
						case Surface.ROTATION_180:
							  degrees = 180;
							  break;
						case Surface.ROTATION_270:
							  degrees = 270;
							  break;
						
				  }
				  int result = 0;
				  
				  if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
						result = (info.orientation + degrees) % 360;
						result = (360 - result) % 360;
				  } else {
						result = (info.orientation - degrees + 360) % 360;
				  }
				  
				  camera.setDisplayOrientation(result);
			}
	  }
	  
}



























