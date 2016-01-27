package com.m2dl.androidhac;

import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.opengl.Matrix;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class CustomDrawableView extends View {
    public int width;
    public  int height;
    Bitmap  bmp, resBmp;
    private Path path;
    private Paint paint;

    public CustomDrawableView(Context context) {
        super(context);
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth(12);

        path = new Path();

        this.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View arg0, MotionEvent event) {
                float x = event.getX();
                float y = event.getY();

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        touch_start(x, y);
                        invalidate();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        touch_move(x, y);
                        invalidate();
                        break;
                    case MotionEvent.ACTION_UP:
                        invalidate();
                        break;
                }
                return true;
            }
        });

    }

    public CustomDrawableView(Context context, AttributeSet attr) {
        super(context, attr);
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth(12);
        path = new Path();

        this.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View arg0, MotionEvent event) {
                float x = event.getX();
                float y = event.getY();

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        touch_start(x, y);
                        invalidate();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        touch_move(x, y);
                        invalidate();
                        break;
                    case MotionEvent.ACTION_UP:
                        ImageView imageView = (ImageView) findViewById(R.id.imageView);
                        Bitmap bitmap;
                        try {

                            imageView.setImageBitmap(resBmp);
                            imageView.setVisibility(View.VISIBLE);
                        } catch (Exception e) {
                            Log.e("Camera", e.toString());
                        }
                        invalidate();
                        break;
                }
                return true;
            }
        });

    }


    protected void onDraw(Canvas canvas) {

        if (bmp == null) {
            Bitmap.Config conf = Bitmap.Config.ARGB_8888; // see other conf types
            bmp = Bitmap.createBitmap(canvas.getWidth(),canvas.getHeight(), conf);
        } else {

        }

        Bitmap resizedBitmap = Bitmap.createScaledBitmap(
                bmp, bmp.getWidth(), canvas.getHeight(), false);

        if (resBmp != null) {
            resizedBitmap = resBmp;
        }

        canvas.drawBitmap(resizedBitmap, 0, 0, null);

        canvas.drawPath(path, paint);

        //
        resBmp = resizedBitmap;

        //canvas.drawBitmap(resizedBitmap, 0, 0, null);


        invalidate();
    }

    private float mX, mY;
    private static final float TOUCH_TOLERANCE = 4;

    private void touch_start(float x, float y) {
        path.reset();
        path.moveTo(x, y);
        mX = x;
        mY = y;
    }

    private void touch_move(float x, float y) {
        float dx = Math.abs(x - mX);
        float dy = Math.abs(y - mY);
        if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
            path.quadTo(mX, mY, (x + mX)/2, (y + mY)/2);
            mX = x;
            mY = y;
        }
    }

    public Bitmap getBmp() {
        return bmp;
    }

    public void setBmp(Bitmap bmp) {
        this.bmp = bmp;
        invalidate();
    }
}