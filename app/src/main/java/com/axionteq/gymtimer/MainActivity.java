package com.axionteq.gymtimer;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import net.crosp.libs.android.circletimeview.CircleTimeView;


public class MainActivity extends AppCompatActivity {

    Handler handler = new Handler();
    CircleTimeView circleTimeView;
    Button btnStart, btnPause;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        circleTimeView = findViewById( R.id.ctv );
        btnPause = findViewById( R.id.btn_pause );
        btnStart = findViewById( R.id.btn_start );

        handler.postDelayed( new Runnable() {
            @Override
            public void run() {
                setClockViewCallbacks();
            }
        }, 2000 );

        btnStart.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        } );


    }

    private void setClockViewCallbacks() {

        circleTimeView.setLapLabelDataProvider( new CircleTimeView.LapDataProvider() {
            @Override
            public String getLapLabelText(long currentTimeInSeconds) {
                return String.valueOf( currentTimeInSeconds % 60 );
            }
        } );

        circleTimeView.setCircleTimeListener( new CircleTimeView.CircleTimeListener() {
            @Override
            public void onTimeManuallySet(long time) {
                Log.d( "Time listener", "TimeManuallySet " + time );
            }

            @Override
            public void onTimeManuallyChanged(long time) {
                Log.d( "Time listener", "TimeManuallyChanged " + time );
            }

            @Override
            public void onTimeUpdated(long time) {
                Log.d( "Time listener", "TimeUpdated " + time );
            }
        } );
        circleTimeView.setCircleTimerListener( new CircleTimeView.CircleTimerListener() {
            @Override
            public void onTimerStop() {
                Log.d( "Time listener", "TimerStop " );

            }

            @Override
            public void onTimerStart(long time) {
                Log.d( "Time listener", "TimerStart " + time );
            }

            @Override
            public void onTimerTimeValueChanged(long time) {
                Log.d( "Time listener", "TimerTimeValueChanged " + time );
            }
        } );
        circleTimeView.startTimer();

    }

    public void Start(View view) {
        circleTimeView.startTimer();
    }

    public void Pause(View view) {
        circleTimeView.stopTimer();
        circleTimeView.setCurrentTimeMode( CircleTimeView.MODE_MANUAL_SETUP );
    }

}
