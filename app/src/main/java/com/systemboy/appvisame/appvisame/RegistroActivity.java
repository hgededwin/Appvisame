package com.systemboy.appvisame.appvisame;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class RegistroActivity extends ActionBarActivity {

    String [] Areas;
    Spinner spAreas;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        spAreas = (Spinner)findViewById(R.id.sp_areas);
        Areas = getResources().getStringArray(R.array.array_areas);
        ArrayAdapter<String> AreasAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Areas);
        AreasAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spAreas.setAdapter(AreasAdapter);

        Toolbar toolbar = (Toolbar) findViewById(R.id.activity_my_toolbar);
        toolbar.setTitle(R.string.title_reporte);
        setSupportActionBar(toolbar);
    }
}
