package com.example.day3.SampleMovie;

import java.util.HashMap;
import java.util.Map;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.day3.R;
import com.google.gson.Gson;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainMovieActivity extends AppCompatActivity {
	  EditText editText;
	  TextView textView;
	  
	  RecyclerView recyclerView;
	  MovieAdapter movieAdapter;
	  
	  static RequestQueue requestQueue;
	  
	  @Override
	  protected void onCreate(@Nullable Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_main_movie);
			
			editText = findViewById(R.id.editText);
			textView = findViewById(R.id.textView);

		 
			Button button = findViewById(R.id.button);
			button.setOnClickListener(new View.OnClickListener() {
				  @Override
				  public void onClick(View view) {
						makeRequest();
				  }
			});
			
			if (requestQueue == null) {
				  requestQueue = Volley.newRequestQueue(getApplicationContext());
			}
			
			//XML 레이아웃에 정의하 리싸이클러뷰 객체 참조
			recyclerView = findViewById(R.id.recyclerView);
			
			LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
			recyclerView.setLayoutManager(layoutManager);
			
			movieAdapter = new MovieAdapter();
			//리싸이클러뷰에 어댑터 설정
			recyclerView.setAdapter(movieAdapter);
			
	  }
	  
	  private void makeRequest() {
			String url = editText.getText().toString();
		 
			StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
				  @Override
				  public void onResponse(String response) {
						println("응답 >> " + response);
						
						processResponse(response);
				  }
			},
				new Response.ErrorListener() {
					  @Override
					  public void onErrorResponse(VolleyError error) {
							println("에러 >> " + error.getMessage());
					  }
				}
			) {
				  @Nullable
				  @Override
				  protected Map<String, String> getParams() throws AuthFailureError {
					 
						Map<String, String> params = new HashMap<>();
					 
						return params;
				  }
			};
			
			request.setShouldCache(false);
			requestQueue.add(request);
			println("요청 보냄.");
			
	  }
	  
	  public void println(String data) {
			Log.d("MainActivity", data);
	  }
	  
	  public void processResponse(String response) {
			Gson gson = new Gson();
			MovieList movieList = gson.fromJson(response, MovieList.class);
		 
			println("영화 정보 수 : " + movieList.boxOfficeResult.dailyBoxOfficeList.size());
		 
			for (int i = 0; i < movieList.boxOfficeResult.dailyBoxOfficeList.size(); i++) {
				  Movie movie = movieList.boxOfficeResult.dailyBoxOfficeList.get(i);
				  
				  movieAdapter.addItem(movie);
				  
			}
			
			movieAdapter.notifyDataSetChanged();
			
	  }
	  
}








































