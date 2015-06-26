package com.example.gutting.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by Gutting on 26.06.2015.
 */
public class MainTabActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_1_main);

        MainActivity main = new MainActivity();
        TextView textView = new TextView(this);
        textView.setText(main.on);
        setContentView(textView);
    }
}
