package com.example.gutting.myapplication;

import android.app.TabActivity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends TabActivity{

    private TabHost mTabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_0_start);

        /*******************Tabs*****************************/
        //Definieren der Tab Reiter Variablen
        mTabHost = getTabHost();
        TabHost.TabSpec spec;
        Intent intent;

        loadApps();

        // Main tab
        intent = new Intent(this, MainTabActivity.class);
        spec = mTabHost.newTabSpec("main")
                .setIndicator("Main")
                .setContent(intent);

        mTabHost.addTab(spec);

        // Apps
        intent = new Intent(this, AppListActivity.class);

        spec = mTabHost.newTabSpec("apps")
                .setIndicator("Apps")
                .setContent(intent);

        mTabHost.addTab(spec);

        // Top10
        intent = new Intent(this, TopTenActivity.class);

        spec = mTabHost.newTabSpec("top10")
                .setIndicator("Top10")
                .setContent(intent);

        mTabHost.addTab(spec);

        // Tipps
        intent = new Intent(this, DataSafeActivity.class);

        spec = mTabHost.newTabSpec("tipps")
                .setIndicator("Tipps")
                .setContent(intent);

        mTabHost.addTab(spec);


        /*******************Tabs*****************************/
    }

    private PackageManager manager;

    private static ArrayList<App> apps = new ArrayList<App>();

    public static ArrayList<App> getApps(){
        return apps;
    }


    public void loadApps(){

        Log.i("[LoadApps]", "ausgeführt");

        manager = getPackageManager();

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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    //Bei Auswahl einer der Button, wird geprüft, welcher Text übergeben wird
    // und es wird die dazugehörige Seite geöffnet

}
