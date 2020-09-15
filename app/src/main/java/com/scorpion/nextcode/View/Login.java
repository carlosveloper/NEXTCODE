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
import android.widget.TextView;

import com.scorpion.nextcode.MainActivity;
import com.scorpion.nextcode.R;
import com.scorpion.nextcode.ViewModel.ViewModelLogin;

public class Login extends AppCompatActivity {

    private EditText user;
    private EditText password;
    private Button ingresar;
    private ViewModelLogin viewModel;
    private ConstraintLayout ContainerLogin;
    private View layoutLoading;
    private TextView registrase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        UI();
        observer();
    }
    private void UI() {
        user = findViewById(R.id.EditLoginUser);
        password = findViewById(R.id.EditLoginPass);
        ingresar = findViewById(R.id.ButtonLoginIngresar);

        registrase = findViewById(R.id.registrarme_login);

        layoutLoading = findViewById(R.id.layoutLoading);
        ContainerLogin = findViewById(R.id.ContainerLogin);
        viewModel = ViewModelProviders.of(this).get(ViewModelLogin.class);


        ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("Click", "login");
                if (view != null) {
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
                viewModel.onClickLogin(user.getText().toString(), password.getText().toString());
            }
        });


        registrase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToRegister();
            }
        });



    }



    private void observer() {

        final Observer<String> observerEmail = new Observer<String>() {
            @Override
            public void onChanged(String s) {
                user.setError(s);
            }
        };
        final Observer<String> observerPassword = new Observer<String>() {
            @Override
            public void onChanged(String s) {
                password.setError(s);
            }
        };

        final Observer<Boolean> observerLoading = new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean result) {

                if(result){
                    layoutLoading.setVisibility(View.VISIBLE);
                    ContainerLogin.setVisibility(View.GONE);
                }else{
                    layoutLoading.setVisibility(View.GONE);
                    ContainerLogin.setVisibility(View.VISIBLE);

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
        viewModel.getIsViewLoading().observe(this, observerLoading);

        viewModel.getintentHome().observe(this, observerHome);

    }

    private void goToSignUp() {
        Intent i = new Intent(Login.this, MainActivity.class);
        startActivity(i);
        finish();
    }

    private void goToRegister() {
        Intent i = new Intent(Login.this, RegistroUser.class);
        startActivity(i);
        finish();
    }

}