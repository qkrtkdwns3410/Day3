package com.example.day3.graphics.custom.drawable;

import com.example.day3.R;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Shader;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.util.AttributeSet;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import androidx.annotation.Nullable;

/**
 *packageName    : com.example.day3.graphics.custom.drawable
 * fileName       : CustomViewDrawable
 * author         : letscombine
 * date           : 2022-02-28
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-02-28        letscombine       최초 생성
 */
public class CustomViewDrawable extends View {
      private ShapeDrawable upperDrawable;
      private ShapeDrawable lowerDrawable;
      
      public CustomViewDrawable(Context context) {
            super(context);
            
            init(context);
      }
      
      public CustomViewDrawable(Context context, @Nullable AttributeSet attrs) {
            super(context, attrs);
            
            init(context);
      }
      
      private void init(Context context) {
            //윈도우 매니저를 이용해 뷰의 폭과 높이를 확인
            WindowManager manager = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
            Display display = manager.getDefaultDisplay();
            int width = display.getWidth();
            int height = display.getHeight();
            
            //리소스에 정의된 색상 값을 변수에 설정
            Resources curRes = getResources();
            int blackColor = curRes.getColor(R.color.color01);
            int grayColor = curRes.getColor(R.color.color02);
            int darkGrayColor = curRes.getColor(R.color.color03);
            
            //Drawable 객체 생성
            upperDrawable = new ShapeDrawable();
            
            RectShape rectangle = new RectShape();
            rectangle.resize(width, height * 2 / 3);
            upperDrawable.setShape(rectangle);
            upperDrawable.setBounds(0, 0, 0, height * 2 / 3);
            
            //LinearGradient : 객체 생성
            LinearGradient gradient = new LinearGradient(0, 0, 0, height * 2 / 3, grayColor, blackColor, Shader.TileMode.CLAMP);
            
            Paint paint = upperDrawable.getPaint();
            
            lowerDrawable = new ShapeDrawable();
            
            RectShape rectangle2 = new RectShape();
            rectangle2.resize(width, height * 1 / 3);
            lowerDrawable.setShape(rectangle2);
            lowerDrawable.setBounds(0, height * 2 / 3, width, height);
            
            LinearGradient gradient2 = new LinearGradient(0, 0, 0, height * 1 / 3, blackColor, darkGrayColor, Shader.TileMode.CLAMP);
            
            Paint paint2 = lowerDrawable.getPaint();
            paint2.setShader(gradient2);
      }
      
      @Override
      protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            
            //Drawable 객체 그리기
            upperDrawable.draw(canvas);
            lowerDrawable.draw(canvas);
            
            //Cap.BUTT .. Join.MITER 페인트 객체에 적용
            Paint pathPaint = new Paint();
            pathPaint.setAntiAlias(true);
            pathPaint.setColor(Color.YELLOW);
            pathPaint.setStyle(Paint.Style.STROKE);
            pathPaint.setStrokeWidth(16.0F);
            pathPaint.setStrokeCap(Paint.Cap.BUTT);
            pathPaint.setStrokeJoin(Paint.Join.MITER);
            
            //Path 객체의 생성
            Path path = new Path();
            path.moveTo(20, 20);
            path.lineTo(120, 20);
            path.lineTo(160, 90);
            path.lineTo(180, 80);
            path.lineTo(200, 120);
            
            //path 객체 그리기
            canvas.drawPath(path, pathPaint);
            
            //Cap.ROUND 와 Join.ROUND 페인트 객체에 적용
            pathPaint.setColor(Color.WHITE);
            pathPaint.setStrokeCap(Paint.Cap.ROUND);
            pathPaint.setStrokeJoin(Paint.Join.ROUND);
            
            //offset을 주어 좌표를 이동한 뒤 Path 객체 그리기
            path.offset(30, 120);
            canvas.drawPath(path, pathPaint);
            
            pathPaint.setColor(Color.CYAN);
            pathPaint.setStrokeCap(Paint.Cap.SQUARE);
            pathPaint.setStrokeJoin(Paint.Join.BEVEL);
            
            path.offset(30, 120);
            canvas.drawPath(path, pathPaint);
      }
}

















































