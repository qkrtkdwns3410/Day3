package com.example.day3.graphics.custom;

import com.example.day3.R;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainGraphicsActivity extends AppCompatActivity {
	  
	  @Override
	  protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
		 
			CustomView view = new CustomView(this);
		 
			setContentView(view);
			
			
	  }
}