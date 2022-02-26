package com.example.day3.SampleProvider;

import java.net.URI;

import com.example.day3.R;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
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
 *
 */
public class MainProviderActivity extends AppCompatActivity {
	  TextView textView;
	  
	  @Override
	  protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_main_provider);
		 
			textView = findViewById(R.id.textView);
		 
			Button button = findViewById(R.id.button);
			button.setOnClickListener(new View.OnClickListener() {
				  @Override
				  public void onClick(View view) {
						insertPerson( );
				  }
			});
			
			Button button2 = findViewById(R.id.button2);
			button2.setOnClickListener(new View.OnClickListener() {
				  @Override
				  public void onClick(View view) {
						queryPerson();
				  }
			});
			
			Button button3 = findViewById(R.id.button3);
			button3.setOnClickListener(new View.OnClickListener() {
				  @Override
				  public void onClick(View view) {
						updatePerson();
				  }
			});
			
			Button button4 = findViewById(R.id.button4);
			button4.setOnClickListener(new View.OnClickListener() {
				  @Override
				  public void onClick(View view) {
						deletePerson();
				  }
			});
	  }
	  
	  private void updatePerson() {
			String uriString = "content://com.example.day3/person";
			Uri uri = new Uri.Builder().build().parse(uriString);
		 
			String selection = "mobile = ?"; //where
			String[] selectionArgs = new String[] {"010-1000-1000"}; // where안의 조건
			//수정할 값입니다.
			ContentValues updateValue = new ContentValues();
			updateValue.put("mobile", "010-2000-2000");
			
			int count = getContentResolver().update(uri, updateValue, selection, selectionArgs);
			
			println("update 결과: " + count);
	  }
	  
	  private void deletePerson() {
			String uriString = "content://com.example.day3/person";
			Uri uri = new Uri.Builder().build().parse(uriString);
		 
			String selection = "name = ?";
			String[] selectionArgs = new String[] {"john"};
		 
			int count = getContentResolver().delete(uri, selection, selectionArgs);
			println("delete 결과 : " + count);
	  }
	  
	
	  
	  private void queryPerson() {
			println("queryPerson 호출됨");
			try {
				  String uriString = "content://com.example.day3/person";
				  Uri uri = new Uri.Builder().build().parse(uriString);
				  
				  String[] columns = new String[] {"name", "age", "mobile"};
				  Cursor cursor = getContentResolver().query(uri, columns, null, null, "name  ASC");
				  println("query 결과  ; " + cursor.getCount());
				  
				  int index = 0;
				  while (cursor.moveToNext()) {
						String name = cursor.getString(cursor.getColumnIndex(columns[0]));
						int age = cursor.getInt(cursor.getColumnIndex(columns[1]));
						String mobile = cursor.getString(cursor.getColumnIndex(columns[2]));
					 
						println("#" + index + " -> " + name + ", " + age + ", " + mobile);
						index += 1;
				  }
			} catch (Exception e) {
				  e.printStackTrace();
				  
			}
	  }
	  
	  private void insertPerson() {
			println("insertPerson 호출됨");
		 
			String uriString = "content://com.example.day3/person";
			Uri uri = new Uri.Builder().build().parse(uriString);
		 
			Cursor cursor = getContentResolver().query(uri, null, null, null, null);
			String[] columns = cursor.getColumnNames();
			println("columns count >> " + columns.length);
		 
			for (int i = 0; i < columns.length; i += 1) {
				  println("#" + i + " : " + columns[i]);
			}
		 
			ContentValues values = new ContentValues();
			values.put("name", "john");
			values.put("age", 20);
			values.put("mobile", "010-1000-1000");
		 
			uri = getContentResolver().insert(uri, values);
			println("insert 결과 >> " + uri.toString());
	  }
	  
	  public void println(String data) {
	  	  textView.append(data+"\n");
	  }
}





























