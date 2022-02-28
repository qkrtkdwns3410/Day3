package com.example.day3.sampleCaptureIntent;

import java.io.File;

import com.example.day3.R;
import com.pedro.library.AutoPermissions;
import com.pedro.library.AutoPermissionsListener;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.core.content.FileProvider;

public class MainCaptureIntentActivity extends AppCompatActivity implements AutoPermissionsListener {
	  ImageView imageView;
	  File file;
	  
	  @Override
	  protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_main_capture_intent);
			
			imageView = findViewById(R.id.imageView);
			
			Button button = findViewById(R.id.button);
			button.setOnClickListener(new View.OnClickListener() {
				  @Override
				  public void onClick(View view) {
						takePicture();
				  }
			});
			
			AutoPermissions.Companion.loadAllPermissions(this, 101);
	  }
	  
	  @Override
	  public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
			super.onRequestPermissionsResult(requestCode, permissions, grantResults);
			
			AutoPermissions.Companion.parsePermissions(this, requestCode, permissions, this);
			
	  }
	  
	  @Override
	  public void onDenied(int i, String[] permissions) {
			Toast.makeText(this, "permissions denied : " + permissions.length, Toast.LENGTH_SHORT).show();
	  }
	  
	  @Override
	  public void onGranted(int i, String[] permissions) {
			Toast.makeText(this, "permissions granted : " + permissions.length, Toast.LENGTH_SHORT).show();
	  }
	  
	  private void takePicture() {
			if (file == null) {
				  file = createFile();
			}
			
			Uri fileUri = FileProvider.getUriForFile(this, "com.example.day3.sampleCaptureIntent.fileprovider", file);
			Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
			intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
			if (intent.resolveActivity(getPackageManager()) != null) {
				  startActivityForResult(intent, 101);
			}
	  }
	  
	  private File createFile() {
			String fileName = "capture.jpg";
			File storageDir = Environment.getExternalStorageDirectory();
			File outFile = new File(storageDir, fileName);
			
			return outFile;
	  }
	  
	  @Override
	  protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
			super.onActivityResult(requestCode, resultCode, data);
			
			if (requestCode == 101 && requestCode == RESULT_OK) {
				  //이미지 파일을 BitMap 객체로 만들기
				  BitmapFactory.Options options = new BitmapFactory.Options();
				  options.inSampleSize = 8;
				  Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath(), options);
				  
				  //이미지뷰에 Bitmap 설정하기
				  imageView.setImageBitmap(bitmap);
				  
			}
	  }
}


























