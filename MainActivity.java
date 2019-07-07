package com.example.stopwatch;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends Activity {
    private int seconds = 0;
    private boolean running;
    private boolean wasrunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState != null){
            seconds = savedInstanceState.getInt("second");
            running = savedInstanceState.getBoolean("running");
            wasrunning = savedInstanceState.getBoolean("wasrunning");
        }
        runTimer();
    }
    @Override
     protected void onPause(){
        super.onPause();
        wasrunning = running;
        running=false;

     }
     @Override
     protected void onResume(){
        super.onResume();
        if(wasrunning){
            running = true;
        }
     }
     public void onSaveInstanceState(Bundle saveInstanceState){
        saveInstanceState.putInt("seconds",seconds);
        saveInstanceState.putBoolean("runnind",running);
        saveInstanceState.putBoolean("wasrunning",wasrunning);
     }
    public void onClickStart(View view) {
        running = true;
    }
    public void onClickStop(View view){
        running=false;
    }
    public void onClickReset(View view){
        running=false;
        seconds=0;
    }
    private void runTimer(){
        final TextView timeview=(TextView)findViewById(R.id.time_view);
        final Handler handler=new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds/3600;
                int minutes = (seconds%3600)/60;
                int secs=seconds%60;
                String time = String.format("%d:%02d:%02d",hours,minutes,secs);
                timeview.setText(time);
                if(running){
                    seconds++;
                }
              handler.postDelayed(this,1000);
            }
        });
    }
}
