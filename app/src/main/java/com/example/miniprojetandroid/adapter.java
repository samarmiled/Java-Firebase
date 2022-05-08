package com.example.miniprojetandroid;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class adapter extends FirebaseRecyclerAdapter<Students,adapter.myViewHolder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public adapter(@NonNull FirebaseRecyclerOptions<Students> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull Students students) {
        holder.name.setText(students.getName());
        holder.specialite.setText(students.getSpecialite());
        holder.email.setText(students.getEmail());
        holder.phone.setText(students.getPhone());
        Glide.with(holder.img.getContext()).load(students.getImage())
                .placeholder(R.drawable.common_google_signin_btn_icon_dark)
                .circleCrop()
                .error(R.drawable.common_google_signin_btn_icon_dark_normal)
                .into(holder.img);
        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.img.getContext())
                        .setContentHolder(new ViewHolder(R.layout.update))
                        .setExpanded(true,1800)
                        .create();
                //dialogPlus.show();
                View view1 = dialogPlus.getHolderView();
                EditText name = (EditText) view1.findViewById(R.id.etName);
                EditText specialite = (EditText) view1.findViewById(R.id.etSpecialite);
                EditText bac = (EditText) view1.findViewById(R.id.etBac);
                EditText phone = (EditText) view1.findViewById(R.id.etPhone);
                EditText email = (EditText) view1.findViewById(R.id.etEmail);
                EditText moy = (EditText) view1.findViewById(R.id.etMoyBac);
                EditText img = (EditText)view1.findViewById(R.id.etImage);
                Button btnUpdate =(Button) view1.findViewById(R.id.btnUpdate);

                name.setText(students.getName());
                specialite.setText(students.getSpecialite());
                bac.setText(students.getBaccalaureat());
                phone.setText(students.getPhone());
                email.setText(students.getEmail());
                moy.setText(students.getMoyenneBac());
                img.setText(students.getImage());

                dialogPlus.show();
                btnUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Map<String,Object> map = new HashMap<>();
                        map.put("baccalaureat",bac.getText().toString());
                        map.put("email",email.getText().toString());
                        map.put("image",img.getText().toString());
                        map.put("moyenneBac",moy.getText().toString());
                        map.put("name",name.getText().toString());
                        map.put("phone",phone.getText().toString());
                        map.put("specialite",specialite.getText().toString());
                        FirebaseDatabase.getInstance().getReference().child("students")
                                .child(getRef(holder.getAdapterPosition()).getKey()).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(holder.name.getContext()," Data Updates Successfully !",Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(Exception e) {
                                Toast.makeText(holder.name.getContext()," Error !",Toast.LENGTH_SHORT).show();
                                dialogPlus.dismiss();

                            }
                        });
                    }
                });
            }
        });
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.name.getContext());
                builder.setTitle("Are you Sure To delete ?");
                builder.setMessage("Deleted data can't be Undo .");

                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseDatabase.getInstance().getReference().child("students")
                                .child(getRef(holder.getAdapterPosition()).getKey())
                                .removeValue();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(holder.name.getContext(),"Delete Cancelled !",Toast.LENGTH_SHORT).show();

                    }
                });
                builder.show();
            }
        });
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_item,parent,false);

        return new myViewHolder(v);
    }

    class myViewHolder extends RecyclerView.ViewHolder{
        CircleImageView img;
        TextView name,specialite,phone,email;
        Button btnEdit,btnDelete;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            img = (CircleImageView)itemView.findViewById(R.id.img);
            name = (TextView) itemView.findViewById(R.id.nametext);
            specialite = (TextView)itemView.findViewById(R.id.specialitetext);
            phone = (TextView)itemView.findViewById(R.id.phonetext);
            email = (TextView)itemView.findViewById(R.id.emailtext);
            btnEdit = (Button) itemView.findViewById(R.id.btnEdit);
            btnDelete = (Button) itemView.findViewById(R.id.btnDelete);

        }
    }
}
