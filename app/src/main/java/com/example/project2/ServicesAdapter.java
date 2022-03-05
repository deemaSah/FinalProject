package com.example.project2;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServicesAdapter extends RecyclerView.Adapter<ServicesAdapter.ViewHolder>{
    private Context context;
    private List<serviceItems> items;
    private Dialog dialog;
    private String url ="http://10.0.2.2:80/Mobile/adminDelete.php";

    public ServicesAdapter(Context context, List<serviceItems> items) {
        this.context = context;
        this.items = items;
        this.dialog = dialog;
    }

    @NonNull
    @Override
    public ServicesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView v = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.adminservicescard,
                parent,
                false);
        return new ServicesAdapter.ViewHolder(v);
    }

    @SuppressLint("WrongConstant")
    @Override
    public void onBindViewHolder(@NonNull ServicesAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        final serviceItems rooms = items.get(position);
        boolean flag = false;
        CardView cardView = holder.cardView;
        TextView id = (TextView) cardView.findViewById(R.id.serviceName);
        TextView name = (TextView) cardView.findViewById(R.id.customerName);
        Button cancel = (Button) cardView.findViewById(R.id.adCancelBtn);
        id.setText("Service: "+rooms.getService());
        name.setText("Customer Name: "+rooms.getEmail());

        //**************************************************************************************

        //**************************************************************************************
        cardView.findViewById(R.id.adCancelBtn).setOnClickListener(new View.OnClickListener(){
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
                      cardView.setVisibility(8);
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
                                       /* for (int i=0;i<bookedRooms.size();i++)
                                        {
                                            if(bookedRooms.get(i).getIdRoom().equals(items.get(position).getId()))
                                            {
                                                bookedRooms.remove(i);
                                            }
                                        }*/
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
                                params.put("email",items.get(position).getEmail());
                                params.put("serviceName",items.get(position).getService());

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