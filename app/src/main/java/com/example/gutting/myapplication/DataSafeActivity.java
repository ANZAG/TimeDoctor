package com.example.gutting.myapplication;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Gutting on 06.06.2015.
 */
public class DataSafeActivity  extends Activity implements View.OnClickListener {

    private Button safe;
    private Button laden;
    private EditText eingabe;
    private TextView anzeige;

    // Variablen zum dauerhafen Speichern benötigt
    private SharedPreferences speicher;
    // editor wird benötigt zum Speichern
    private Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_4_tipps);

        // Klasse DataSafe wird aktiviert
        datasafe();
    }

    // Starte die Klasse datasafe (wird über MainActivity aufgerufen)
    public void datasafe()
    {
        safe = (Button) findViewById(R.id.tab4);
        laden = (Button) findViewById(R.id.tab5);
        eingabe = (EditText) findViewById(R.id.tab2);
        anzeige = (TextView) findViewById(R.id.tab3);

        safe.setOnClickListener(this);
        laden.setOnClickListener(this);

        // Einbauen einer permanenten Datenspeicherung (auch nach schließen der App)
        speicher = getApplicationContext().getSharedPreferences("Daten", 0);//0 bedeutet wieviel andere Klassen noch darauf zugreifen können, in diesem Fall also 0
                                                                            // prüft ob es eine shared preferences gibt mit dem Namen Daten
                                                                            // Eindeutig festgelegt, dass späterer Zugriff wieder möglich ist
        editor = speicher.edit(); // bietet die Editier möglichkeit der gespeicherten Dateien
    }

    @Override
    public void onClick(View e) {
        // Wenn Button Safe gedrückt wird
        if(e == safe)
        {
            textSpeichern(eingabe.getText().toString());
        }
        // Wenn Button Safe gedrückt wird
        if(e == laden)
        {
            textLaden();
        }
    }
    private void textSpeichern(String inhalt){
        if(inhalt != null)
        {
            editor.putString("Data1", inhalt);
            editor.commit();
            Toast.makeText(this, "Neuer Inhalt wurde hinzugeüfgt", Toast.LENGTH_LONG).show();
        }
    }

    private void textLaden(){
        if(speicher.getString("Data1",null) != null){ // WEnn nichts in Data 1, geht weiter zu zweitem Parameter null und geht dann nur weiter wenn ungleich 0
        anzeige.setText(speicher.getString("Data1",null));
        }
        else
        {
            Toast.makeText(this, "Kein Inhalt gespeichert", Toast.LENGTH_LONG).show();
        }
    }
}
