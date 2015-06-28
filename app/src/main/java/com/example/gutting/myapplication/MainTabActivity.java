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

    TextView screenCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_1_main);

        registerReceiver(mybroadcast, new IntentFilter(Intent.ACTION_SCREEN_ON));
        registerReceiver(mybroadcast, new IntentFilter(Intent.ACTION_SCREEN_OFF));

        //Test
        screenCheck = (TextView) findViewById(R.id.tv_counter);
        //screenCheck = new TextView(this);
        //screenCheck.setText(Integer.toString(on));
        //setContentView(screenCheck);
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

                // gesamte Anzahl online in sekunden.
                gesamt = gesamt + (ende - start);

                Log.i("[BroadcastReceiver]", "Screen OFF");
                Log.i("[BroadcastReceiver]", String.valueOf(ende));
                Log.i("[BroadcastReceiver]", String.valueOf(gesamt));
            }

        }
    };
}
