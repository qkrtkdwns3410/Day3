package com.example.day3.SampleContacts;

import com.example.day3.R;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

/*
 *
 *
 *
 *
 * */
public class MainContactsActivity extends AppCompatActivity {
	  TextView textView;
	  
	  @Override
	  protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_main_contacts);
		 
			textView = findViewById(R.id.textView);
		 
			Button button = findViewById(R.id.button);
		button.setOnClickListener(new View.OnClickListener() {
			  @Override
			  public void onClick(View view) {
					chooseContact();
			  }
		});
	  }
	  
	  private void chooseContact() {
			Intent contactPrickerIntent = new Intent(Intent.ACTION_PICK);
	  }
}