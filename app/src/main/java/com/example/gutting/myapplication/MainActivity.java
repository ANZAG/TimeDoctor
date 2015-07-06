package com.example.gutting.myapplication;
import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Build;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ActionBarActivity {

    /* Variablen fuer Toolbar und Tabs */
    Toolbar toolbar;
    ViewPager pager;
    ViewPagerAdapter adapter;
    SlidingTabLayout tabs;

    private DatenbankManager mDatenbankManager;
    CharSequence Titles[]={"Display","Apps", "Top5", "Facts"};
    int Numboftabs = 4;

    /* Variablen fuer Programmlogik */

    // Displayaufrufe
    int on;

    // Timestamp Display Zeit
    private int hours;
    private int minutes;
    private int seconds;

    // Schleife, die sich nach bestimmter Zeit wiederholt
    private Handler handler = new Handler();

    // Liste aller installierten Apps
    private static ArrayList<App> apps = new ArrayList<>();


    public static ArrayList<App> getApps(){
        return apps;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* Speicher */
        //Uebergeben der gespeicherten Bundles
        if(savedInstanceState != null) {
            on = savedInstanceState.getInt("DisplayOn"); //Display Entsperrungen
            hours = savedInstanceState.getInt("DisplayHours"); //wie lange der Display on war, ohne Berücksichtigung einer App
            minutes = savedInstanceState.getInt("DisplayMinutes");
            seconds = savedInstanceState.getInt("DisplaySeconds");
        }

        /* Toolbar und Tabs*/

        // Finden des Layouts fuer die Toolbar
        toolbar = (Toolbar) findViewById(R.id.tool_bar);


        // Danach dieses als Standard fuer die Activity setzen
        setSupportActionBar(toolbar);
        // Wenn die Toolbar initialisiert wurde
        if(toolbar != null){
            //Setze das TimeDoctor Logo innerhalb der Toolbar
            getSupportActionBar().setIcon(R.mipmap.logo);
            //Setze den Text "TimeDoctor" mit der Textfarbe "weiß"
            getSupportActionBar().setTitle(Html.fromHtml("<font color=\"#FFFFFF\">" + getString(R.string.app_name) + "</font>"));
        }
        // Erstellen des ViewPagerAdapter.
        // Diesem wird der Fragment Manager, die Namen der Tabs und die Anzahl aller Tabs uebergeben.
        adapter =  new ViewPagerAdapter(getSupportFragmentManager(),Titles,Numboftabs);


        // Finden des Layouts fuer die ViewPager View
        pager = (ViewPager) findViewById(R.id.pager);
        // Adapter setzen
        pager.setAdapter(adapter);

        // Finden des Layouts fuer die Sliding Tab Layout View
        tabs = (SlidingTabLayout) findViewById(R.id.tabs);
        // Hier werden die Tabs fixiert und der groesse anteilig ueber den Display angeordnet
        tabs.setDistributeEvenly(true);

        // Setzen der personalisierten Farben der Balken unter den Tabs
        tabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.blue); // Farbgebung Tabalken
            }
        });
        // Setzen des  ViewPager fuer das SlidingTabsLayout
        tabs.setViewPager(pager);

        /* Programm Engine */

        // Ueberpruefe den aktuellen Status des Handydisplays
        registerReceiver(mybroadcast, new IntentFilter(Intent.ACTION_SCREEN_ON));
        registerReceiver(mybroadcast, new IntentFilter(Intent.ACTION_SCREEN_OFF));

        /* Methoden fuer die Tabs */

        // Alle installierten Apps des Handys ausfindig machen
        loadApps();

        // Starte eine Schleife, die sich jede Sekunde neu aufruft
        handler.postDelayed(runnable, 1000);

        mDatenbankManager = new DatenbankManager(this);
        mDatenbankManager.write("Display", hours, minutes, seconds, on);
        mDatenbankManager.read();
    }

    /**
     * Speichern der aktuellen Werte
     * @param outState
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("DisplayOn", on);
        outState.putInt("DisplayHours", hours);
        outState.putInt("DisplayMinutes", minutes);
        outState.putInt("DisplaySeconds", seconds);

        mDatenbankManager.write("Display", hours, minutes, seconds, on);
    }

    /**
     * Erstellen des Broadcast Objekts
     *
     */
    BroadcastReceiver mybroadcast = new BroadcastReceiver() {
        //When Ereignis ausgeloest wird, wird onRecive ausgeloest
        @Override
        public void onReceive(Context context, Intent intent) {

            if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {

                //Start der Methode runnable mittels Handler (Uebergabe 1 Sekunde)
                handler.postDelayed(runnable, 1000);

                Log.i("[BroadcastReceiver]", "Screen ON");
                //Anzahl der Bildschirmentsperrungen + 1
                on++;
                //Neue Anzahl via Textview ausgeben
                Tab1.screenCheck.setText(Integer.toString(on));


            } else if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {

                Log.i("[BroadcastReceiver]", "Screen OFF");

                //Beenden des runnable Handler
                handler.removeCallbacks(runnable);
            }

        }

    };

    /**
     * Laden aller installierten Apps
     */
    public void loadApps(){
        PackageManager manager = this.getPackageManager();

        Intent i = new Intent(Intent.ACTION_MAIN, null);
        i.addCategory(Intent.CATEGORY_LAUNCHER);

        List<ResolveInfo> availableActivities = manager.queryIntentActivities(i, 0);
        for(ResolveInfo ri:availableActivities){
            App app = new App();
            app.setName(ri.loadLabel(manager));
            app.setPfad(ri.activityInfo.packageName);
            app.setIcon(ri.activityInfo.loadIcon(manager));
            apps.add(app);
        }
    }

    /**
     * Schleife, die jede Sekunde aufgerufen wird
     */
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            //Starte Methode setTime
            setTime();
            // Ermitteln der momentan geoeffneten App
            setProgrammTime(getCurrentProgramm());

            Log.i("[Runnable]", "Hochzaehlen");
            // runnable handler jede Sekunde neu starten
            handler.postDelayed(this, 1000);
        }
    };

    /**
     * Zeit hochzaehlen
     */
    public void setTime() {
        // Sekunde hochzaehlen
        seconds++;

        // Wenn 60 Sekunden
        if(seconds > 59) {
            // Minuten hochzaehlen, 60 Sekunden abziehen
            minutes++;

            // Ausgabe der Minuten via TextView
            Tab1.timeStampMinutes.setText(Integer.toString(minutes));

            // Sekunden zuruecksetzen
            seconds = 0;
        }

        // Wenn Minuten = 60
        if(minutes > 59 ){
            // Stunden hochzaehlen, Minuten abziehen
            hours++;

            // Ausgabe der Stunden via TextView
            Tab1.timeStampHours.setText(Integer.toString(hours));

            // Minuten zuruecksetzen
            minutes = 0;
        }

        // Ausgabe der Sekunden via TextView
        Tab1.timeStampSeconds.setText(Integer.toString(seconds));
    }

    /**
     *
     * @param packageName Pfad der aktuell geoeffneten App
     * Setzen der Zeit die eine App im Vordergrund geoeffnet war
     *
     */
    public void setProgrammTime(String packageName){
        //Fuer alle Elemente der Liste fuehre aus:
        for(int i = 0; i < apps.size(); i++)
        {

            String appListItem = apps.get(i).getPfad();

            //Danach vergleichen des Pfades der aktuell geoeffneten App und des Pfades des Elements an der Stelle i
            if (appListItem.equals(packageName))
            {
                apps.get(i).setTime();
            }
        }
    }

    public String getCurrentProgramm(){

        //Initialiere String fuer Package Ausgabe
        String mPackageName;

        // Initialisieren des Activity Managers
        // Aufruf des gewuenschten State Handler --> ACTIVITY_SERVICE
        // Hiermit kann innerhalb der App mit saemtlichen Activitys des Mobilen Geraetes kommuniziert werden
        ActivityManager mActivityManager = (ActivityManager)this.getSystemService(Context.ACTIVITY_SERVICE);


        // Ueberpruefe welches API Version benutzt wird
        // Gebe als Ergebnis den Package Namen der momentan geoeffneten App aus.
        if(Build.VERSION.SDK_INT > 20){
            // Neue Versionen (>20), hierfuer gilt der Befehl getRunningAppProcesses
            mPackageName = mActivityManager.getRunningAppProcesses().get(0).processName;
        }
        else{
            // Aeltere Versionen (<=20), hierfuer gilt der Befehl getRunningTasks
            mPackageName = mActivityManager.getRunningTasks(1).get(0).topActivity.getPackageName();
        }

        //Gebe den Package Namen weiter
        return mPackageName;
    }



}