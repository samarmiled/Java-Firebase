package com.example.miniprojetandroid;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.service.controls.actions.FloatAction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HomeFragment extends Fragment  {
    RecyclerView recyclerView;
    DatabaseReference database;
    MainAdapter mainAdapter;
    ArrayList<Students> list;
    adapter adapter;
    FloatingActionButton floatingActionButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view=  inflater.inflate(R.layout.homefragment,container,false);
        recyclerView = (RecyclerView) view.findViewById(R.id.rv);
        floatingActionButton = (FloatingActionButton)view.findViewById(R.id.floatingActionButton);
        database = FirebaseDatabase.getInstance().getReference().child("students");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        list = new ArrayList<>();
        FirebaseRecyclerOptions<Students> options= new FirebaseRecyclerOptions.Builder<Students>().
                setQuery(database,Students.class).build();
        adapter = new adapter(options);
        recyclerView.setAdapter(adapter);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(),AddActivity.class));
            }
        });
       return view;

    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}
