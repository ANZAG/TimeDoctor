package com.example.gutting.myapplication;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.media.Image;
import android.util.Log;
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
public class AppAdapter extends ArrayAdapter<App> {

    private List<App> appList = null;
    private Context context;
    private PackageManager packageManager;

    private TextView time = null;

    // Konstruktor
    public AppAdapter(Context context, int resource,
                      List<App> objects)
    {   //Aufruf des Konstruktors der Mutterklasse
        //Übergabe der Klasse (context)
        //Übergabe des Layouts (resource)
        //Übergabe der befüllten Liste (objects)
        super(context, resource, objects);

        //Befüllen der Instanzvariablen
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
     * Definieren des endgültigen Layouts der Liste
     * Aufruf findet für jedes Element der Liste statt
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View view = convertView;

        // Ist der View nicht instanziiert worden
        if(null == view){
            //Instanziiert ein neues XML File inklusive der AppListe innerhalb des momentanen View
            // d.h. es findet eine Überschreibung des durch die AppListActivity erzeugten Layouts statt
            // in unserem Fall durch das Layout.XML file "list_item"
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.list_item, null);
        }
        //Aktuelles Element der Liste aufrufen
        App data = appList.get(position);

        // Wenn Element nicht leer
        if(null != data)
        {
            //Identifiziere den entsprechenden TextView innerhalb des Layouts
            TextView appName = (TextView) view.findViewById(R.id.app_name);
            time = (TextView) view.findViewById(R.id.app_time);
            ImageView iconView = (ImageView) view.findViewById(R.id.app_icon);

            //Befüllung des entsprechenden TextViews
            appName.setText(data.getName());
            time.setText("Verbrauchte Zeit: 0h:0m:0s");
            //packageName.setText(data.packageName);
            iconView.setImageDrawable(data.getIcon());
        }
            //Rückgabe des neu erzeugten Layouts
            return view;
    }

    public void setTime(App app)
    {
//        time.setText("Verbrauchte Zeit: "
  //                  + app.getHours() + "h:"
    //                + app.getMinutes() + "m:"
      //              + app.getSecounds()+ "s");
        Log.i("Test hours", Integer.toString(app.getHours()));
        Log.i("Test minutes", Integer.toString(app.getMinutes()));
        Log.i("Test secounds", Integer.toString(app.getSecounds()));
    }

}

