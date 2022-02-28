package com.example.day3.sampleMulitiTouch;

import com.example.day3.R;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainSampleMultitouchActivity extends AppCompatActivity {
	  
	  @Override
	  protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_main_sample_multitouch);
			
			LinearLayout container = findViewById(R.id.container);
			Resources res = getResources();
			Bitmap bitmap = BitmapFactory.decodeResource(res, R.drawable.face);
			
			//객체의 생성
			ImageDisplayView view = new ImageDisplayView(this);
			view.setImageData(bitmap);
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.MATCH_PARENT
			);
			
			//XML 레이아웃에 들어 있는 LinearLayout안에 ImageDisplayView 객체 추가하기
			container.addView(view, params);
	  }
	  
}