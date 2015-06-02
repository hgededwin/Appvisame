package com.systemboy.appvisame.appvisame;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewManager;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.gc.materialdesign.views.ProgressBarCircularIndeterminate;
import com.getbase.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MainActivity extends ActionBarActivity {

    List<Usuario> lista;
    UsuarioAdapter mAdapter;
    RecyclerView recList;
    private Singleton volley;
    private JSONArray Arrayjson;
    protected RequestQueue fRequestQueue;
    ProgressBarCircularIndeterminate indeterminate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(Build.VERSION.SDK_INT < 19 || Build.VERSION.SDK_INT >= 22){
            FrameLayout statusBar = (FrameLayout) findViewById(R.id.statusBar);
            ViewGroup.LayoutParams layoutParams = statusBar.getLayoutParams();
            layoutParams.height = 0;
        }

        indeterminate = (ProgressBarCircularIndeterminate)findViewById(R.id.progressBarCircularIndeterminate2);
        volley = Singleton.getInstance(this.getApplicationContext());
        fRequestQueue = volley.getRequestQueue();



        final RecyclerView recList = (RecyclerView)findViewById(R.id.cardList);
        recList.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recList.setLayoutManager(llm);
        makeRequest();


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

//    UsuarioAdapter mAdapter = new UsuarioAdapter(lista);
//    recList.setAdapter(mAdapter);

    private void makeRequest() {

        lista = new ArrayList<>();
        RequestQueue queue = Volley.newRequestQueue(this.getApplicationContext());
        String url ="http://appvisame.hol.es/appvisame.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Arrayjson = new JSONArray(response);
                            llenarTarjetas(Arrayjson);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        System.out.println(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("-"+error);
            }
        }){
        };
// Add the request to the RequestQueue.
        queue.add(stringRequest);

    }

    public void llenarTarjetas(JSONArray noticias){
        lista = new ArrayList<>();
        for (int i = 0; i < noticias.length();i++){
            try {
                JSONObject jsonObject = noticias.getJSONObject(i);
                String titulo_card = jsonObject.getString("titulo");
                String estado_card = jsonObject.getString("estado");
                String fecha_card = jsonObject.getString("fecha");
                String urgencia_card = jsonObject.getString("urgencia");

                lista.add(new Usuario(titulo_card,estado_card, fecha_card, urgencia_card));

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
         mAdapter = new UsuarioAdapter(lista);
        recList.setAdapter(mAdapter);
        onConnectionFinished();

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

    public void addToQueue(Request request){
        if(request != null){
            request.setTag(this);
            if(fRequestQueue == null){
                fRequestQueue = volley.getRequestQueue();
                request.setRetryPolicy(new DefaultRetryPolicy(6000, 3, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                onPreStartConnection();
                fRequestQueue.add(request);
            }
        }
    }

    public void onPreStartConnection(){


    }

    public void onConnectionFinished(){

        ((ViewManager)indeterminate.getParent()).removeView(indeterminate);

    }

    public void onConnectionFailed(){


        Toast.makeText(getApplicationContext(), "Error al cargar la sección de pendientes", Toast.LENGTH_LONG);

    }
}