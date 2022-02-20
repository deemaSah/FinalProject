package com.example.project2;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class RoomsAdapter extends RecyclerView.Adapter<RoomsAdapter.ViewHolder>{

    private Context context;
    private List<Hotel> items;

    public RoomsAdapter(Context context, List<Hotel> items ){
        this.context = context;
        this.items = items;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView v = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.card_caption_image,
                parent,
                false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Hotel pizza = items.get(position);
        CardView cardView = holder.cardView;
        ImageView imageView = (ImageView) cardView.findViewById(R.id.image);
        Glide.with(context).load(pizza.getImage()).into(imageView);
        TextView txt = (TextView)cardView.findViewById(R.id.txtName);
        txt.setText(pizza.getName());
        cardView.setOnClickListener(view -> {
            if(position==0) {
                Intent intent = new Intent(context,FoodActivity.class);
                context.startActivity(intent);
            }
            if(position==1) {
                Intent intent = new Intent(context, beauty.class);
                context.startActivity(intent);
            }
            if(position==2) {
                Intent intent = new Intent(context, sportActivity.class);
                context.startActivity(intent);
            }
            if(position==3) {
                Intent intent = new Intent(context,entertaement.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private CardView cardView;
        public ViewHolder(CardView cardView){
            super(cardView);
            this.cardView = cardView;
        }

    }
}

