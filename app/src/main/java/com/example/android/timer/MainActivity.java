package com.example.android.timer;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.Timer;

public class MainActivity extends AppCompatActivity {
    SeekBar timerSeekBar;
     TextView timerTextView;
     Boolean counterIsActive=false;
     Button controllerButton;
     CountDownTimer countDownTimer;
    public void resetTimer() {

        timerTextView.setText("0:30");
        timerSeekBar.setProgress(30);
        countDownTimer.cancel();
        controllerButton.setText("Go!");
        timerSeekBar.setEnabled(true);
        counterIsActive = false;

    }
    public void updateTimer(int secLeft){
        int minutes=(int)secLeft/60;
        int seconds =secLeft-minutes*60;
        String secondstring=Integer.toString(seconds);
        if(seconds<=9){
            secondstring="0"+secondstring;
        }

        timerTextView.setText(Integer.toString(minutes)+":"+secondstring);

    }
    public void controlTimer(View view){
        if(counterIsActive==false){
        counterIsActive=true;
        timerSeekBar.setEnabled(false);
            controllerButton.setText("Stop");
      countDownTimer=  new CountDownTimer(timerSeekBar.getProgress() * 1000+100, 1000) {
            @Override
            public void onTick(long l) {
updateTimer((int)l/1000);
            }

            @Override
            public void onFinish() {
resetTimer();
                MediaPlayer mplayer=MediaPlayer.create(getApplicationContext(),R.raw.airhorn);
                mplayer.start();
            }
        }.start();

        }
        else {
            resetTimer();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timerSeekBar= (SeekBar) findViewById(R.id.timerSeekBar);
        timerTextView=(TextView) findViewById(R.id.timerTextView);
        controllerButton=(Button) findViewById(R.id.controllerButton);

        timerSeekBar.setMax(600);
        timerSeekBar.setProgress(30);
        timerSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
updateTimer(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
