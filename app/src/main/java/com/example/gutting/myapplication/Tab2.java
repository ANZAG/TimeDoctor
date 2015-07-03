package com.example.gutting.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Gutting on 03.07.2015.
 */
public class Tab2 extends ListFragment {

    protected List<App> apps;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tab_2,container,false);

        apps = MainActivity.getApps();

        loadListView();
        //addClickListener();

        return v;
    }

    private ListView list;
    public TextView appTime;

    public void loadListView(){

        ArrayAdapter<App> adapter = new ArrayAdapter<App>(getActivity(), R.layout.list_item, apps) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if(convertView == null){

                    convertView = getActivity().getLayoutInflater().inflate(R.layout.list_item, null);
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

        setListAdapter(adapter);
    }

    /*
    private void addClickListener(){
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> av, View v, int pos, long id) {
                // TODO Klickbares Event??
                //loadListView();
                Log.i("[KLICK]", "gecklickt");
            }
        });
    }*/





}