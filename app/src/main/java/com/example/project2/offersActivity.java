package com.example.project2;

import androidx.appcompat.app.AppCompatActivity;
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

public class offersActivity extends AppCompatActivity {
    private List<offers> items = new ArrayList<>();
    private RecyclerView recycler;
    private static  final String BASE_URL = "http://10.0.2.2:80/proj2/offersItem.php";
    private RequestQueue queue;
    static ArrayList<bookedRoom> rooms = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offers);
        recycler=findViewById(R.id.offers_recycler);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        queue = Volley.newRequestQueue(offersActivity.this);
        getBookedRoom("deema");
        loadItems();
        //*****************************************




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
                                String caption = object.getString("caption");
                                String oldPrice = object.getString("oldPrice");
                                String newPrice = object.getString("newPrice");


                                offers obj = new offers(id,imgUrl, caption, oldPrice, newPrice);
                                items.add(obj);
                            }

                        } catch (Exception e) {

                        }


                        offersAdapter adapter = new offersAdapter(offersActivity.this,
                                items,rooms);
                        recycler.setAdapter(adapter);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(offersActivity.this, error.toString(),Toast.LENGTH_LONG).show();

            }


        });

        Volley.newRequestQueue(offersActivity.this).add(stringRequest);

    }
    public void getBookedRoom(String userName) {
        String url = "http://10.0.2.2:80/proj2/bookedRooms.php?userName=" + userName;

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject obj = response.getJSONObject(i);
                        rooms.add(new bookedRoom( obj.getString("id"),obj.getString("dateIn"),obj.getString("dateOut"),obj.getString("cardnum"),obj.getString("cvcCode"),obj.getString("userName"),obj.getString("idRoom")));
                    }catch(JSONException exception){
                        Log.d("Error", exception.toString());
                    }
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(offersActivity.this, error.toString(),
                        Toast.LENGTH_SHORT).show();
            }
        });

        queue.add(request);



    }
}