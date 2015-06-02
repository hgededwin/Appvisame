package com.systemboy.appvisame.appvisame;


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
import org.json.JSONObject;

public class RegistroActivity extends ActionBarActivity {

    Usuario persona;
    Reporte reporte;
    String [] Areas;
    Spinner spAreas;
    String prioridad;
    ImageButton btnsatusNormal, btnStatusImportante, btnStatusUrgente;
    Button btnEnviarReporte;
    Button btnelegir;
    EditText edTitulo, edDescripcion;
    int area;


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
                prioridad = "Normal";
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
                prioridad = "Importante";
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
                prioridad = "Urgente";
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
                /*reporte.setArea(area+1);
                reporte.setTitulo(edTitulo.getText().toString());
                reporte.setDescripcion(edDescripcion.getText().toString());
                reporte.setPrioridad(prioridad);
                persona.setName("Desconocido");
                persona.setEmail("hola@mundo.com");
                enviarJSON(persona,reporte);*/

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


    public String enviarJSON(Usuario registra, Reporte problema){
        JSONObject jsonObject = new JSONObject();
        try{
            jsonObject.put("area", problema.getArea());
            jsonObject.put("titulo",problema.getTitulo());
            jsonObject.put("descripcion",problema.getDescripcion());
            jsonObject.put("prioridad",problema.getPrioridad());
          //  jsonObject.put("usuario",registra.getName());
           // jsonObject.put("correo",registra.getEmail());
            System.out.println("<-"+jsonObject.toString()+"->");
            return jsonObject.toString();
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
}