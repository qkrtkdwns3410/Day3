package com.example.day3.SampleAlbum;

import java.io.InputStream;

import com.example.day3.R;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainAlbumActivity extends AppCompatActivity {
	  ImageView imageView;
	  
	  @Override
	  protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_main_album);
		 
			imageView = findViewById(R.id.imageView);
		 
			Button button = findViewById(R.id.button);
			button.setOnClickListener(new View.OnClickListener() {
				  @Override
				  public void onClick(View view) {
						openGallery();
				  }
			});
	  }
	  
	  private void openGallery() {
			Intent intent = new Intent();
			intent.setType("image/*");
			intent.setAction(Intent.ACTION_GET_CONTENT);
		 
			startActivityForResult(intent, 101);
	  }
	  
	  //
	  @Override
	  protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
			super.onActivityResult(requestCode, resultCode, data);
		  
			if (requestCode == 101) {
				  if (resultCode == RESULT_OK) {
						Uri fileUri = data.getData();
					  
						//ContentResolver 객체 참조하기
						ContentResolver resolver = getContentResolver();
					 
						try {
							  
							  //ContentResolver 객체의 openInputStream() 메서더로 파일을 읽어들이기
							  InputStream inputStream = resolver.openInputStream(fileUri);
							  Bitmap imgBitmap = BitmapFactory.decodeStream(inputStream);
							  
							  imageView.setImageBitmap(imgBitmap);
							  
							  inputStream.close();
							  
						} catch (Exception e) {
							  e.printStackTrace();
						}
						
				  }
			}
	  }
}




























