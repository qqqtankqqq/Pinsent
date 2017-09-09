package com.pinsent.user.pinsent.component;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.pinsent.user.pinsent.R;

import static android.R.attr.data;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;

/**
 * Created by hong on 2017/8/25.
 */

public class BarStripView extends View {
    private Paint backgroungPaint;
    private Paint barPaint;
    private int parentX;
    private int parentY;
    private int data = 0;


    public BarStripView(Context context, AttributeSet attrs) {
        super(context, attrs);
        backgroungPaint = new Paint();
        barPaint = new Paint();
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.BarChartView);
        int barPaintColor = typedArray.getColor(R.styleable.BarChartView_barChartPaintColor, 0xff49667F);
        barPaint.setColor(barPaintColor);
        int backgroungPaintColor = typedArray.getColor(R.styleable.BarChartView_backgroundtPaintColor, 0xffffffff);
        backgroungPaint.setColor(backgroungPaintColor);
        typedArray.recycle();
    }

    public void setdata(int data) {
        this.data = data;
        invalidate();
    }

    public int setW(double Per) {
        return (int) ((Per > 100.0) ? parentX : ((parentX * Per) / 100));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(0, 0, 2000, 55, backgroungPaint);
        double doubleData = Double.valueOf((data / 100 * parentX));
        canvas.drawRect(0, 0, setW(doubleData), 200, barPaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        parentX = MeasureSpec.getSize(widthMeasureSpec);
        parentY = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(parentX | MeasureSpec.EXACTLY, parentY | MeasureSpec.EXACTLY);
    }
}
