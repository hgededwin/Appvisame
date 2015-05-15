package com.systemboy.appvisame.appvisame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;

import com.pkmmte.view.CircularImageView;


public class LoginActivity extends ActionBarActivity {

    Button btninicio;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        btninicio = (Button)findViewById(R.id.btnIniciar);

        CircularImageView circularImageView = (CircularImageView)findViewById(R.id.image_circular);
        circularImageView.setBorderColor(getResources().getColor(R.color.accent_color));
        circularImageView.setBorderWidth(4);
        circularImageView.addShadow();


        btninicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
