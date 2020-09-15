package com.scorpion.nextcode.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.scorpion.nextcode.MainActivity;
import com.scorpion.nextcode.R;
import com.scorpion.nextcode.ViewModel.ViewModelLogin;
import com.scorpion.nextcode.ViewModel.ViewModelRegistrarUser;

public class RegistroUser extends AppCompatActivity {

    private EditText email,password,nombres,apellidos,cedula;
    private ViewModelRegistrarUser viewModel;
     Button ButtonRegistro;
     TextView ir_login;
     private RelativeLayout ContainerRegistro;
    private View layoutLoading;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_user);
        UI();
        observer();

    }


    private void UI() {
        email = findViewById(R.id.registro_email);
        password = findViewById(R.id.registro_pass);
        nombres = findViewById(R.id.registro_nombre);
        ContainerRegistro=findViewById(R.id.ContainerRegistro);
        apellidos = findViewById(R.id.registro_apellido);
        cedula = findViewById(R.id.registro_cedula);
        ir_login= findViewById(R.id.ir_login);
        layoutLoading = findViewById(R.id.layoutLoading);
        ButtonRegistro= findViewById(R.id.ButtonRegistro);
        viewModel = ViewModelProviders.of(this).get(ViewModelRegistrarUser.class);


        ButtonRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("Click", "login");
                if (view != null) {
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
                viewModel.onClickRegistro(email.getText().toString(), password.getText().toString(),nombres.getText().toString(),apellidos.getText().toString(),cedula.getText().toString());
            }
        });

        ir_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToLogin();
            }
        });



    }




    private void observer() {
        final Observer<String> observerEmail = new Observer<String>() {
            @Override
            public void onChanged(String s) {
                email.setError(s);
            }
        };
        final Observer<String> observerPassword = new Observer<String>() {
            @Override
            public void onChanged(String s) {
                password.setError(s);
            }
        };

        final Observer<String> observerNombres = new Observer<String>() {
            @Override
            public void onChanged(String s) {
                nombres.setError(s);
            }
        };

        final Observer<String> observerApellidos = new Observer<String>() {
            @Override
            public void onChanged(String s) {
                apellidos.setError(s);
            }
        };


        final Observer<String> observerCedula = new Observer<String>() {
            @Override
            public void onChanged(String s) {
                cedula.setError(s);
            }
        };





        final Observer<Boolean> observerLoading = new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean result) {

                if(result){
                    layoutLoading.setVisibility(View.VISIBLE);
                    ContainerRegistro.setVisibility(View.GONE);
                }else{
                    layoutLoading.setVisibility(View.GONE);
                    ContainerRegistro.setVisibility(View.VISIBLE);
                }

            }
        };


        final Observer<Boolean> observerHome = new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean result) {
                if(result){
                    goToSignUp();
                }else{

                }

            }
        };

        viewModel.getEmail().observe(this, observerEmail);
        viewModel.getPassword().observe(this, observerPassword);


        viewModel.getApellidos().observe(this, observerApellidos);
        viewModel.getNombres().observe(this, observerNombres);

        viewModel.getCedula().observe(this, observerCedula);

        viewModel.getIsViewLoading().observe(this, observerLoading);
        viewModel.getintentHome().observe(this, observerHome);

    }





    private void goToSignUp() {
        Intent i = new Intent(RegistroUser.this, MainActivity.class);
        startActivity(i);
        finish();
    }

    private void goToLogin() {
        Intent i = new Intent(RegistroUser.this, Login.class);
        startActivity(i);
        finish();
    }

}