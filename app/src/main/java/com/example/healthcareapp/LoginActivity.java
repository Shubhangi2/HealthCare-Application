package com.example.healthcareapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText userName, password;
    Button btn;
    TextView newUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userName = findViewById(R.id.loginUserName);
        password = findViewById(R.id.loginPwd);
        btn = findViewById(R.id.loginBtn);
        newUser = findViewById(R.id.new_user);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = userName.getText().toString();
                String pwd = password.getText().toString();
                Database db = new Database(getApplicationContext(),"HealthCare", null, 1);


                if(name.length()==0 || pwd.length()==0){
                    Toast.makeText(getApplicationContext(), "Please fill the login details", Toast.LENGTH_SHORT).show();
                }else{
                    if(db.login(name, pwd)==1){
                        Toast.makeText(getApplicationContext(), "Login successfully", Toast.LENGTH_SHORT).show();
                        SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("username", name);
                        editor.apply();
                        startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                        finish();
                    }else{
                        Toast.makeText(getApplicationContext(), "Incorrect username or password", Toast.LENGTH_SHORT).show();

                    }
                }

            }
        });

        newUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                finish();
            }
        });
    }
}