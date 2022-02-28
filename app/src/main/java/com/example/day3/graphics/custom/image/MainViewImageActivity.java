package com.example.day3.graphics.custom.image;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainViewImageActivity extends AppCompatActivity {
	  
	  @Override
	  protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			
			CustomViewImage view = new CustomViewImage(this);
			setContentView(view);
			
	  }
}