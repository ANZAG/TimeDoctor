package com.example.gutting.myapplication;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Gutting on 11.06.2015.
 */
public class AppAdapter extends ArrayAdapter<ApplicationInfo> {

    private List<ApplicationInfo> appList = null;
    private Context context;
    private PackageManager packageManager;

    // Konstruktor
    public AppAdapter(Context context, int resource,
                      List<ApplicationInfo> objects)
    {   //Aufruf des Konstruktors der Mutterklasse
        //�bergabe der Klasse (context)
        //�bergabe des Layouts (resource)
        //�bergabe der bef�llten Liste (objects)
        super(context, resource, objects);

        //Bef�llen der Instanzvariablen
        this.context = context;
        this.appList = objects;
        packageManager = context.getPackageManager();
    }

    /**
     *
     * @param position      Aktuelles Element der Liste
     * @param convertView   von ArrayAdapter gesetztes Layout
     * @param parent
     * @return
     *
     * Aufruf durch Mutterklasse ArrayAdapter
     *
     * Definieren des endg�ltigen Layouts der Liste
     * Aufruf findet f�r jedes Element der Liste statt
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View view = convertView;

        // Ist der View nicht instanziiert worden
        if(null == view){
            //Instanziiert ein neues XML File inklusive der AppListe innerhalb des momentanen View
            // d.h. es findet eine �berschreibung des durch die AppListActivity erzeugten Layouts statt
            // in unserem Fall durch das Layout.XML file "list_item"
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.list_item, null);
        }
        //Aktuelles Element der Liste aufrufen
        ApplicationInfo data = appList.get(position);

        // Wenn Element nicht leer
        if(null != data)
        {
            //Identifiziere den entsprechenden TextView innerhalb des Layouts
            TextView appName = (TextView) view.findViewById(R.id.app_name);
            TextView packageName = (TextView) view.findViewById(R.id.app_package);
            ImageView iconView = (ImageView) view.findViewById(R.id.app_icon);

            //Bef�llung des entsprechenden TextViews
            appName.setText(data.loadLabel(packageManager));
            packageName.setText(data.packageName);
            iconView.setImageDrawable(data.loadIcon(packageManager));
        }
            //R�ckgabe des neu erzeugten Layouts
            return view;
    }
}

