package com.example.gutting.myapplication;

import android.graphics.drawable.Drawable;
import android.util.Log;

/**
 *
 */
public class App implements Comparable<App> {

    private CharSequence name;
    private String pfad;
    private Drawable icon;

    private int timeMax = 0;
    private int hours = 0;

    public int getTimeMax() {

        timeMax = 0;
        timeMax = timeMax + hours * 3600;
        timeMax = timeMax + minutes * 60;
        timeMax = timeMax + secounds;
        return timeMax;
    }

    public int getHours() {
        return hours;
    }

    public int getSecounds() {
        return secounds;
    }

    public int getMinutes() {
        return minutes;
    }

    private int minutes = 0;
    private int secounds = 0;

    private String timeString = "Verbrauchte Zeit: " + hours + "h:" + minutes + "m:" + secounds +"s";

    public void setTime(){
        //Ist der Pfad gleich:
        secounds++;
        // Wenn 60 Sekunden
        if(secounds > 59) {
            // Minuten hochzaehlen, 60 Sekunden abziehen
            minutes++;
            secounds = 0;
        }
        // Wenn Minuten = 60
        if(minutes > 59 ){
            // Stunden hochzaehlen, Minuten abziehen
            hours++;
            minutes = 0;
        }

        timeString = "Verbrauchte Zeit: " + hours + "h:" + minutes + "m:" + secounds +"s";
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

    public String getTimeString() {
        return timeString;
    }

    // Test Balkendiagramm

    @Override
    public int compareTo(App app) {
        return Integer.toString(this.timeMax).compareTo(Integer.toString(app.getTimeMax()));
    }


}
