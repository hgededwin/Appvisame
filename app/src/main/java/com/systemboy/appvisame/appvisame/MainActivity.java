package com.systemboy.appvisame.appvisame;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewManager;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
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
import java.util.List;


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

        Toolbar toolbar = (Toolbar) findViewById(R.id.activity_my_toolbar);
        toolbar.setTitle(R.string.app_name);
        setSupportActionBar(toolbar);

        indeterminate = (ProgressBarCircularIndeterminate)findViewById(R.id.progressBarCircularIndeterminate2);
        volley = Singleton.getInstance(this);
        fRequestQueue = volley.getRequestQueue();



       recList = (RecyclerView)findViewById(R.id.cardList);
        recList.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recList.setLayoutManager(llm);
        makeRequest();


         /**/

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
        String url ="http://appvisame.hol.es/registrar.php";
        StringRequest stringRequest = new StringRequest(url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Arrayjson = new JSONArray(response);

                            llenarTarjetas(Arrayjson);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

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

                new MaterialDialog.Builder(this)
                        .title(R.string.txt_config)
                        .titleColorRes(R.color.primary_dark_color)
                        .content("¿Deseas cerrar tu sesión?")
                        .positiveText(R.string.txt_cerrar_sesion)
                        .negativeText(R.string.txt_cancelar)
                        .negativeColorRes(R.color.black_color)
                        .positiveColorRes(R.color.primary_dark_color)
                        .callback(new MaterialDialog.ButtonCallback() {

                            @Override
                            public void onPositive(MaterialDialog mdialog) {

                                SharedPreferences settings = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = settings.edit();
                                editor.putString("name", "");
                                editor.putString("pass", "");
                                editor.putString("nameroot", "");
                                editor.putString("passroot", "");
                                editor.commit();

                                final ProgressDialog dialogo = ProgressDialog.show(MainActivity.this, getString(R.string.txt_progress_cerrando), getString(R.string.txt_progress_espere));
                                new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        try{
                                            Thread.sleep(3000);
                                            dialogo.dismiss();
                                            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                                            startActivity(intent);
                                            finish();

                                        }catch (InterruptedException e){
                                            e.printStackTrace();
                                        }
                                    }
                                }).start();
                            }
                        })
                        .show();
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

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {

            new MaterialDialog.Builder(this)
                    .title(getString(R.string.txt_desea_salir))
                    .content(getString(R.string.txt_estas_seguro))
                    .titleColorRes(R.color.primary_dark_color)
                    .positiveText(getString(R.string.txt_salir))
                    .negativeColorRes(R.color.black_color)
                    .positiveColorRes(R.color.primary_dark_color)
                    .negativeText(getString(R.string.txt_cancelar))
                    .callback(new MaterialDialog.ButtonCallback() {

                        @Override
                        public void onPositive(MaterialDialog mdialog) {
                            MainActivity.this.finish();
                        }
                    })
                    .show();
        }
        return false;
    }
}