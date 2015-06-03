package com.systemboy.appvisame.appvisame;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import com.pkmmte.view.CircularImageView;


public class LoginActivity extends ActionBarActivity {

    Button btnLogin;
    static String NAME = "ehernndez";
    static String PASS = "edwindaniel123";
    EditText edtxtUser;
    EditText edtxtPass;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if(Build.VERSION.SDK_INT < 19 || Build.VERSION.SDK_INT >= 22){
            FrameLayout statusBar = (FrameLayout) findViewById(R.id.statusBar);
            ViewGroup.LayoutParams layoutParams = statusBar.getLayoutParams();
            layoutParams.height = 0;
        }

        edtxtUser = (EditText)findViewById(R.id.edtxtUsuario);
        edtxtPass = (EditText)findViewById(R.id.edtxtPassw);
        btnLogin = (Button)findViewById(R.id.btnIniciar);
        SharedPreferences prefs = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
        String name = prefs.getString("name", "");
        String pass = prefs.getString("pass", "");

        if(name.equals(NAME) && pass.equals(PASS)){

            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

        CircularImageView circularImageView = (CircularImageView)findViewById(R.id.image_circular);
        circularImageView.setBorderColor(getResources().getColor(R.color.accent_color));
        circularImageView.setBorderWidth(4);
        circularImageView.addShadow();


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
