package com.example.day3.graphics.custom.drawable;

import com.example.day3.R;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainCustomViewDrawableActivity extends AppCompatActivity {
	  
	  @Override
	  protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			
			CustomViewDrawable view = new CustomViewDrawable(this);
			setContentView(view);
			
	  }
}