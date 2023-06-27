package com.example.healthcareapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    EditText ETname, ETemail, ETpassowrd, ETconfirmPassword;
    Button btn;
    TextView existingUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ETname = findViewById(R.id.regUserName);
        ETemail = findViewById(R.id.regEmail);
        ETpassowrd = findViewById(R.id.regPwd);
        ETconfirmPassword = findViewById(R.id.regConfirmPwd);

        btn = findViewById(R.id.registerBtn);
        existingUser = findViewById(R.id.existing_user);

        Database db = new Database(getApplicationContext(),"HealthCare", null, 1);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = ETname.getText().toString();
                String email = ETemail.getText().toString();
                String pwd = ETpassowrd.getText().toString();
                String cpwd = ETconfirmPassword.getText().toString();

                if(name.length()==0 || email.length()==0 || pwd.length()==0 || cpwd.length()==0){
                    Toast.makeText(getApplicationContext(), "Fill all details", Toast.LENGTH_SHORT).show();
                }else{
                    if(!pwd.equals(cpwd)){
                        Toast.makeText(getApplicationContext(), "Password doesn't match", Toast.LENGTH_SHORT).show();
                    }else{
                        db.register(name, email, pwd);
                        Toast.makeText(getApplicationContext(), "Registered successfully", Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });

        existingUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                finish();
            }
        });
    }
}