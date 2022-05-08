package com.example.miniprojetandroid;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    EditText EmailEt,PasswordEt;
    Button loginBtn;
    TextView signUp;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);
        mAuth = FirebaseAuth.getInstance();
        EmailEt = (EditText) findViewById(R.id.et_email);
        PasswordEt = (EditText) findViewById(R.id.et_password);
        loginBtn = (Button) findViewById(R.id.btn_login);
        signUp = (TextView) findViewById(R.id.et_signUp);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent x = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(x);
            }
        });
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });

    }

    private void login() {
        final String email = EmailEt.getText().toString().trim();
        final String password = PasswordEt.getText().toString().trim();
        boolean isValid = true;

            if (email.isEmpty()) {
                EmailEt.setError("Email is required");
                EmailEt.requestFocus();
                return;
            }
            if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                EmailEt.setError("Please enter a valid email");
                EmailEt.requestFocus();
                return;

            }
        if (password.isEmpty()) {
            PasswordEt.setError("Password is requires");
            PasswordEt.requestFocus();
            return;
        }
        if (password.length() < 8) {
            PasswordEt.setError("Password must be at least 8 chars long!");
            PasswordEt.requestFocus();
            return;
            }

       mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
           @Override
           public void onComplete(@NonNull Task<AuthResult> task) {
               if(task.isSuccessful()){
                   Toast.makeText(LoginActivity.this,"Login !",Toast.LENGTH_SHORT).show();

                   Intent intent = new Intent(LoginActivity.this,MenuActivity.class);
                   startActivity(intent);
               }else{
                   Toast.makeText(LoginActivity.this,"Failed to login ! Please check your credentials !",Toast.LENGTH_SHORT).show();
               }

           }
       });
    }
}
