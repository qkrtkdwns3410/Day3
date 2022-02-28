package com.example.day3.graphics.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;
import androidx.annotation.Nullable;

/**
 *packageName    : com.example.day3.graphics.custom
 * fileName       : CustomVie
 * author         : letscombine
 * date           : 2022-02-28
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-02-28        letscombine       최초 생성
 */
public class CustomView extends View {
	  
	  private Paint paint;
	  
	  public CustomView(Context context) {
			super(context);
			
			init(context);
	  }
	  
	  public CustomView(Context context, @Nullable AttributeSet attrs) {
			super(context, attrs);
			
			init(context);
	  }
	  
	  private void init(Context context) {
			paint = new Paint();
			paint.setColor(Color.RED);
			
	  }
	  
	  @Override
	  protected void onDraw(Canvas canvas) {
			super.onDraw(canvas);
			canvas.drawRect(100, 100, 200, 200, paint);
	  }
	  
	  @Override
	  public boolean onTouchEvent(MotionEvent event) {
			if (event.getAction() == MotionEvent.ACTION_DOWN) {
				  Toast.makeText(super.getContext(), "MotionEvent.ActionDown : " + event.getX() + ", " + event.getY(), Toast.LENGTH_SHORT).show();
			}
	  
			return super.onTouchEvent(event);
	  }
}

























