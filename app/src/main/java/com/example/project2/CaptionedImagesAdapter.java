package com.example.project2;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.util.List;

import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

public class CaptionedImagesAdapter
        extends RecyclerView.Adapter<CaptionedImagesAdapter.ViewHolder>{
    private Context context;
    private List<Rooms> items;
    private Dialog dialog;



    public CaptionedImagesAdapter(Context context, List<Rooms> items){
        this.context = context;
        this.items = items;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView v = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.card_captioned_image,
                parent,
                false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Rooms room = items.get(position);
        CardView cardView = holder.cardView;
        ImageView imageView = (ImageView) cardView.findViewById(R.id.image);
        Glide.with(context).load(room.getImgUrl()).into(imageView);
        TextView txt = (TextView) cardView.findViewById(R.id.txtName);
        TextView txt2 = (TextView) cardView.findViewById(R.id.description);
        txt.setText(room.getPrice());
        txt2.setText(room.getDescription());
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.login_dialog);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            dialog.getWindow().setBackgroundDrawable(context.getDrawable(R.drawable.custom_dialog_background));
        }
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        Button Okay = dialog.findViewById(R.id.okaybtn);
        Button cancel = dialog.findViewById(R.id.cbtn);
        Okay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, LogInActivity.class);
                context.startActivity(intent);
                dialog.dismiss();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        cardView.findViewById(R.id.reserve).setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String email = "";
                SharedPreferences prefs1 = PreferenceManager.getDefaultSharedPreferences(context);
                email = prefs1.getString("USERNAME", "");
                if (email.equals("")){
                    dialog.show();


                }else {

                    Intent intent2 = new Intent(context, checkinoutActivity2.class);
                    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
                    SharedPreferences.Editor editor = prefs.edit();
                    Gson gson = new Gson();
                    String myObj = gson.toJson(room.getId());
                    editor.putString("RoomID", myObj);
                    editor.commit();
                    context.startActivity(intent2);
                }

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

