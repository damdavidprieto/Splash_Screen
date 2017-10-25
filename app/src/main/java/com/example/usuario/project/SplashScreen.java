package com.example.usuario.project;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class SplashScreen extends AppCompatActivity {

    Test myTest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        /*
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent= new Intent(SplashScreen.this,MainActivity.class);
                startActivity(intent);
            }
        },4000);
        */
        myTest = new Test(this);
        setContentView(myTest);
    }
    private class Test extends View{

        private Timer time;
        private TimerTask tarea;
        private int  contador,aux;

        public Test(final Context context){

            super(context);
            contador=0;
            aux=40;
            time=new Timer();

            tarea=new TimerTask() {
                @Override
                public void run() {
                    if(contador==100){
                        time.cancel();
                        Intent intent= new Intent(SplashScreen.this,MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    contador++;
                    repintar();
                }
            };

            time.schedule(tarea,0,100);
        }

        public void repintar(){
            postInvalidate();
        }

        public void onDraw(Canvas canvas){
            super.onDraw(canvas);
            Paint paint = new Paint();
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(Color.BLUE);
            canvas.drawPaint(paint);

            paint.setAntiAlias(true);
            paint.setColor(Color.BLACK);
            canvas.drawRect(getWidth()/2-getWidth()/4,getHeight()/2-30,getWidth()/2+getWidth()/4,getHeight()/2+30,paint);

            int inicio=getWidth()/2-getWidth()/4+15;
            int fin=getWidth()/2+getWidth()/4-15;
            int recorrido=fin-inicio;
            int aux=inicio+(recorrido*contador/100);

            paint.setColor(Color.RED);
            canvas.drawRect(inicio,getHeight()/2-30+15,aux,getHeight()/2+30-15,paint);

        }

    }
}
