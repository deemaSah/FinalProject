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

public class roomStatusActivity extends AppCompatActivity {
    private List<Rooms> items = new ArrayList<>();
    private RecyclerView recycler;
    private static  final String BASE_URL = "http://10.0.2.2:80/Mobile/rooms.php";
    private RequestQueue queue;
    static ArrayList<bookedRoom> rooms = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_status);
        recycler=findViewById(R.id.roomsStatus_recycler);
        recycler.setLayoutManager(new GridLayoutManager(this,2));
        queue = Volley.newRequestQueue(roomStatusActivity.this);
        getBookedRoom();
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
                                String imgUrl = object.getString("imgUrl");
                                String description = object.getString("description");
                                String price = object.getString("price");
                                String pNum = object.getString("pNum");


                                Rooms obj = new Rooms(id,imgUrl,price,pNum, description);
                                items.add(obj);
                            }

                        } catch (Exception e) {

                        }


                        roomStatusAdapter adapter = new roomStatusAdapter(roomStatusActivity.this,
                                items,rooms);
                        recycler.setAdapter(adapter);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(roomStatusActivity.this, error.toString(),Toast.LENGTH_LONG).show();

            }


        });

        Volley.newRequestQueue(roomStatusActivity.this).add(stringRequest);

    }
    public void getBookedRoom() {
        String url = "http://10.0.2.2:80/Mobile/allBookedRooms.php";

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

                Toast.makeText(roomStatusActivity.this, error.toString(),
                        Toast.LENGTH_SHORT).show();
            }
        });

        queue.add(request);



    }

}