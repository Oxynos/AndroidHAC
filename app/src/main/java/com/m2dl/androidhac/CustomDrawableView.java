package com.m2dl.androidhac;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.opengl.Matrix;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class CustomDrawableView extends View {
    private ShapeDrawable mDrawable;
    int x = 10; int y = 10;
    int width = 100; int height = 100;

    public CustomDrawableView(Context context) {
        super(context);
        mDrawable = new ShapeDrawable(new OvalShape());
        mDrawable.getPaint().setColor(0xff74AC23);

        this.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View arg0, MotionEvent arg1) {
                switch (arg1.getAction()) {
                    case MotionEvent.ACTION_MOVE: {
                        System.out.println("OKKK");
                        invalidate(); // pour invalider l'image et forcer un rappel à la methode onDraw de la classe.
                    }
                }
                return true;
            }
        });

    }
    public CustomDrawableView(Context context, AttributeSet attr) {
        super(context, attr);
        mDrawable = new ShapeDrawable(new OvalShape()); // ici on affiche un oval...
        mDrawable.getPaint().setColor(0xff74AC23);

        this.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View arg0, MotionEvent arg1) {
                switch (arg1.getAction()) {
                    case MotionEvent.ACTION_MOVE: {
                        x = (int) arg1.getX();
                        y = (int) arg1.getY();
                        invalidate(); // pour invalider l'image et forcer un rappel à la methode onDraw de la classe.
                    }
                }
                return true;
            }
        });

    }
    protected void onDraw(Canvas canvas) {
        mDrawable.setBounds(x, y, x + width, y + height);
        mDrawable.draw(canvas);
    }
}