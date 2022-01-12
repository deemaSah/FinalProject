package com.example.project2;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;


public class LogInActivity extends AppCompatActivity {

    private EditText emailEd , passwordEd;
    private String email,password;
    private static  final String URL = "http://10.0.2.2/Mobile/login.php";
    private Button login;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        email = password ="";
        emailEd = findViewById(R.id.emailEd);
        passwordEd = findViewById(R.id.passwordEd);
        login = findViewById(R.id.login);

        login.setOnClickListener(view -> {
        String email = emailEd.getText().toString();

        String password = passwordEd.getText().toString();
           if(!email.equals("") && !password.equals("")){

               StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                   @Override
                   public void onResponse(String response) {
                       Toast.makeText(LogInActivity.this, response.toString(), Toast.LENGTH_LONG).show();
                       if (response.contains("success")) {
                           SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(LogInActivity.this);
                           SharedPreferences.Editor editor = prefs.edit();
                           Gson gson = new Gson();
                           String emailgson= gson.toJson(email);
                           editor.putString("Email", emailgson);
                           editor.commit();
                           Intent intent = new Intent(LogInActivity.this, HotelServeciesActivity.class);
                           intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                           startActivity(intent);

                       } else if (response.contains("failure")) {
                           AlertDialog alertDialog = new AlertDialog.Builder(LogInActivity.this).create();
                           alertDialog.setTitle("Invalid!");
                           alertDialog.setMessage("Invalid Login Email/password");
                           alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
                               @Override
                               public void onClick(DialogInterface dialogInterface, int i) {
                                   dialogInterface.dismiss();
                               }
                           });
                           alertDialog.show();
                         //  Toast.makeText(LogInActivity.this, "Invalid Login Email/password", Toast.LENGTH_LONG).show();

                       }
                   }
               }, new Response.ErrorListener() {
                   @Override
                   public void onErrorResponse(VolleyError volleyError) {
                       Toast.makeText(LogInActivity.this, volleyError.toString(), Toast.LENGTH_LONG).show();
                   }
               }){
                   @Nullable
                   @Override
                   protected Map<String, String> getParams() throws AuthFailureError {
                      Map<String ,String>data = new HashMap<>();
                      data.put("email" ,email);
                      data.put("password",password);

                       return data;
                   }
               };
               RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
               requestQueue.add(stringRequest);
           }else {
               Toast.makeText(this , "Failed can not be empty",Toast.LENGTH_LONG).show();
           }
        });
    }



}