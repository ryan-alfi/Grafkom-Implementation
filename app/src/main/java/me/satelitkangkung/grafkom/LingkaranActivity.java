package me.satelitkangkung.grafkom;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import butterknife.ButterKnife;

public class LingkaranActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MyCircleView(this));

        ButterKnife.bind(this);
        Toast.makeText(this, "Pewarnaan (fill) dibuat opacity 50% agar tipe garis terlihat", Toast.LENGTH_SHORT).show();
    }

    public class MyCircleView extends View{

        public MyCircleView(Context context) {
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            Intent intent = getIntent();
            int x = intent.getIntExtra("xcircle",0);
            int y = intent.getIntExtra("ycircle",0);
            int rad = intent.getIntExtra("radcircle",0);

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
            paint.setAlpha(150);
            mPaint.setStyle(Paint.Style.STROKE);

            if (type == 2){
                mPaint.setPathEffect(new DashPathEffect(new float[] {30,10}, 0));
            }else if (type == 1) {
                mPaint.setPathEffect(new DashPathEffect(new float[] { 5, 5 }, 0));
            }
            Log.d("Tebal" , "nih "+tebalnya);
            mPaint.setStrokeWidth(tebalnya);
            canvas.drawCircle(x,y,rad,paint);
            canvas.drawCircle(x,y,rad,mPaint);

        }
    }
}
