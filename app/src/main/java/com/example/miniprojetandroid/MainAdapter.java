package com.example.miniprojetandroid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.myViewHolder> {
Context context;
ArrayList<Students> list;



 public MainAdapter(Context context, ArrayList<Students> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.main_item,parent,false);

        return new myViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
Students student = list.get(position);
holder.name.setText(student.getName());
holder.specialite.setText(student.getSpecialite());
holder.email.setText(student.getEmail());
holder.phone.setText(student.getPhone());
Glide.with(holder.img.getContext()).load(student.getImage())
        .placeholder(R.drawable.common_google_signin_btn_icon_dark)
        .circleCrop()
        .error(R.drawable.common_google_signin_btn_icon_dark_normal)
        .into(holder.img);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
   public static class myViewHolder extends RecyclerView.ViewHolder{
    CircleImageView img;
    TextView name,specialite,phone,email;

    public myViewHolder(@NonNull View itemView) {
        super(itemView);

            img = (CircleImageView)itemView.findViewById(R.id.img);
            name = (TextView) itemView.findViewById(R.id.nametext);
            specialite = (TextView)itemView.findViewById(R.id.specialitetext);
            phone = (TextView)itemView.findViewById(R.id.phonetext);
            email = (TextView)itemView.findViewById(R.id.emailtext);

    }
}
}

