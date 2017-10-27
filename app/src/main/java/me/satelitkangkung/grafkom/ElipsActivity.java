package me.satelitkangkung.grafkom;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class ElipsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MyElipsView(this));

        Toast.makeText(this, "Pewarnaan (fill) dibuat opacity 50% agar tipe garis terlihat", Toast.LENGTH_SHORT).show();
    }

    public class MyElipsView extends View{
        public MyElipsView(Context context) {
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            Intent intent = getIntent();
            int x0 = intent.getIntExtra("xjari",0);
            int x1 = intent.getIntExtra("yjari",0);
            int y0 = intent.getIntExtra("xpusat",0);
            int y1 = intent.getIntExtra("ypusat",0);

            int type = intent.getIntExtra("tipeGaris", 0);
            int tebalnya = intent.getIntExtra("ketebalanElips",0);
            int warnanya = intent.getIntExtra("colorElips", 0);

            Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
            Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

            paint.setColor(Color.BLACK);
            canvas.drawPaint(paint);
            if (warnanya == 0){
                paint.setColor(Color.WHITE);
                mPaint.setColor(Color.WHITE);
            }else {
                paint.setColor(warnanya);
                mPaint.setColor(warnanya);
            }

            paint.setStyle(Paint.Style.FILL);
            paint.setAlpha(125);
            mPaint.setStyle(Paint.Style.STROKE);


            if (type == 2){
                mPaint.setPathEffect(new DashPathEffect(new float[] {30,10}, 0));
            }else if (type == 1) {
                mPaint.setPathEffect(new DashPathEffect(new float[] { 5, 5 }, 0));
            }

            mPaint.setStrokeWidth(tebalnya);

            RectF oval = new RectF(y0, y1, x0, x1);
            canvas.drawOval(oval, paint);
            canvas.drawOval(oval, mPaint);
        }

    }
}
