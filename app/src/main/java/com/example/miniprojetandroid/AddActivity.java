package com.example.miniprojetandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class AddActivity extends AppCompatActivity {
    EditText name,specialite,phone,email,img,bac,moyBac;
    Button btnAdd,btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        img = (EditText)findViewById(R.id.etImageN);
        name = (EditText) findViewById(R.id.etNameN);
        specialite = (EditText) findViewById(R.id.etSpecialiteN);
        phone = (EditText)findViewById(R.id.etPhoneN);
        email = (EditText)findViewById(R.id.etEmailN);
        bac = (EditText) findViewById(R.id.etBacN);
        moyBac = (EditText) findViewById(R.id.etMoyBacN);

        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnBack = (Button) findViewById(R.id.btnBack);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertData();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    private void insertData(){
        Map<String,Object> map = new HashMap<>();
        map.put("baccalaureat",bac.getText().toString());
        map.put("email",email.getText().toString());
        map.put("image",img.getText().toString());
        map.put("moyenneBac",moyBac.getText().toString());
        map.put("name",name.getText().toString());
        map.put("phone",phone.getText().toString());
        map.put("specialite",specialite.getText().toString());
        FirebaseDatabase.getInstance().getReference().child("students").push()
                .setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(AddActivity.this," Data Inserted Successfully",Toast.LENGTH_SHORT).show();
                        finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure( Exception e) {
                        Toast.makeText(AddActivity.this," Error for Insert Data !",Toast.LENGTH_SHORT).show();
                    }
                });

    }
}