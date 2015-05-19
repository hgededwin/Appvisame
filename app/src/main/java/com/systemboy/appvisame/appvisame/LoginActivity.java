package com.systemboy.appvisame.appvisame;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import com.pkmmte.view.CircularImageView;


public class LoginActivity extends ActionBarActivity {

    Button btninicio;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if(Build.VERSION.SDK_INT < 19 || Build.VERSION.SDK_INT >= 22){
            FrameLayout statusBar = (FrameLayout) findViewById(R.id.statusBar);
            ViewGroup.LayoutParams layoutParams = statusBar.getLayoutParams();
            layoutParams.height = 0;
        }

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
