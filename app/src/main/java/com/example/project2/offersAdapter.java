package com.example.project2;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.Build;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class offersAdapter extends RecyclerView.Adapter<offersAdapter.ViewHolder>{
    private Context context;
    private List<offers> items;
    private Dialog dialog;
   private List<bookedRoom> rooms;
   private String url ="http://10.0.2.2:80/Mobile/Delete.php";




    public offersAdapter(Context context, List<offers> items,List<bookedRoom> rooms) {
        this.context = context;
        this.items = items;
        this.rooms=rooms;


    }

    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {

        CardView v = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.offers_card,
                parent,
                false);


        return new ViewHolder(v);
    }

    @SuppressLint("WrongConstant")
    @Override
    public void onBindViewHolder(ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        final offers offers = items.get(position);
        CardView cardView = holder.cardView;
        String email ="";
        SharedPreferences prefs1 = PreferenceManager.getDefaultSharedPreferences(context);
        email = prefs1.getString("USERNAME", "");
        if ((email.equals("")))
        {
            LinearLayout layout = (LinearLayout) cardView.findViewById(R.id.booked);
            layout.setVisibility(8);
            LinearLayout layout2 = (LinearLayout) cardView.findViewById(R.id.bokinglayout);
            layout2.setVisibility(0);
        }
        for (int i=0;i<rooms.size();i++) {
            if (offers.getId().equals(rooms.get(i).getIdRoom())) {

                LinearLayout layout = (LinearLayout) cardView.findViewById(R.id.booked);
                layout.setVisibility(0);
                LinearLayout layout2 = (LinearLayout) cardView.findViewById(R.id.bokinglayout);
                layout2.setVisibility(8);
            }
        }
        for (int i=0;i<rooms.size();i++) {

        }

        ImageView imageView = (ImageView) cardView.findViewById(R.id.image);
        Glide.with(context).load(offers.getImgUrl()).into(imageView);
        TextView txt3 = (TextView) cardView.findViewById(R.id.caption);
        txt3.setText(offers.getCaption());
        TextView txt = (TextView) cardView.findViewById(R.id.oldPrice);
        txt.setText(offers.getOldPrice());
        txt.setPaintFlags(txt.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

        TextView txt2 = (TextView) cardView.findViewById(R.id.newPrice);
        txt2.setText(offers.getNewPrice());
        //***************************************************************************************

        //***************************************************************************************
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

        cardView.findViewById(R.id.bookingbtn).setOnClickListener( new View.OnClickListener(){

            @Override
            public void onClick(View v){
                String email ="";
                SharedPreferences prefs1 = PreferenceManager.getDefaultSharedPreferences(context);
                 email = prefs1.getString("USERNAME", "");
                if (email.equals("")){
                    dialog.show();


                }else {

                    Intent intent2 = new Intent(context, CheckInOut.class);
                    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
                    SharedPreferences.Editor editor = prefs.edit();
                    Gson gson = new Gson();
                    String myObj = gson.toJson(offers.getId());
                    editor.putString("RoomID", myObj);
                    editor.commit();
                    context.startActivity(intent2);


                }
            }
        });
        cardView.findViewById(R.id.cancelbtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //===========================================================
                dialog = new Dialog(context);
                dialog.setContentView(R.layout.confirm_dialog);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    dialog.getWindow().setBackgroundDrawable(context.getDrawable(R.drawable.custom_dialog_background));
                }
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.setCancelable(false);
                dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
                Button Okay = dialog.findViewById(R.id.errorbtn);
                Button cancel2 = dialog.findViewById(R.id.Cbutton);
                Okay.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        LinearLayout layout = (LinearLayout) cardView.findViewById(R.id.booked);
                        layout.setVisibility(8);
                        LinearLayout layout2 = (LinearLayout) cardView.findViewById(R.id.bokinglayout);
                        layout2.setVisibility(0);
                        dialog.dismiss();
                        //==============================================
                        StringRequest request = new StringRequest(Request.Method.POST, url, new com.android.volley.Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Log.e("TAG", "RESPONSE IS " + response);
                                try {
                                    JSONObject jsonObject = new JSONObject(response);
                                    String check = jsonObject.getString("state");
                                    if (check.equals("delete"))
                                    {
                                        for (int i=0;i<rooms.size();i++)
                                        {
                                            if(rooms.get(i).getIdRoom().equals(items.get(position).getId()))
                                            {
                                                rooms.remove(i);
                                            }
                                        }
                                        Toast.makeText(context, "Delete successful", Toast.LENGTH_SHORT).show();
                                    }else {
                                        Toast.makeText(context,response,Toast.LENGTH_SHORT).show();
                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                        }, new com.android.volley.Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // method to handle errors.
                                Toast.makeText(context,
                                        "Fail to get response = " + error.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }) {

                            @Override
                            protected Map<String, String> getParams() {
                                Map<String, String> params = new HashMap<String, String>();
                                params.put("idRoom",items.get(position).getId());

                                return params;
                            }
                        };
                        RequestQueue requestQueue = Volley.newRequestQueue(context);
                        requestQueue.add(request);
                    }
                });
                cancel2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();

                    }
                });
                dialog.show();

            }

        });


    }
//********************************************************************
    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;
        public ViewHolder(CardView cardView){
            super(cardView);
            this.cardView = cardView;
        }
    }
    //***************************************************************


}
