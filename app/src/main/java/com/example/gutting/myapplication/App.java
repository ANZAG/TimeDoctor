package com.example.gutting.myapplication;

import android.app.ListActivity;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.ImageView;

/**
 * Created by Gutting on 29.06.2015.
 */
public class App{

    private CharSequence name;
    private String pfad;
    private Drawable icon;

    public int hours = 0;
    public int minutes = 0;
    public int secounds = 0;

    public App(CharSequence name, String pfad, Drawable icon){
        this.name = name;
        this.pfad = pfad;
        this.icon = icon;
    }

    public void setTime(){
        Log.i("secounds", Integer.toString(secounds));
        //Ist der Pfad gleich:
        secounds++;
        // Wenn 60 Sekunden
        if(secounds > 59) {
            // Minuten hochzählen, 60 Sekunden abziehen
            minutes++;
            secounds = 0;
        }
        // Wenn Minuten = 60
        if(minutes > 59 ){
            // Stunden hochzählen, Minuten abziehen
            hours++;
            minutes = 0;
        }
    }

    public CharSequence getName() {
        return name;
    }

    public void setName(CharSequence name) {
        this.name = name;
    }

    public String getPfad() {
        return pfad;
    }

    public void setPfad(String pfad) {
        this.pfad = pfad;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public int getSecounds() {
        return secounds;
    }

    public void setSecounds(int secounds) {
        this.secounds = secounds;
    }
}
