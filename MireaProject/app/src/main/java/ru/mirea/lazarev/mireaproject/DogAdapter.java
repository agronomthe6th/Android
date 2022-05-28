package ru.mirea.gribkova.mireaproject1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DogAdapter extends RecyclerView.Adapter<DogAdapter.ViewHolder>{
    private List<Dog> dogs;

    private LayoutInflater mInflater;

    public DogAdapter(List<Dog> dogs){
        this.dogs = dogs;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DogAdapter.ViewHolder holder, int position) {

        holder.namedog.setText(dogs.get(position).name_dog);
        holder.breed.setText(dogs.get(position).breed);
    }

    @Override
    public int getItemCount() {
        return dogs.size();

    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView breed;
        TextView namedog;

        ViewHolder(View itemView){
            super(itemView);
            breed = itemView.findViewById(R.id.breed);
            namedog = itemView.findViewById(R.id.namedog);

        }

    }



}
