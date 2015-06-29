package com.example.gutting.myapplication;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by Gutting on 26.06.2015.
 */
public class MainTabActivity extends Activity {

    public int on = 0;

    // Timestamp
    private long start;
    private long ende;
    private long gesamt;

    private int hours;
    private int minutes;
    private int secounds;

    TextView screenCheck;
    TextView timeStamp;

    private void setTime()
    {
        // gesamte Anzahl online in sekunden.
        gesamt = gesamt + (ende - start);

        hours = (int) (gesamt / 3600);
        gesamt = gesamt - hours * 3600;
        minutes = (int) (gesamt / 60);
        gesamt = gesamt - minutes * 60;
        secounds = (int) gesamt;
        gesamt = gesamt - secounds;

        timeStamp.setText("Stunden: " + Integer.toString(hours)
                      + "\n Minuten: " + Integer.toString(minutes)
                      + "\n Sekunden: " + Integer.toString(secounds));


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_1_main);

        // Initiales setzen des Timestamps beim Start der App
        if (start == 0)
        {
            start = System.currentTimeMillis()/1000;
        }

        registerReceiver(mybroadcast, new IntentFilter(Intent.ACTION_SCREEN_ON));
        registerReceiver(mybroadcast, new IntentFilter(Intent.ACTION_SCREEN_OFF));

        screenCheck = (TextView) findViewById(R.id.tv_counter);
        timeStamp= (TextView) findViewById(R.id.tv_timestamp);
        setTime();
    }


    //Create broadcast object
    BroadcastReceiver mybroadcast = new BroadcastReceiver() {
        //When Event is published, onReceive method is called
        @Override
        public void onReceive(Context context, Intent intent) {
            // TODO Auto-generated method stub
            Log.i("[BroadcastReceiver]", "MyReceiver");

            if(intent.getAction().equals(Intent.ACTION_SCREEN_ON)){

                // Timestamp setzen dabei in sekunden umrechnen (/1000)
                start = System.currentTimeMillis()/1000;

                Log.i("[BroadcastReceiver]", "Screen ON");
                Log.i("[BroadcastReceiver]", String.valueOf(start));

                on++;
                screenCheck.setText("Anzahl Entsperrungen: "+Integer.toString(on));
            }
            else if(intent.getAction().equals(Intent.ACTION_SCREEN_OFF)){
                // Timestamp setzen dabei in sekunden umrechnen (/1000)
                ende = System.currentTimeMillis()/1000;

                Log.i("[BroadcastReceiver]", "Screen OFF");
                Log.i("[BroadcastReceiver]", String.valueOf(ende));
                Log.i("[BroadcastReceiver]", String.valueOf(gesamt));
            }

        }
    };
}
