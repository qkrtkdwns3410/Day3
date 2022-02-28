package com.example.day3.SampleContacts;

import com.example.day3.R;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.text.method.NumberKeyListener;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.Nullable;
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
	  	  //연락처 화면을 띄우기 위한 인텐트 만들기
			Intent contactPrickerIntent = new Intent(Intent.ACTION_PICK,
				ContactsContract.Contacts.CONTENT_URI);
		  
			startActivityForResult(contactPrickerIntent, 101);
			
	  }
	  
	  @Override
	  protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
			super.onActivityResult(requestCode, resultCode, data);
		 
			if (resultCode == RESULT_OK) {
				  if (requestCode == 101) {
						try {
							  Uri contactsUri = data.getData();
							  //선택한 연락처의 id 값 확인하기
							  String id = contactsUri.getLastPathSegment();
							  
							  getContacts(id);
						} catch (Exception e) {
							  e.printStackTrace();
						}
				  }
			}
	  }
	  
	  private void getContacts(String id) {
			Cursor cursor = null;
			String name = "";
		 
			try {
				  //1 ; ContactsContract.Data.CONTENT_URI : 연락처의 상세 정보의 조회 3; 테이블에서 ID 에 해당하는 칼럼중에 .... =? 값을 들고옵니다. 들고오는 값은 ... {   id  } 값입니다.
				  cursor = getContentResolver().query(ContactsContract.Data.CONTENT_URI, null, ContactsContract.Data.CONTACT_ID + "=?", new String[] {id},
					  null);
				  if (cursor.moveToFirst()) {
						name = cursor.getString(cursor.getColumnIndex(ContactsContract.Data.DISPLAY_NAME));
						println("name : " + name);
					 
						String columns[] = cursor.getColumnNames();
						for (String column : columns) {
							  int index = cursor.getColumnIndex(column);
							  String columnOutput = ("#" + index + " -> ]" + column + "]" + cursor.getString(index));
							  println(columnOutput);
							  
						}
						cursor.close();
				  }
				  
			} catch (Exception e) {
				  e.printStackTrace();
			}
	  }
	  
	  public void println(String data) {
			textView.append(data + "\n");
	  }
}








































