package com.example.gutting.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.RelativeLayout;

/**
 * Created by Gutting on 04.07.2015.
 */
public class Info extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_popup);



        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width * .8), (int) (height * .6));


    }
}
