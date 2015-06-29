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
        private List<App> applist = null;
        private AppAdapter listadapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.layout_2_apps);

            //Initialisieren des PackageManager
            packageManager = getPackageManager();
            //Erzeugen/ Starten der Lokalen Klasse LoadApplication
            // wird im Hintergrund des Programms ausgeführt (Durch execute)
            new LoadApplications().execute();
        }

    //Die Klasse AsyncTask erlaubt es Hintergrundoperationen auszuführen
    // Am Ende werden nur noch die fertigen Ergebnisse innerhalb des UI dargestellt
    private class LoadApplications extends AsyncTask<Void, Void, Void>
    {
        // Erzeugt einen Dialog, um während des Hintergrund Prozesses mit dem User kommunizieren zu können
        private ProgressDialog progress = null;

        // Schritt 1:
        @Override
        //Beginn der Hintergrundabläufe
        protected Void doInBackground(Void... params) {

            FillAppList fillAppList = new FillAppList(packageManager);
            //Befüllen der definierten applist über die Methode checkForLaunchIntent
            applist = fillAppList.checkForLaunchIntent(packageManager.getInstalledApplications(PackageManager.GET_META_DATA));

            //Erzeugen der Klasse AppAdapter und übergabe an einen AppAdapter
            //AppAdapter wird von der Klasse AppListActivity aus aufgerufen
            //Dabei wird das Layout list_item aufgerufen und die befüllte applist übergeben
            listadapter = new AppAdapter(AppListActivity.this, R.layout.list_item, applist);
            //Innerhalb der Methode doInBackground ist ein return notwendig
            return null;
        }

        /**
         *
         * @param result
         * Diese Methode findet nach der Ausführung statt
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
         * Methode wird nach erzeugen der Liste ausgeführt
         */
        @Override
        protected void onPreExecute()
        {
            //Erstellen eines temporären Dialogs, bis die Liste im View angezeigt wird
            progress = ProgressDialog.show(AppListActivity.this, null, "Loading apps info..." );
                    super.onPreExecute();
        }
    }



}
