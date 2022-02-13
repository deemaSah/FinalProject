package com.example.project2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
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

public class SearchRoomActivity extends AppCompatActivity {


    private List<Rooms> items = new ArrayList<>();
    private RecyclerView recycler;
    private static String Pnum ="3";
    private static final String BASE_URL = "http://10.0.2.2:80/Mobile/search.php?pNum="+Pnum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        recycler = findViewById(R.id.room_recycler);


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
                            for (int i = 0; i < array.length(); i++) {

                                JSONObject object = array.getJSONObject(i);

                                String id = object.getString("id");
                                String imgUrl= object.getString("imgUrl");
                                String price = object.getString("price");
                                String pNum = object.getString("pNum");
                                String description = object.getString("description");



                                Rooms room = new Rooms(id, imgUrl, price, pNum, description);
                                items.add(room);
                            }

                        } catch (Exception e) {

                        }

                        searchAdapter adapter = new searchAdapter(SearchRoomActivity.this,
                                items);
                        recycler.setAdapter(adapter);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


                Toast.makeText(SearchRoomActivity.this, error.toString(), Toast.LENGTH_LONG).show();

            }
        });

        Volley.newRequestQueue(SearchRoomActivity.this).add(stringRequest);

    }
}