package com.example.project2;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class roomStatusAdapter extends RecyclerView.Adapter<roomStatusAdapter.ViewHolder>{
    private Context context;
    private List<Rooms> items;
    private Dialog dialog;
    private List<bookedRoom> bookedRooms;
    private String url ="http://10.0.2.2:80/Mobile/Delete.php";

    public roomStatusAdapter(Context context, List<Rooms> items, List<bookedRoom> rooms) {
        this.context = context;
        this.items = items;
        this.dialog = dialog;
        this.bookedRooms = rooms;
    }

    @NonNull
    @Override
    public roomStatusAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView v = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.roomstatuscard,
                parent,
                false);
        return new roomStatusAdapter.ViewHolder(v);
    }

    @SuppressLint("WrongConstant")
    @Override
    public void onBindViewHolder(@NonNull roomStatusAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        final Rooms rooms = items.get(position);
        String userName = " ";
        boolean flag = false;
        CardView cardView = holder.cardView;
        TextView id = (TextView) cardView.findViewById(R.id.roomID);
        TextView name = (TextView) cardView.findViewById(R.id.userName);
        Button cancel = (Button) cardView.findViewById(R.id.cancelBtn);
        Button booking = (Button) cardView.findViewById(R.id.bookingbutton);
        CheckBox box = (CheckBox) cardView.findViewById(R.id.checkBox);
        for (int i=0;i<bookedRooms.size();i++) {
            if (rooms.getId().equals(bookedRooms.get(i).getIdRoom())) {
                userName = bookedRooms.get(i).getUserName();
                flag=true;
            }
        }

        if (flag)
        {
            id.setText("Room ID: "+rooms.getId());
            box.setChecked(true);
            name.setText("Customer Name: "+userName);
        }else {
            id.setText("Room ID: "+rooms.getId());
            name.setVisibility(4);
            cancel.setVisibility(8);
            booking.setVisibility(0);

        }
        //**************************************************************************************
        cardView.findViewById(R.id.bookingbutton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(context, CheckInOut.class);
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
                SharedPreferences.Editor editor = prefs.edit();
                Gson gson = new Gson();
                String myObj = gson.toJson(rooms.getId());
                editor.putString("RoomID", myObj);
                editor.commit();
                context.startActivity(intent2);
            }
        });
        //**************************************************************************************
        cardView.findViewById(R.id.cancelBtn).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //********************************************************
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
                        name.setText("");
                        name.setVisibility(4);
                        cancel.setVisibility(8);
                        booking.setVisibility(0);
                        box.setChecked(false);
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
                                        for (int i=0;i<bookedRooms.size();i++)
                                        {
                                            if(bookedRooms.get(i).getIdRoom().equals(items.get(position).getId()))
                                            {
                                                bookedRooms.remove(i);
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

        //*****************************************************************


    }

    @Override
    public int getItemCount() { return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public CardView cardView;

        public ViewHolder(CardView cardView){
            super(cardView);
            this.cardView = cardView;
        }
    }
}