package com.example.gutting.myapplication;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Gutting.
 */
public class Tab1 extends Fragment {

    public static TextView screenCheck;
    public static TextView timeStampHours;
    public static TextView timeStampMinutes;
    public static TextView timeStampSeconds;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tab_1,container,false);

        // Initialisieren der TextView Felder
        screenCheck = (TextView) v.findViewById(R.id.tv_counter);
        timeStampHours = (TextView) v.findViewById(R.id.tv_hours);
        timeStampMinutes = (TextView) v.findViewById(R.id.tv_minutes);
        timeStampSeconds = (TextView) v.findViewById(R.id.tv_seconds);

        return v;
    }

}