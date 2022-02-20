package com.example.project2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AdminSport extends AppCompatActivity {
    private List<serviceItems> items = new ArrayList<>();
    private RecyclerView recycler;
    private static  final String BASE_URL = "http://10.0.2.2:80/Mobile/getSport.php";
    private RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_sport);
        recycler=findViewById(R.id.adminSport_recycler);
        recycler.setLayoutManager(new GridLayoutManager(this,2));
        queue = Volley.newRequestQueue(AdminSport.this);
        loadItems();

    }
    private void loadItems() {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, BASE_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        try {

                            JSONArray array = new JSONArray(response);
                            for (int i = 0; i < array.length(); i++) {

                                JSONObject object = array.getJSONObject(i);

                                String email = object.getString("email");
                                String service = object.getString("serviceName");


                                serviceItems obj = new serviceItems(email,service);
                                items.add(obj);
                            }

                        } catch (Exception e) {

                        }


                        ServicesAdapter adapter = new ServicesAdapter(AdminSport.this,
                                items);
                        recycler.setAdapter(adapter);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(AdminSport.this, error.toString(),Toast.LENGTH_LONG).show();

            }


        });

        Volley.newRequestQueue(AdminSport.this).add(stringRequest);

    }

}