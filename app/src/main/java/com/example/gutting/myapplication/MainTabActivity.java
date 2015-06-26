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

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_1_main);

        registerReceiver(mybroadcast, new IntentFilter(Intent.ACTION_SCREEN_ON));
        registerReceiver(mybroadcast, new IntentFilter(Intent.ACTION_SCREEN_OFF));

        TextView screenCheck = new TextView(this);
        screenCheck.setText(Integer.toString(on));
        setContentView(screenCheck);
    }

    public int on = 0;

    //Create broadcast object
    BroadcastReceiver mybroadcast = new BroadcastReceiver() {
        //When Event is published, onReceive method is called
        @Override
        public void onReceive(Context context, Intent intent) {
            // TODO Auto-generated method stub
            Log.i("[BroadcastReceiver]", "MyReceiver");

            if(intent.getAction().equals(Intent.ACTION_SCREEN_ON)){
                Log.i("[BroadcastReceiver]", "Screen ON");
                on++;
            }
            else if(intent.getAction().equals(Intent.ACTION_SCREEN_OFF)){
                Log.i("[BroadcastReceiver]", "Screen OFF");
            }

        }
    };
}
