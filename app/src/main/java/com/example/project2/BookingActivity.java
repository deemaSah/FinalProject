package com.example.project2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class BookingActivity extends AppCompatActivity {

        private List<Rooms> items = new ArrayList<>();
        private RecyclerView recycler;
        private static final String BASE_URL = "http://10.0.2.2:80/Mobile/rooms.php";
        FloatingActionButton searchBtn;
        private Dialog dialog;


    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_booking);
            searchBtn=findViewById(R.id.searchBtn);
            recycler = findViewById(R.id.room_recycler);
            recycler.setLayoutManager(new LinearLayoutManager(this));
            loadItems();
            searchBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(BookingActivity.this, searchDialog.class);

                    BookingActivity.this.startActivity(intent);
                }
            });
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

                                    String id = object.getString("id");
                                    String imgUrl = object.getString("imgUrl");
                                    String description = object.getString("description");
                                    String price = object.getString("price");
                                    String pNum = object.getString("pNum");


                                    Rooms obj = new Rooms(id,imgUrl,price,pNum, description);
                                    items.add(obj);
                                }

                            } catch (Exception e) {

                            }
                            CaptionedImagesAdapter adapter = new CaptionedImagesAdapter(BookingActivity.this,
                                    items);
                            recycler.setAdapter(adapter);

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {


                    Toast.makeText(BookingActivity.this, error.toString(), Toast.LENGTH_LONG).show();

                }
            });

            Volley.newRequestQueue(BookingActivity.this).add(stringRequest);

        }

}