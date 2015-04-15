package com.systemboy.appvisame.appvisame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.getbase.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {


    List<Usuario> lista;
    Usuario uno,dos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        /**/
        uno= new Usuario();
        uno.setName("Edwin");
        uno.setName("correo@dominiodeedwin.com");
        uno.setSurname("breve descrpcion");
        uno.setTitulo("Titulo Edwin");

        dos= new Usuario();
        dos.setName("Daniel");
        dos.setEmail("correo@dominiodedaniel.com");
        dos.setSurname("no se que sea esto");
        dos.setTitulo("Titulo Daniel");

        lista= new ArrayList<>();
        lista.add(uno);
        lista.add(dos);

        final RecyclerView recList = (RecyclerView) findViewById(R.id.cardList);
        recList.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recList.setLayoutManager(llm);
        UsuarioAdapter mAdapter = new UsuarioAdapter(lista);
        recList.setAdapter(mAdapter);
         /**/

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

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:

                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intent);
                break;
            case R.id.action_about:
                Intent intentAbout = new Intent(MainActivity.this, AcercaActivity.class);
                startActivity(intentAbout);
            default:
                break;
        }
        return false;
    }
}