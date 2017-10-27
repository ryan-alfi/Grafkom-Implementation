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

public class GarisActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MyLineView(this));
    }

    public class  MyLineView extends View {

        public MyLineView(Context context) {
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            Intent intent = getIntent();
            int x0 = intent.getIntExtra("x0line",0);
            int x1 = intent.getIntExtra("x1line",0);
            int y0 = intent.getIntExtra("y0line",0);
            int y1 = intent.getIntExtra("y1line",0);

            int type = intent.getIntExtra("tipeGaris", 0);
            int tebalnya = intent.getIntExtra("ketebalanElips",0);
            int warnanya = intent.getIntExtra("colorGaris", 0);

            Paint paint = new Paint();
            setLayerType(View.LAYER_TYPE_SOFTWARE,paint);
            paint.setColor(Color.BLACK);
            canvas.drawPaint(paint);
            paint.setStyle(Paint.Style.STROKE);
            paint.setAlpha(120);
            paint.setStrokeWidth(tebalnya);
            if (warnanya == 0){
                paint.setColor(Color.WHITE);
            }else {
                paint.setColor(warnanya);
            }

            if (type == 2){
                paint.setPathEffect(new DashPathEffect(new float[] {30,10}, 0));
            }else if (type == 1) {
                paint.setPathEffect(new DashPathEffect(new float[] { 5, 5 }, 0));
            }

            canvas.drawLine(x0,y0,x1,y1,paint);




        }
    }
}
