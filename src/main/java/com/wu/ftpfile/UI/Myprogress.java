package com.wu.ftpfile.UI;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.ProgressBar;

public class Myprogress extends ProgressBar {
	 	private String text_progress="0%";
    /**
     * 自定义进度条画笔
      */
    private Paint mPaint;//
	public Myprogress(Context context) {
		super(context);
		initPaint();
		
	}
	 public Myprogress(Context context, AttributeSet attrs) {  
	        super(context, attrs);  
	        initPaint();  
	    }  
	    public Myprogress(Context context, AttributeSet attrs, int defStyle) {  
	        super(context, attrs, defStyle);  
	        initPaint();  
	    }  
	public synchronized void setProgress(int progress) {  
        super.setProgress(progress);  
        setTextProgress(progress);  
    }  
	protected synchronized void onDraw(Canvas canvas) {  
        super.onDraw(canvas);  
        Rect rect=new Rect();  
        this.mPaint.getTextBounds(this.text_progress, 0, this.text_progress.length(), rect);  
        int x = (getWidth() / 2) - rect.centerX();  
        int y = (getHeight() / 2) - rect.centerY();  
        canvas.drawText(this.text_progress, x, y, this.mPaint);  
    }  
	
	 /** 
     *  
     *description: ��ʼ������ 
     *Create by lll on 2013-8-13 ����1:41:49 
     */  
    private void initPaint(){  
        this.mPaint=new Paint();  
        this.mPaint.setAntiAlias(true);  
        this.mPaint.setColor(Color.WHITE);  
    }  
    private void setTextProgress(int progress){   
        int i = (int) ((progress * 1.0f / this.getMax()) * 100);  
        this.text_progress = String.valueOf(i) + "%";  
    }  
  
}
