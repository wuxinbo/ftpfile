package com.wu.ftpfile.UI;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.EditText;

/**
 * Created by wuxinbo on 2014/10/12.
 */
public class MyEdittext  extends EditText{
    public MyEdittext(Context context) {
        super(context);
    }

    public MyEdittext(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint =new Paint();
        paint.setColor(Color.DKGRAY);
        paint.setStrokeWidth(1);
        int width=getWidth();
        int height =getHeight();
        canvas.drawColor(Color.WHITE);
        canvas.drawLine(0,0,width,0,paint);
        super.onDraw(canvas);

    }
}
