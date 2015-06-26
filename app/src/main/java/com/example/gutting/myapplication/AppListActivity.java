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

            packageManager = getPackageManager();
            new LoadApplications().execute();
        }


    private List<ApplicationInfo> checkForLaunchIntent(List<ApplicationInfo> list)
    {
        ArrayList<ApplicationInfo> appList = new ArrayList<ApplicationInfo>();

        for(ApplicationInfo info : list)
        {
            try{
                if(packageManager.getLaunchIntentForPackage(info.packageName) !=null)
                {
                    appList.add(info);
                }
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        return appList;
    }



    private class LoadApplications extends AsyncTask<Void, Void, Void>
    {
    private ProgressDialog progress = null;

        @Override
        protected Void doInBackground(Void... params) {
            applist = checkForLaunchIntent(packageManager.getInstalledApplications(PackageManager.GET_META_DATA));

        listadapter = new AppAdapter(AppListActivity.this, R.layout.list_item, applist);
            return null;
    }

        @Override
        protected void onPostExecute(Void result)
        {
            setListAdapter(listadapter);
            progress.dismiss();
            super.onPostExecute(result);
        }

        @Override
        protected void onPreExecute()
        {
            progress = ProgressDialog.show(AppListActivity.this, null, "Loading apps info..." );
                    super.onPreExecute();
        }
    }
}
