package com.example.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    EditText etUser;
    EditText etPass;
    Button btngiris;
    TextView tvRegister;

    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUser = (EditText) findViewById(R.id.etUser);
        etPass = (EditText) findViewById(R.id.etPass);
        btngiris = (Button) findViewById(R.id.btngiris);
        tvRegister = (TextView) findViewById(R.id.registerlink);

        db = new DBHelper(this);

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),RegisterActivity.class);
                startActivity(i);
            }
        });

        btngiris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user = etUser.getText().toString();
                String password = etPass.getText().toString();

                if (user.equals("") || password.equals("")){
                    Toast.makeText(Login.this, "alanlar tamamlanmadı", Toast.LENGTH_SHORT).show();
                } else {
                    Boolean checkUserPass = db.checkUserPass(user, password);
                    if (checkUserPass == true){
                        Toast.makeText(Login.this, "Giriş Başarılı", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(Login.this, MainActivity.class);
                        startActivity(i);
                    } else {
                        Toast.makeText(Login.this, "Yanlış kullanıcı adı veya şifre", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
}
