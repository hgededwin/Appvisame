package com.systemboy.appvisame.activity.about;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.systemboy.appvisame.appvisame.R;


public class DetailsActivity extends ActionBarActivity {

    // Before the onCreate
    public final static String ID = "ID";
    public Integrantes mContact;
    TextView mName;
    TextView mPhone;
    TextView mMail;
    TextView mCity;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_acercade);

        mContact = Integrantes.getItem(getIntent().getIntExtra(ID, 0));

        mName = (TextView) findViewById(R.id.DETAILS_name);
        mName.setText(mContact.get(Integrantes.Field.NAME));

        mPhone = (TextView)findViewById(R.id.DETAILS_phone);
        mPhone.setText(mContact.get(Integrantes.Field.PHONE));

        mMail = (TextView)findViewById(R.id.DETAILS_email);
        mMail.setText(mContact.get(Integrantes.Field.EMAIL));

        mCity = (TextView)findViewById(R.id.DETAILS_city);
        mCity.setText(mContact.get(Integrantes.Field.CITY));






    }
}
