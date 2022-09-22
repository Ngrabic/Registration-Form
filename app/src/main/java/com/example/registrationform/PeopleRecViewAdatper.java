package com.example.registrationform;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class PeopleRecViewAdatper extends  RecyclerView.Adapter<PeopleRecViewAdatper.ViewHolder>{


    public static ArrayList<People> peoples=new ArrayList<>();
    Context context;
    MainActivity activity=new MainActivity();

    public PeopleRecViewAdatper(Context context) {
        this.context=context;
    }

    public void setPeoples(ArrayList<People> peoples) {
        this.peoples = peoples;
        notifyDataSetChanged();
    }

    public ArrayList<People> getPeoples() {
        return peoples;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.people_list_item,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(@NonNull  PeopleRecViewAdatper.ViewHolder holder, int position) {
        holder.peopleName.setText(peoples.get(position).getName());
        Intent intent=new Intent(context,ListOfPeopleAcitivity.class);
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("name",peoples.get(position).getName());
                intent.putExtra("email",peoples.get(position).getEmail());
                intent.putExtra("pass",peoples.get(position).getPassword());
                intent.putExtra("country",peoples.get(position).getCountry());
                intent.putExtra("gender",peoples.get(position).getGender());
                context.startActivity(intent);
            }
        });
        holder.img.setImageURI(peoples.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return peoples.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView peopleName;
        private CardView parent;
        private ImageView img;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            peopleName=itemView.findViewById(R.id.peopleNameId);
            parent=itemView.findViewById(R.id.parent);
            img=itemView.findViewById(R.id.cardViewImageId);
        }
    }
}
