package com.example.project2;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class FoodActivity extends AppCompatActivity {
        int counter=0;
        @Override
        protected void onCreate(Bundle savedInstanceState) {


            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_food);
        }

    public void breakfast(View view) {
        counter ++;
        if(counter <=10){
            SharedPreferences sharedPreferences =getSharedPreferences(LogInActivity.SHARED_PREF_NAME, Context.MODE_PRIVATE);
            addname(sharedPreferences.getString(LogInActivity.FirstName,null),"breakfast");
            Toast.makeText(this,"You can come", Toast.LENGTH_SHORT).show();

        }
        else{
            Toast.makeText(this, "Sorry! its full", Toast.LENGTH_SHORT).show();
        }
    }

    public void maindish(View view) {
        counter ++;
        if(counter <=10){
            SharedPreferences sharedPreferences =getSharedPreferences(LogInActivity.SHARED_PREF_NAME, Context.MODE_PRIVATE);
            addname(sharedPreferences.getString(LogInActivity.FirstName,null),"maindish");
            Toast.makeText(this,"You can come", Toast.LENGTH_SHORT).show();

        }
        else{
            Toast.makeText(this, "Sorry! its full", Toast.LENGTH_SHORT).show();
        }
    }

    public void appetizers(View view) {
        counter ++;
        if(counter <=10){
            SharedPreferences sharedPreferences =getSharedPreferences(LogInActivity.SHARED_PREF_NAME, Context.MODE_PRIVATE);
            addname(sharedPreferences.getString(LogInActivity.FirstName,null),"appetizers");
            Toast.makeText(this,"You can come", Toast.LENGTH_SHORT).show();

        }
        else{
            Toast.makeText(this, "Sorry! its full", Toast.LENGTH_SHORT).show();
        }
    }

    public void snaks(View view) {
        counter ++;
        if(counter <=10){
            SharedPreferences sharedPreferences =getSharedPreferences(LogInActivity.SHARED_PREF_NAME, Context.MODE_PRIVATE);
            addname(sharedPreferences.getString(LogInActivity.FirstName,null),"snaks");
            Toast.makeText(this,"You can come", Toast.LENGTH_SHORT).show();

        }
        else{
            Toast.makeText(this, "Sorry! its full", Toast.LENGTH_SHORT).show();
        }
    }
    private void addname(String email, String serviceName){
        String url = "http://10.0.2.2/Mobile/Add_FoodName.php";
        RequestQueue queue = Volley.newRequestQueue(FoodActivity.this);
        StringRequest request = new StringRequest(Request.Method.POST, url, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("TAG", "RESPONSE IS " + response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    Toast.makeText(FoodActivity.this,
                            jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

                Toast.makeText(FoodActivity.this,
                        "Fail to get response = " + volleyError, Toast.LENGTH_SHORT).show();

            }

        }) {
            @Override
            public String getBodyContentType() {
                return "application/x-www-form-urlencoded; charset=UTF-8";
            }

            @Override
            protected Map<String, String> getParams() {


                Map<String, String> params = new HashMap<String, String>();

                params.put("email", email);
                params.put("serviceName", serviceName);



                return params;
            }
        };

        queue.add(request);
    }


}
