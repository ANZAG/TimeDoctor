package com.example.gutting.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */


public class Tab3 extends Fragment {

    protected List<App> apps;



    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tab_3,container,false);

        //Aufrufen der momentanen Liste
        apps = MainActivity.getApps();

        // Anpassen an den Wert der Auflösung
        float density = getResources().getDisplayMetrics().density;

        for(int i = 0; i < 5; i++)
        {
            //Übergabe des aktuellen Zeitwert und im Verhältnis 0,5 setzen
            int time = (int) (apps.get(i).getTimeMax()* 0.5);
            switch (i){
                case 0:
                    //Zuordnung des Logo 1
                    ImageView bar_logo_1 = (ImageView) v.findViewById(R.id.bar_logo_1);
                    bar_logo_1.setImageDrawable(apps.get(i).getIcon());

                    // Zuordnung Balken 1
                    RelativeLayout bar_1 = (RelativeLayout) v.findViewById(R.id.bar_1);
                    //Übergabe des aktuellen Zeitwert und im Verhältnis 0,5 setzen

                    //Pixelanzahl des Balkens entsprechend der Time Variable anpassen
                    bar_1.getLayoutParams().height = time;

                case 1:
                    //Zuordnung des Logo 2
                    ImageView bar_logo_2 = (ImageView) v.findViewById(R.id.bar_logo_2);
                    bar_logo_2.setImageDrawable(apps.get(i).getIcon());

                    // Zuordnung Balken 1
                    RelativeLayout bar_2 = (RelativeLayout) v.findViewById(R.id.bar_2);
                    //Pixelanzahl des Balkens entsprechend der Time Variable anpassen
                    bar_2.getLayoutParams().height = time;
                case 2:
                    ImageView bar_logo_3 = (ImageView) v.findViewById(R.id.bar_logo_3);
                    bar_logo_3.setImageDrawable(apps.get(i).getIcon());

                    // Zuordnung Balken 1
                    RelativeLayout bar_3 = (RelativeLayout) v.findViewById(R.id.bar_3);
                    //Pixelanzahl des Balkens entsprechend der Time Variable anpassen
                    bar_3.getLayoutParams().height = time;
                case 3:
                    ImageView bar_logo_4 = (ImageView) v.findViewById(R.id.bar_logo_4);
                    bar_logo_4.setImageDrawable(apps.get(i).getIcon());

                    // Zuordnung Balken 1
                    RelativeLayout bar_4 = (RelativeLayout) v.findViewById(R.id.bar_4);
                    //Pixelanzahl des Balkens entsprechend der Time Variable anpassen
                    bar_4.getLayoutParams().height = time;
                case 4:
                    ImageView bar_logo_5 = (ImageView) v.findViewById(R.id.bar_logo_5);
                    bar_logo_5.setImageDrawable(apps.get(i).getIcon());

                    // Zuordnung Balken 1
                    RelativeLayout bar_5 = (RelativeLayout) v.findViewById(R.id.bar_5);
                    //Pixelanzahl des Balkens entsprechend der Time Variable anpassen
                    bar_5.getLayoutParams().height = time;
            }

        }



        return v;



    }




}