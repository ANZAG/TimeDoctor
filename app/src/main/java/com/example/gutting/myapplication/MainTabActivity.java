package com.example.gutting.myapplication;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;

import java.util.Iterator;
import java.util.List;

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
    //private FillAppList fillAppList = new FillAppList();

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

        // Ausgabe der Stunden, Minuten, Sekunden via TextView
        timeStamp.setText("Stunden: " + Integer.toString(hours)
                + "\nMinuten: " + Integer.toString(minutes)
                + "\nSekunden: " + Integer.toString(secounds));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_1_main);

        handler.postDelayed(runnable, 1000); //Test

        registerReceiver(mybroadcast, new IntentFilter(Intent.ACTION_SCREEN_ON));
        registerReceiver(mybroadcast, new IntentFilter(Intent.ACTION_SCREEN_OFF));

        // Initialisieren der TextView Felder
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
                //Anzahl der Bildschirmentsperrungen + 1
                on++;
                //Neue Anzahl via Textview ausgeben
                screenCheck.setText("Anzahl Entsperrungen: " + Integer.toString(on));


            } else if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {

                Log.i("[BroadcastReceiver]", "Screen OFF");

                //Beenden des runnable Handler
                handler.removeCallbacks(runnable);
            }

        }

    };
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            // Sekunde hochz�hlen
            secounds++;
            //Starte Methode setTime
            setTime();
            // Ermitteln der momentan ge�ffneten App
            setProgrammTime(getCurrentProgramm());


            Log.i("[Runnable]", "Hochzaehlen");
            // runnable handler jede Sekunde neu starten
            handler.postDelayed(this, 1000);
        }
    };

    public String getCurrentProgramm(){

        //Initialiere String f�r Package Ausgabe
        String mPackageName;

        // Initialisieren des Activity Managers
        // Aufruf des gew�nschten State Handler --> ACTIVITY_SERVICE
        // Hiermit kann innerhalb der App mit s�mtlichen Activitys des Mobilen Ger�tes kommuniziert werden
        ActivityManager mActivityManager =(ActivityManager)this.getSystemService(Context.ACTIVITY_SERVICE);

        // �berpr�fe welches API Version benutzt wird
        // Gebe als Ergebnis den Package Namen der momentan ge�ffneten App aus.
        if(Build.VERSION.SDK_INT > 20){
            // Neue Versionen (>20), hierf�r gilt der Befehl getRunningAppProcesses
            mPackageName = mActivityManager.getRunningAppProcesses().get(0).processName;
        }
        else{
            // �ltere Versionen (<=20), hierf�r gilt der Befehl getRunningTasks
           mPackageName = mActivityManager.getRunningTasks(1).get(0).topActivity.getPackageName();
        }
        Log.i("Package", mPackageName);
        //Gebe den Package Namen weiter
        return mPackageName;
    }

    public void setProgrammTime(String packageName){

        //�bergebe die AppList an eine lokale Liste
       /* List<ApplicationInfo> appList = fillAppList.getAppList();

        for(int i = 0; i < appList.size(); i++)
        {
           Log.i("AppList", "" + appList.get(i));
        }*/
    }
}