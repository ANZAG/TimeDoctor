package com.example.gutting.myapplication;

import android.app.ListActivity;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gutting on 29.06.2015.
 */
public class FillAppList{

    PackageManager packageManager = null;

    public FillAppList(PackageManager pm)
    {
        packageManager = pm;
    }

    // Befüllen der AppList
    // Wird innerhalb der Methode DoInBackground aufgerufen
    public List<App> checkForLaunchIntent(List<ApplicationInfo> list)
    {
        //Initialisieren der AppList
        ArrayList<App> appList = new ArrayList<App>();

        //Für jedes Element der Liste list führe aus:
        for(ApplicationInfo info : list)
        {
            try{

                //Wenn Inhalt des Package != Null
                // d.h. wird über die Methode getLaunchIntentForPackage in einem Package eine MainActivity gefunden
                // --> ist die Rückgabe != Null
                if(packageManager.getLaunchIntentForPackage(info.packageName) !=null)
                {
                    CharSequence name = info.loadLabel(packageManager);
                    String pfad = info.packageName;
                    Drawable icon = info.loadIcon(packageManager);

                    App app = new App(name, pfad, icon);
                    //Füge app hinzu
                    appList.add(app);
                }
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        //Rückgabe der befüllten appList
        return appList;
    }
}
