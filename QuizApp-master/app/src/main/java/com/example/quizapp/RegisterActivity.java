package com.example.quizapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    EditText etUser;
    EditText etPass;
    Button btnRegister;
    EditText etRepass;
    TextView tvRegister;

    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etUser = (EditText) findViewById(R.id.etUserReg);
        etPass = (EditText) findViewById(R.id.etPassReg);
        btnRegister = (Button) findViewById(R.id.btnRegister);
        tvRegister = (TextView) findViewById(R.id.tvIniciar);
        etRepass = (EditText) findViewById(R.id.etPassReg2);

        db = new DBHelper(this);

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Login.class);
                startActivity(i);
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user = etUser.getText().toString();
                String password = etPass.getText().toString();
                String rePassword = etRepass.getText().toString();

                if (user.equals("") || password.equals("") || rePassword.equals("")){
                    Toast.makeText(RegisterActivity.this, "Alanlar tamamlanmadÄ±", Toast.LENGTH_SHORT).show();
                } else if (password.equals(rePassword)) {
                    Boolean checkuser = db.checkUsername(user);
                    if (checkuser == false){
                        Boolean insert = db.insertData(user, password);
                        if (insert == true){
                            Toast.makeText(RegisterActivity.this, "KayÄ±t baÅarÄ±lÄ±", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(getApplicationContext(), Login.class);
                            startActivity(i);
                        } else {
                            Toast.makeText(RegisterActivity.this, "KayÄ±t BaÅarÄ±sÄ±z", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(RegisterActivity.this, "KullanÄ±cÄ± Zaten var", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(RegisterActivity.this, "YanlÄ±Å Åifre", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}