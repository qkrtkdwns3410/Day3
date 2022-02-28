package com.example.day3.graphics.custom.image;

import java.net.ProtocolException;

import javax.xml.parsers.FactoryConfigurationError;

import com.example.day3.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.Nullable;

/**
 *packageName    : com.example.day3.graphics.custom.image
 * fileName       : CustomViewImage
 * author         : letscombine
 * date           : 2022-02-28
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-02-28        letscombine       최초 생성
 */

public class CustomViewImage extends View {
	  
	  private Bitmap cacheBitmap;
	  private Canvas cacheCanvas;
	  private Paint mPaint;
	  
	  public CustomViewImage(Context context) {
			super(context);
			
			init(context);
	  }
	  
	  public CustomViewImage(Context context, @Nullable AttributeSet attrs) {
			super(context, attrs);
			
			init(context);
	  }
	  
	  private void init(Context context) {
			mPaint = new Paint();
	  }
	  
	  protected void onSizeChanged(int w, int h, int oldw, int oldh) {
			createCacheBitmap(w, h);
			textDrawing();
	  }
	  
	  private void createCacheBitmap(int w, int h) {
			//메모리에 BItmap 객체를 만들고 Canvas 객체 설정
			cacheBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
			cacheCanvas = new Canvas();
			cacheCanvas.setBitmap(cacheBitmap);
			
	  }
	  
	  private void textDrawing() {
			cacheCanvas.drawColor(Color.WHITE);
			mPaint.setColor(Color.RED);
			cacheCanvas.drawRect(100, 100, 200, 200, mPaint);
			
			Bitmap srcImg = BitmapFactory.decodeResource(getResources(), R.drawable.waterdrop);
			cacheCanvas.drawBitmap(srcImg, 30, 30, mPaint);
			
			Matrix horInverseMatrix = new Matrix();
			horInverseMatrix.setScale(-1, 1);
			Bitmap horInverseImg = Bitmap.createBitmap(srcImg, 0, 0, srcImg.getWidth(), srcImg.getHeight(), horInverseMatrix, false);
			cacheCanvas.drawBitmap(horInverseImg, 30, 130, mPaint);
			
			Matrix verInverseMatrix = new Matrix();
			verInverseMatrix.setScale(1, -1);
			Bitmap verInverseImg = Bitmap.createBitmap(srcImg, 0, 0, srcImg.getWidth(), srcImg.getHeight(), verInverseMatrix, false);
			cacheCanvas.drawBitmap(verInverseImg, 30, 230, mPaint);
			
	  }
	  
	  @Override
	  protected void onDraw(Canvas canvas) {
			if (cacheBitmap != null) {
				  canvas.drawBitmap(cacheBitmap, 0, 0, null);
			}
	  }
}

























