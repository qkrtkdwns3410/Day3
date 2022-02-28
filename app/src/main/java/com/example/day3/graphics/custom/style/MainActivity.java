package com.example.day3.graphics.custom.style;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 *packageName    : com.example.day3.graphics.custom.style
 * fileName       : MainActivity
 * author         : letscombine
 * date           : 2022-02-28
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-02-28        letscombine       최초 생성
 */
public class MainActivity extends AppCompatActivity {
      @Override
      protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            CustomViewStyle view = new CustomViewStyle(this);
            setContentView(view);
      }
}

























