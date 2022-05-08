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
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {
DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://projetandroid-1bdd7-default-rtdb.firebaseio.com/");
    EditText FullName,Phone,Email,Password;
    private FirebaseAuth mAuth;

@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_screen);
        mAuth = FirebaseAuth.getInstance();
         FullName = (EditText) findViewById(R.id.et_FullName);
         Phone = (EditText) findViewById(R.id.et_phone);
         Email = (EditText) findViewById(R.id.et_Email);
         Password = (EditText) findViewById(R.id.et_Password);
        Button register = (Button) findViewById(R.id.btn_register);
        TextView login = (TextView) findViewById(R.id.et_login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent x = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(x);
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             createAccount();

                }

        });

    }
    private void createAccount(){
        final String fullName = FullName.getText().toString().trim();
        final String phone = Phone.getText().toString().trim();
        final String email = Email.getText().toString().trim();
        final String password = Password.getText().toString().trim();
        if(fullName.isEmpty()){
            FullName.setError("Full name is required");
            FullName.requestFocus();
            return;
        }
        if(phone.isEmpty()){
            Phone.setError("Phone is required");
            Phone.requestFocus();
            return;
        }
        if(email.isEmpty()){
            Email.setError("Email is required");
            Email.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            Email.setError("Please provide valid email");
            Email.requestFocus();
            return;

        }
        if(password.isEmpty()){
            Password.setError("Password is required");
            Password.requestFocus();
            return;
        }
        if(password.length() <6 ){
            Password.setError("Min password length should be 6 characters !");
            Password.requestFocus();
            return;
        }
        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    User user = new User(fullName,phone,email,password);
                    FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Toast.makeText(RegisterActivity.this,"User has been registered successfully !",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(RegisterActivity.this,MainActivity.class);
                            startActivity(intent);
                        }
                    });
                }else{
                    Toast.makeText(RegisterActivity.this,"Try again !",Toast.LENGTH_SHORT).show();
                }

            }
        });



    }


}

