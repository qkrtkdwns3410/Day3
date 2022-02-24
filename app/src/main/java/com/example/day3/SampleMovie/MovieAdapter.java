package com.example.day3.SampleMovie;

import java.util.ArrayList;
import java.util.Calendar;

import com.example.day3.R;

import android.speech.tts.TextToSpeech;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 *packageName    : com.example.day3.SampleMovie
 * fileName       : MovieAdapter
 * author         : ipeac
 * date           : 2022-02-24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-02-24        ipeac       최초 생성
 */
public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder>{
	  ArrayList<Movie> items = new ArrayList<>();
	  
	  @NonNull
	  @Override
	  public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
			return null;
	  }
	  
	  @Override
	  public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
			
	  }
	  
	  @Override
	  public int getItemCount() {
			return 0;
	  }
	  
	  static class ViewHolder extends RecyclerView.ViewHolder {
			TextView textView;
			TextView textView2;
			
			public ViewHolder(@NonNull View itemView) {
				  super(itemView);
				  
				  textView = itemView.findViewById(R.id.textView);
				  textView2 = itemView.findViewById(R.id.textView2);
				  
			}
		 
			public void setItem(Movie item) {
				  textView.setText(item.movieNm);
				  textView2.setText(item.audiCnt + " 명");
			}
	  }
}




























