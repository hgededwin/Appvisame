package com.systemboy.appvisame.activity.about;

import android.annotation.TargetApi;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.systemboy.appvisame.appvisame.R;


public class DetailsActivity extends ActionBarActivity {

    // Before the onCreate
    public final static String ID = "ID";
    public Integrantes mContact;
    public View mCircle;
    TextView mName;
    TextView mPhone;
    TextView mMail;
    TextView mCity;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_acercade);

        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);




        Toolbar toolbar_transparent = (Toolbar) findViewById(R.id.activity_my_toolbar_transparent);
        toolbar_transparent.setTitle("Contacto");
        setSupportActionBar(toolbar_transparent);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);




        mContact = Integrantes.getItem(getIntent().getIntExtra(ID, 0));

        mCircle = findViewById(R.id.DETAILS_circle);


        mName = (TextView) findViewById(R.id.DETAILS_name);
        mName.setText(mContact.get(Integrantes.Field.NAME));

        mPhone = (TextView)findViewById(R.id.DETAILS_phone);
        mPhone.setText(mContact.get(Integrantes.Field.PHONE));

        mMail = (TextView)findViewById(R.id.DETAILS_email);
        mMail.setText(mContact.get(Integrantes.Field.EMAIL));

        mCity = (TextView)findViewById(R.id.DETAILS_city);
        mCity.setText(mContact.get(Integrantes.Field.CITY));

        GradientDrawable bgShape = (GradientDrawable) mCircle.getBackground();
        bgShape.setColor(Color.parseColor(mContact.get(Integrantes.Field.COLOR)));






    }
}
