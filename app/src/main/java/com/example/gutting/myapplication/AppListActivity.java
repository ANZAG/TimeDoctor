package com.example.gutting.myapplication;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.List;

public class AppListActivity extends ListActivity {

        private PackageManager packageManager = null;
        private List<ApplicationInfo> applist = null;
        private AppAdapter listadapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.layout_2_apps);

            //Initialisieren des PackageManager
            packageManager = getPackageManager();
            //Erzeugen/ Starten der Lokalen Klasse LoadApplication
            // wird im Hintergrund des Programms ausgef�hrt (Durch execute)
            new LoadApplications().execute();
        }

    //Die Klasse AsyncTask erlaubt es Hintergrundoperationen auszuf�hren
    // Am Ende werden nur noch die fertigen Ergebnisse innerhalb des UI dargestellt
    private class LoadApplications extends AsyncTask<Void, Void, Void>
    {
        // Erzeugt einen Dialog, um w�hrend des Hintergrund Prozesses mit dem User kommunizieren zu k�nnen
        private ProgressDialog progress = null;

        // Schritt 1:
        @Override
        //Beginn der Hintergrundabl�ufe
        protected Void doInBackground(Void... params) {
            //Bef�llen der definierten applist �ber die Methode checkForLaunchIntent
            applist = checkForLaunchIntent(packageManager.getInstalledApplications(PackageManager.GET_META_DATA));

            //Erzeugen der Klasse AppAdapter und �bergabe an einen AppAdapter
            //AppAdapter wird von der Klasse AppListActivity aus aufgerufen
            //Dabei wird das Layout list_item aufgerufen und die bef�llte applist �bergeben
            listadapter = new AppAdapter(AppListActivity.this, R.layout.list_item, applist);
            //Innerhalb der Methode doInBackground ist ein return notwendig
            return null;
        }

        /**
         *
         * @param result
         * Diese Methode findet nach der Ausf�hrung statt
         */
        @Override
        protected void onPostExecute(Void result)
        {
            //Der erstellte ListAdapter wird gesetzt
            setListAdapter(listadapter);
            progress.dismiss();
            super.onPostExecute(result);
        }

        /**
         *
         * Methode wird nach erzeugen der Liste ausgef�hrt
         */
        @Override
        protected void onPreExecute()
        {
            //Erstellen eines tempor�ren Dialogs, bis die Liste im View angezeigt wird
            progress = ProgressDialog.show(AppListActivity.this, null, "Loading apps info..." );
                    super.onPreExecute();
        }
    }

    // Bef�llen der AppList
    // Wird innerhalb der Methode DoInBackground aufgerufen
    private List<ApplicationInfo> checkForLaunchIntent(List<ApplicationInfo> list)
    {
        //Initialisieren der AppList
        ArrayList<ApplicationInfo> appList = new ArrayList<ApplicationInfo>();

        //F�r jedes Element der Liste list f�hre aus:
        for(ApplicationInfo info : list)
        {
            try{
                //Wenn Inhalt des Package != Null
                // d.h. wird �ber die Methode getLaunchIntentForPackage in einem Package eine MainActivity gefunden
                // --> ist die R�ckgabe != Null
                if(packageManager.getLaunchIntentForPackage(info.packageName) !=null)
                {
                    //F�ge app hinzu
                    appList.add(info);
                }
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        //R�ckgabe der bef�llten appList
        return appList;
    }

}
