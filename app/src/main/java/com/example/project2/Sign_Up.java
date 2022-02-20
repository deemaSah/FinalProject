package com.example.project2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
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


public class Sign_Up extends AppCompatActivity {

    private EditText nametxt;
    private EditText passwordtxt;
    private TextView note;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        nametxt = findViewById(R.id.Nametxt);

        passwordtxt = findViewById(R.id.Passwordtxt);

    }


    public void btnAddOnClick(View view) {

        String Name = nametxt.getText().toString();
        String Password = passwordtxt.getText().toString();

        if(Name.equals("") && Password.equals("")){
            Toast.makeText(Sign_Up.this,"PLEAS ENTER NAME / PASSWORD",Toast.LENGTH_SHORT).show();
        }
        else{
            addUser(Name, Password);
        }



    }


    private void addUser(String name , String password) {
        String url = "http://10.0.2.2:80/Mobile/Add_User.php";
        RequestQueue queue = Volley.newRequestQueue(Sign_Up.this);
        StringRequest request = new StringRequest(Request.Method.POST, url, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("TAG", "RESPONSE IS " + response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    // on below line we are displaying a success toast message.
                    Toast.makeText(Sign_Up.this,
                            jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }
                , new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // method to handle errors.
                Toast.makeText(Sign_Up.this,
                        "Fail to get response = " + error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            public String getBodyContentType() {

                return "application/x-www-form-urlencoded; charset=UTF-8";
            }

            @Override
            protected Map<String, String> getParams() {


                Map<String, String> params = new HashMap<String, String>();

                params.put("name", name);
                params.put("password", password);

                return params;
            }
        };

        queue.add(request);
    }


    public void GoToLoginOnClick(View view) {
        Intent intent = new Intent(this,LogInActivity.class);
        startActivity(intent);

    }
}