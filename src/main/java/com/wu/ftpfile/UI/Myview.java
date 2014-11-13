package com.wu.ftpfile.UI;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.wu.ftpfile.R;

import java.util.jar.Attributes;

/**
 * Created by wuxinbo on 2014/10/15.
 */
public class Myview  extends View{
    public Myview(Context context) {
        super(context);
    }
    public Myview(Context context,AttributeSet attr){
        super(context,attr);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint =new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(5);
        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(18);
        canvas.drawColor(getResources().getColor(R.color.background));
    }
}
