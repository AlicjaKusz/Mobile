package com.example.admin.akcelerometr;

import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.os.Handler;
import android.view.Display;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends Activity implements SensorEventListener {

    private int screenWidth, screenHeight;
    private ImageView Ball;
    private float ballX, ballY;

    private TextView Up, Down, Right, Left, frames, balls;
    private Sensor mySensor;
    private SensorManager SM;
    float upper, downer, lefter,righter;
    private int frameHeight;
    int ballSize, downStop;

    private Handler handler = new Handler();
    private Timer timer = new Timer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        frames = (TextView) findViewById(R.id.frames);
        balls = (TextView) findViewById(R.id.balls);
        Ball = (ImageView)findViewById(R.id.Ball_id);
        SM = (SensorManager) getSystemService(SENSOR_SERVICE);
        mySensor = SM.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        // extends z "AppCompatActivity" na "Activity implements SensorEventListener"
        SM.registerListener(this, mySensor, SensorManager.SENSOR_DELAY_NORMAL);


        WindowManager wm= getWindowManager();
        Display disp = wm.getDefaultDisplay();
        Point size = new Point();
        disp.getSize(size);
        screenHeight = size.y;
        screenWidth = size.x;

        Ball.setX(screenHeight/2);
        Ball.setY(screenWidth/2);

        frames.setText(""+screenWidth);


        //ballSize = Ball.getHeight();
        balls.setText(""+Ball.getHeight());

       timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        changePos();
                    }
                });
            }
        }, 0,20);

    }

    public void changePos() {


        if(upper > 2)
        {
            ballY -= (upper*2);
        }

        if(downer > 2)
        {
            ballY += (downer*2);
        }

       // if(righter > 2) ballX -= (righter*2);


       // if(lefter > 2) ballX += (lefter*2);

        if(ballY < 0)
        {
           ballY = 0;
        }

        if (ballY > screenHeight - ballSize) {
           ballY = screenHeight - ballSize;
        }

        Ball.setX(ballX);
        Ball.setY(ballY);

    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        if(event.values[0]>=0)
        {
           // Left.setText(""+Math.round(event.values[0]));
           // Right.setText("0");
            lefter= event.values[1];
           /* if(lefter > 1) {
                ballX -= (lefter * 4);
                Ball.setY(ballX);
            }*/
        }

        else {
           // Left.setText("0");
           // Right.setText(""+Math.round(Math.abs(event.values[0])));
            righter= event.values[1];
            /*if(righter > 1) {
                ballX += (righter * 4);
                Ball.setY(ballX);
            }*/
        }

        if(event.values[1]>=0)
        {
           // Up.setText("0");
           // Down.setText(""+Math.round(event.values[1]));
            downer = event.values[1];
           /* if(downer > 1) {
                ballY += (downer * 4);
                Ball.setY(ballY);
            }*/
        }

        else {
          //  Up.setText("" + Math.round(Math.abs(event.values[1])));
          //  Down.setText("0");
            upper = Math.abs(event.values[1]);
            /*if (upper > 1) {
                ballY -= (upper * 4);
                Ball.setY(ballY);

            }*/
        }


        /*ballX += -event.values[0]*10;
        ballY += event.values[1]*10;
        Ball.setX(ballX);
        Ball.setY(ballY);
        */

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        //not use
    }

}