package com.systemboy.appvisame.appvisame;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;

import com.pkmmte.view.CircularImageView;


public class LoginActivity extends ActionBarActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        CircularImageView circularImageView = (CircularImageView)findViewById(R.id.image_circular);
        circularImageView.setBorderColor(getResources().getColor(R.color.accent_color));
        circularImageView.setBorderWidth(4);
        circularImageView.addShadow();
    }
}
