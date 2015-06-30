package com.example.gutting.myapplication;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class AppListActivity extends Activity {

    protected List<App> apps;

    @Override
    protected void onCreate(Bundle savedInstanceState)
        {

            super.onCreate(savedInstanceState);
            setContentView(R.layout.layout_2_apps);

            apps = MainActivity.getApps();

            loadListView();
            addClickListener();
        }

    private ListView list;
    public TextView appTime;

    public void loadListView(){
        list = (ListView)findViewById(R.id.list);

        Log.i("[LoadListView]", "Bin drinnen!");

        ArrayAdapter<App> adapter = new ArrayAdapter<App>(this, R.layout.list_item, apps) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if(convertView == null){
                    convertView = getLayoutInflater().inflate(R.layout.list_item, null);
                }

                ImageView appIcon = (ImageView)convertView.findViewById(R.id.app_icon);
                appIcon.setImageDrawable(apps.get(position).getIcon());

                TextView appName = (TextView)convertView.findViewById(R.id.app_name);
                appName.setText(apps.get(position).getName());

                TextView appTime = (TextView)convertView.findViewById(R.id.app_time);
                appTime.setText(apps.get(position).getTimeString());

                return convertView;
            }
        };

        list.setAdapter(adapter);
    }

    private void addClickListener(){
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> av, View v, int pos, long id) {
                // TODO Klickbares Event??
                loadListView();
                Log.i("[KLICK]", "gecklickt");
            }
        });
    }


}
