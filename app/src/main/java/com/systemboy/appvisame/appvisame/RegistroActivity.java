package com.systemboy.appvisame.appvisame;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;



public class RegistroActivity extends ActionBarActivity {

    String [] Areas;
    Spinner spAreas;
    ImageButton btnsatusNormal, btnStatusImportante, btnStatusUrgente;
    Button btnelegir;
    Button btnEnviarReporte;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        btnEnviarReporte = (Button)findViewById(R.id.btnSendReport);
        btnsatusNormal = (ImageButton)findViewById(R.id.image_status_normal);
        btnStatusImportante = (ImageButton)findViewById(R.id.image_status_importante);
        btnStatusUrgente = (ImageButton)findViewById(R.id.image_status_urgente);
        btnelegir = (Button)findViewById(R.id.btn_elegir);
        btnelegir.setVisibility(View.GONE);
        spAreas = (Spinner)findViewById(R.id.sp_areas);
        Areas = getResources().getStringArray(R.array.array_areas);
        ArrayAdapter<String> AreasAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Areas);
        AreasAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spAreas.setAdapter(AreasAdapter);

        Toolbar toolbar = (Toolbar) findViewById(R.id.activity_my_toolbar);
        toolbar.setTitle(R.string.title_reporte);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        btnsatusNormal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnStatusImportante.setVisibility(View.GONE);
                btnStatusUrgente.setVisibility(View.GONE);
                btnelegir.setVisibility(View.VISIBLE);
                Toast.makeText(getApplicationContext(), "Prioridad: Normal", Toast.LENGTH_LONG).show();
            }
        });

        btnStatusImportante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnsatusNormal.setVisibility(View.GONE);
                btnStatusUrgente.setVisibility(View.GONE);
                btnelegir.setVisibility(View.VISIBLE);
                Toast.makeText(getApplicationContext(), "Prioridad: Importante", Toast.LENGTH_LONG).show();
            }
        });

        btnStatusUrgente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnsatusNormal.setVisibility(View.GONE);
                btnStatusImportante.setVisibility(View.GONE);
                btnelegir.setVisibility(View.VISIBLE);
                Toast.makeText(getApplicationContext(), "Prioridad: Urgente", Toast.LENGTH_LONG).show();
            }
        });

        btnelegir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnsatusNormal.setVisibility(View.VISIBLE);
                btnStatusImportante.setVisibility(View.VISIBLE);
                btnStatusUrgente.setVisibility(View.VISIBLE);
                btnelegir.setVisibility(View.GONE);
            }
        });

        btnEnviarReporte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegistroActivity.this, MainActivity.class);
                startActivity(intent);

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem)
    {
        onBackPressed();
        return true;
    }
}
