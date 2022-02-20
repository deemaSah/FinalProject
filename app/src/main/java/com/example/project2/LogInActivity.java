package com.example.project2;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
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

    public final static String SHARED_PREF_NAME="log_user_info";
    public final static String FirstName="fname";
    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        sharedPreferences=getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
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
                           SharedPreferences.Editor editor =sharedPreferences.edit();
                           editor.putString(FirstName,email);
                           editor.apply();



//                           SharedPreferences prefs = getSharedPreferences("IDvalue", 0);
//                           SharedPreferences.Editor editor = prefs.edit();
//                           Gson gson = new Gson();
//                           String emailgson= gson.toJson(email);
//                           editor.putString("Email", emailgson);
//                           editor.commit();

                           Intent intent = new Intent(LogInActivity.this, MainActivity.class);
                           intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);

                           SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(LogInActivity.this);
                           SharedPreferences.Editor editor2 = prefs.edit();
                           editor2.putString("USERNAME", email);
                           editor2.commit();

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


    public void toSignUp(View view) {
        Intent intent = new Intent(LogInActivity.this,Sign_Up.class);
        startActivity(intent);
    }
}