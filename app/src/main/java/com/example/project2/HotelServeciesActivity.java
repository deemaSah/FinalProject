package com.example.project2;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class HotelServeciesActivity extends AppCompatActivity {

        private List<Hotel> items = new ArrayList<>();
        private RecyclerView recycler;
        private static  final String BASE_URL = "http://10.0.2.2/Mobile/get_items.php";
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_hotel_servecies);

            recycler = findViewById(R.id.hotel_recycler);


            recycler.setLayoutManager(new LinearLayoutManager(this));
            loadItems();
        }

        private void loadItems() {

            StringRequest stringRequest = new StringRequest(Request.Method.GET, BASE_URL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {

                                JSONArray array = new JSONArray(response);
                                for (int i = 0; i<array.length(); i++){

                                    JSONObject object = array.getJSONObject(i);

                                    String name = object.getString("name");
                                    String image = object.getString("image");

                                    Hotel pizza = new Hotel(name, image);
                                    items.add(pizza);
                                }

                            }catch (Exception e){

                            }

                            CaptionedImagesAdapter adapter = new CaptionedImagesAdapter(HotelServeciesActivity.this, items);
                            recycler.setAdapter(adapter);

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {


                    Toast.makeText(HotelServeciesActivity.this, error.toString(),Toast.LENGTH_LONG).show();

                }
            });

            Volley.newRequestQueue(HotelServeciesActivity.this).add(stringRequest);

        }
    }
