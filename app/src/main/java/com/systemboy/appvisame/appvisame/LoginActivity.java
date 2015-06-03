package com.systemboy.appvisame.appvisame;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.afollestad.materialdialogs.MaterialDialog;
import com.pkmmte.view.CircularImageView;


public class LoginActivity extends ActionBarActivity {

    Button btnLogin;
    static String NAME = "ehernndez";
    static String PASS = "edwindaniel123";
    static String NAMEROOT = "root";
    static String PASSROOT = "123";
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
        String nameroot = prefs.getString("nameroot", "");
        String passroot = prefs.getString("passroot", "");


        if(name.equals(NAME) && pass.equals(PASS)){

            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }else if(nameroot.equals(NAMEROOT) && passroot.equals(PASSROOT)){
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

                if ("".equals(edtxtUser.getText().toString())) {
                    edtxtUser.setError(getString(R.string.txt_campo_vacio));
                } else if ("".equals(edtxtPass.getText().toString())) {
                    edtxtPass.setError(getString(R.string.txt_campo_vacio));
                } else if (edtxtUser.getText().toString().equals(NAME) && edtxtPass.getText().toString().equals(PASS)) {

                    SharedPreferences settings = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = settings.edit();
                    editor.putString("name", edtxtUser.getText().toString());
                    editor.putString("pass", edtxtPass.getText().toString());
                    editor.commit();

                    final ProgressDialog dialogo = ProgressDialog.show(LoginActivity.this, getString(R.string.txt_progress_iniciando), getString(R.string.txt_progress_bienvenido));
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Thread.sleep(4000);
                                dialogo.dismiss();

                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();

                } else if (edtxtUser.getText().toString().equals(NAMEROOT) && edtxtPass.getText().toString().equals(PASSROOT)) {

                    SharedPreferences settings = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = settings.edit();
                    editor.putString("nameroot", edtxtUser.getText().toString());
                    editor.putString("passroot", edtxtPass.getText().toString());
                    editor.commit();

                    final ProgressDialog dialogo = ProgressDialog.show(LoginActivity.this, getString(R.string.txt_progress_iniciando), getString(R.string.txt_progress_bienvenido));
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Thread.sleep(4000);
                                dialogo.dismiss();

                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
                }
                else{
                edtxtUser.setError(getString(R.string.txt_user_incorrecto));
                edtxtPass.setError(getString(R.string.txt_pass_incorrecto));
                edtxtUser.setText("");
                edtxtPass.setText("");

                new MaterialDialog.Builder(v.getContext())
                        .title(R.string.txt_error)
                        .titleColorRes(R.color.primary_dark_color)
                        .content(R.string.txt_pass_usuario_incorrecto)
                        .positiveText(R.string.txt_entendido)
                        .positiveColorRes(R.color.primary_dark_color)
                        .build()
                        .show();
            }
        }
    });
        edtxtPass.setError(null);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event){

        if(keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0){

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
                            LoginActivity.this.finish();
                        }
                    })
                    .show();
        }
        return false;
    }
}
