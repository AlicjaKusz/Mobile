package com.example.admin.akcelerometr;

import android.app.Activity;
import android.os.Bundle;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.widget.TextView;

public class MainActivity extends Activity implements SensorEventListener {

    private TextView Up, Down, Right, Left;
    private Sensor mySensor;
    private SensorManager SM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Up = (TextView)findViewById(R.id.up);
        Down = (TextView)findViewById(R.id.down);
        Right = (TextView)findViewById(R.id.right);
        Left = (TextView)findViewById(R.id.left);

        SM = (SensorManager) getSystemService(SENSOR_SERVICE);
        mySensor = SM.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        // extends z "AppCompatActivity" na "Activity implements SensorEventListener"
        SM.registerListener(this, mySensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        if(event.values[0]>=0)
        {
            Left.setText(""+Math.round(event.values[0]));
            Right.setText("0");
        }

        else {
            Left.setText("0");
            Right.setText(""+Math.round(Math.abs(event.values[0])));
        }

        if(event.values[1]>=0)
        {
            Up.setText("0");
            Down.setText(""+Math.round(event.values[1]));
        }

        else {
            Up.setText(""+Math.round(Math.abs(event.values[1])));
            Down.setText("0");
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        //not use
    }

}