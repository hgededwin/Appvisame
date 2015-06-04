package com.systemboy.appvisame.appvisame;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class RegistroActivity extends ActionBarActivity {

    Usuario persona;
    Reporte reporte;
    String [] Areas;
    Spinner spAreas;
    String prioridad;
    String fecha;
    ImageButton btnsatusNormal, btnStatusImportante, btnStatusUrgente;
    Button btnEnviarReporte;
    Button btnelegir;
    EditText edTitulo, edDescripcion;
    int area;
    private Singleton volley;
    protected RequestQueue fRequestQueue;
    JSONObject jsonObject;



    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);


        if(Build.VERSION.SDK_INT < 19 || Build.VERSION.SDK_INT >= 22){
            FrameLayout statusBar = (FrameLayout) findViewById(R.id.statusBar);
            ViewGroup.LayoutParams layoutParams = statusBar.getLayoutParams();
            layoutParams.height = 0;
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.activity_my_toolbar);
        toolbar.setTitle(R.string.title_reporte);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        final Calendar c = Calendar.getInstance();
        int anio = c.get(Calendar.YEAR); //obtenemos el año
        int mes = c.get(Calendar.MONTH); //obtenemos el mes
        mes = mes + 1;
        int dia = c.get(Calendar.DAY_OF_MONTH); // obtemos el día.
        fecha = anio + "-" + mes + "-" + dia;
        System.out.println("#########################->"+fecha);


        volley = Singleton.getInstance(this);
        fRequestQueue = volley.getRequestQueue();

        btnEnviarReporte = (Button)findViewById(R.id.btnSendReport);

        reporte= new Reporte();
      //  persona=new Usuario();

        btnsatusNormal = (ImageButton)findViewById(R.id.image_status_normal);
        btnStatusImportante = (ImageButton)findViewById(R.id.image_status_importante);
        btnStatusUrgente = (ImageButton)findViewById(R.id.image_status_urgente);
        btnelegir = (Button)findViewById(R.id.btn_elegir);
        btnelegir.setVisibility(View.GONE);
        edTitulo = (EditText) findViewById(R.id.edtxt_titulo);
        edDescripcion = (EditText) findViewById(R.id.edDescripcion);


        spAreas = (Spinner)findViewById(R.id.sp_areas);
        Areas = getResources().getStringArray(R.array.array_areas);
        ArrayAdapter<String> AreasAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Areas);
        AreasAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spAreas.setAdapter(AreasAdapter);
        spAreas.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        area = position;
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                }
        );



        btnsatusNormal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnStatusImportante.setVisibility(View.GONE);
                btnStatusUrgente.setVisibility(View.GONE);
                btnelegir.setVisibility(View.VISIBLE);
                prioridad = "1";
                btnsatusNormal.setEnabled(false);
                Toast.makeText(getApplicationContext(), "Prioridad: Normal", Toast.LENGTH_LONG).show();
            }
        });

        btnStatusImportante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnsatusNormal.setVisibility(View.GONE);
                btnStatusUrgente.setVisibility(View.GONE);
                btnelegir.setVisibility(View.VISIBLE);
                prioridad = "2";
                btnStatusImportante.setEnabled(false);
                Toast.makeText(getApplicationContext(), "Prioridad: Importante", Toast.LENGTH_LONG).show();
            }
        });

        btnStatusUrgente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnsatusNormal.setVisibility(View.GONE);
                btnStatusImportante.setVisibility(View.GONE);
                btnelegir.setVisibility(View.VISIBLE);
                prioridad = "3";
                btnStatusUrgente.setEnabled(false);
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
                btnsatusNormal.setEnabled(true);
                btnStatusImportante.setEnabled(true);
                btnStatusUrgente.setEnabled(true);
            }
        });


        btnEnviarReporte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if ("".equals(edTitulo.getText().toString())) {
                    edTitulo.setError(getString(R.string.txt_campo_vacio));
                } else if ("".equals(edDescripcion.getText().toString())) {
                    edDescripcion.setError(getString(R.string.txt_campo_vacio));
                }else if(prioridad == null) {
                    Toast.makeText(getApplicationContext(), "Debes seleccionar la urgencia del reporte.", Toast.LENGTH_SHORT).show();
                }else{

                final ProgressDialog dialogo = ProgressDialog.show(RegistroActivity.this, getString(R.string.txt_enviando), getString(R.string.txt_progress_espere));
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(5000);
                            dialogo.dismiss();

                            reporte.setArea(area+1);
                            reporte.setTitulo(edTitulo.getText().toString());
                            reporte.setDescripcion(edDescripcion.getText().toString());
                            reporte.setPrioridad(prioridad);
                            reporte.setFecha(fecha);
                            // persona.setName("Desconocido");
                            // persona.setEmail("hola@mundo.com");
                            enviarJSON(reporte);
                            makeRequest();

                            Intent intent = new Intent(RegistroActivity.this, MainActivity.class);
                            startActivity(intent);
                            RegistroActivity.this.finish();

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem)
    {
        onBackPressed();
        return true;
    }


    public String enviarJSON(Reporte problema){
       jsonObject = new JSONObject();
        try{
            jsonObject.put("area", problema.getArea());
            jsonObject.put("titulo",problema.getTitulo());
            jsonObject.put("descripcion",problema.getDescripcion());
            jsonObject.put("prioridad",""+prioridad);
            jsonObject.put("estado","1");
            jsonObject.put("fecha", problema.getFecha());
            jsonObject.put("usuario","1");
          //  jsonObject.put("usuario",registra.getName());
           // jsonObject.put("correo",registra.getEmail());
            System.out.println("<-"+jsonObject.toString()+"->");

            return jsonObject.toString();
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public void onConnectionFinished() {

        Intent intent = new Intent(RegistroActivity.this, MainActivity.class);
        startActivity(intent);
    }
    public void makeRequest(){
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://appvisame.hol.es/appvisame.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println("/////////////////////////->"+response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("-"+error);
            }
        }){
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("reporte", ""+jsonObject.toString());
                return params;
            }
        };
// Add the request to the RequestQueue.
        queue.add(stringRequest);
        onConnectionFinished();
    }

}