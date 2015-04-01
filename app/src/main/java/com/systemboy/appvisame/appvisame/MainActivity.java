package com.systemboy.appvisame.appvisame;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.getbase.floatingactionbutton.FloatingActionButton;


public class MainActivity extends ActionBarActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.activity_my_toolbar);
        toolbar.setTitle(R.string.app_name);
        setSupportActionBar(toolbar);


        FloatingActionButton btnNuevoReporte;
        btnNuevoReporte = (FloatingActionButton)findViewById(R.id.btnNewProblem);
        btnNuevoReporte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegistroActivity.class);
                startActivity(intent);
            }
        });
    }
}