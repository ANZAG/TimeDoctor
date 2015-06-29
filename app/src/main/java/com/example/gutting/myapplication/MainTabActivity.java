package com.example.gutting.myapplication;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by Gutting on 26.06.2015.
 */
public class MainTabActivity extends Activity {

    public int on = 0;

    // Timestamp
    private int hours;
    private int minutes;
    private int secounds;

    private Handler handler = new Handler();

    TextView screenCheck;
    TextView timeStamp;

    private void setTime() {
        // Wenn 60 Sekunden
        if(secounds > 59) {
            // Minuten hochz�hlen, 60 Sekunden abziehen
            minutes++;
            secounds = 0;
        }

        // Wenn Minuten = 60
        if(minutes > 59 ){
            // Stunden hochz�hlen, Minuten abziehen
            hours++;
            minutes = 0;
        }



        timeStamp.setText("Stunden: " + Integer.toString(hours)
                + "\nMinuten: " + Integer.toString(minutes)
                + "\nSekunden: " + Integer.toString(secounds));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_1_main);

        registerReceiver(mybroadcast, new IntentFilter(Intent.ACTION_SCREEN_ON));
        registerReceiver(mybroadcast, new IntentFilter(Intent.ACTION_SCREEN_OFF));

        screenCheck = (TextView) findViewById(R.id.tv_counter);
        timeStamp = (TextView) findViewById(R.id.tv_timestamp);

    }


    //Create broadcast object
    BroadcastReceiver mybroadcast = new BroadcastReceiver() {
        //When Event is published, onReceive method is called
        @Override
        public void onReceive(Context context, Intent intent) {
            // TODO Auto-generated method stub
            Log.i("[BroadcastReceiver]", "MyReceiver");

            if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {

                //Start der Methode runnable mittels Handler (�bergabe 1 Sekunde)
                handler.postDelayed(runnable, 1000);

                Log.i("[BroadcastReceiver]", "Screen ON");
                on++;
                screenCheck.setText("Anzahl Entsperrungen: " + Integer.toString(on));

            } else if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {

                Log.i("[BroadcastReceiver]", "Screen OFF");

                //Beende den runnable Handler
                handler.removeCallbacks(runnable);
            }

        }

    };
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            // Sekunde hochz�hlen
            secounds++;
            setTime();
            Log.i("[Runnable]", "Hochzaehlen");
            // runnable handler jede Sekunde neu starten
            handler.postDelayed(this, 1000);
        }
    };
}