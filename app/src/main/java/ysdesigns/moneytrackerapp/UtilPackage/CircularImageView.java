package com.example.james.moneytracker.UtilPackage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by james on 15-07-19.
 */
public class CircularImageView extends ImageView {

    private Integer outerCirclePaintColor;
    private Integer mSpecSize;


    public CircularImageView(Context context) {
        super(context);
        outerCirclePaintColor = Color.parseColor("#99ff0000");
    }

    public CircularImageView(Context context, Drawable drawable, int color) {
        super(context);
        setImageDrawable(drawable);
        outerCirclePaintColor = Color.parseColor("#99ff0000");

    }

    public CircularImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        outerCirclePaintColor = Color.parseColor("#99ff0000");
    }

    public CircularImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        outerCirclePaintColor = Color.parseColor("#99ff0000");
    }

    @Override
    protected void onDraw(Canvas canvas) {

        // Choosing the smaller dimension between width and height
        int length = mSpecSize;
        int outerCircleRadius = 10;

        // Setting the paint color.
        Paint paint = new Paint();
        if (outerCirclePaintColor != null ) {
            paint.setColor(outerCirclePaintColor);
        }
        // Resizing the Bitmap
        Bitmap bitmap = ((BitmapDrawable) getDrawable()).getBitmap();
        bitmap = Bitmap.createScaledBitmap(bitmap, (int) (length*0.9), (int) (length*0.9), false);

        // Drawing the circle and the image.
        canvas.drawCircle(length/2, length/2, length / 2 - outerCircleRadius, paint);
        canvas.drawBitmap(bitmap, (float) (length*0.05), (float) (length*0.05), paint);
    }

    public void setBackgroundPaintColor(int color) {
        this.outerCirclePaintColor = color;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = measureWidth(widthMeasureSpec);
        int height = measureHeight(heightMeasureSpec);
        mSpecSize = Math.min(width, height);
        setMeasuredDimension(width, height);
    }

    private int measureWidth(int measureSpec) {
        int result = 0;
        int specMode = View.MeasureSpec.getMode(measureSpec);
        int specSize = View.MeasureSpec.getSize(measureSpec);

        if (specMode == View.MeasureSpec.EXACTLY) {
            // The parent has determined an exact size for the child.
            result = specSize;
        } else if (specMode == View.MeasureSpec.AT_MOST) {
            // The child can be as large as it wants up to the specified size.
            result = specSize;
        } else {
            // The parent has not imposed any constraint on the child.
            result = specSize;
        }

        return result;
    }

    private int measureHeight(int measureSpecHeight) {
        int result = 0;
        int specMode = View.MeasureSpec.getMode(measureSpecHeight);
        int specSize = View.MeasureSpec.getSize(measureSpecHeight);

        if (specMode == View.MeasureSpec.EXACTLY) {
            // We were told how big to be
            result = specSize;
        } else if (specMode == View.MeasureSpec.AT_MOST) {
            result = specSize;
        } else {
            result = specSize;
        }

        return (result + 2);
    }

}
