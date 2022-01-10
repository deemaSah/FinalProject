package com.example.project2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class LogInActivity extends AppCompatActivity {
private EditText emailEd,passwordEd;
private String email ,password;
private String URL =  "http://10.0.2.2/Mobile/login.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        email=password="";
        emailEd = findViewById(R.id.emailEd);
        passwordEd = findViewById(R.id.passwordEd);

    }

    public void login(View view) {
        email = emailEd.getText().toString().trim();
        password = passwordEd.getText().toString().trim();
        if(!email.equals("") && !password.equals("")){
            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if (response.equals("success")) {
                        Intent intent = new Intent(LogInActivity.this, sliderAdapter.class);
                        startActivity(intent);
                        finish();
                    } else if (response.equals("failed")) {
                        Toast.makeText(LogInActivity.this, "Invalid Login Id/Password", Toast.LENGTH_SHORT);
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                        Toast.makeText(LogInActivity.this, error.toString().trim(),Toast.LENGTH_SHORT).show();
                }
            }){
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> data = new HashMap<>();
                    data.put("email",email);
                    data.put("password",password);
                    return data;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(stringRequest);
        }else{
            Toast.makeText(this,"Fields can not be empty",Toast.LENGTH_LONG).show();
        }

    }
}