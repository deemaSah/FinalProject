package com.example.project2;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class Bahamas extends AppCompatActivity {
    SliderView sliderView;
    private EditText Nametxt;
    private Button RegBtn;

    int[] images = {R.drawable.b1,
            R.drawable.b2,
            R.drawable.b3};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bahamas);

        RegBtn = findViewById(R.id.btnOne_A);
        Nametxt = findViewById(R.id.RegName1);

        sliderView = findViewById(R.id.image_slider);
        Slider_Adapter1 slider_adapter1 = new Slider_Adapter1(images);

        sliderView.setSliderAdapter(slider_adapter1);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);
        sliderView.startAutoCycle();


    }


    public void BahBtnOnClick(View view) {

        String Name = Nametxt.getText().toString();

        addUser(Name);



    }


    private void addUser(String name) {
        String url = "http://10.0.2.2:80/Mobile/add_entertaiment.php";
        RequestQueue queue = Volley.newRequestQueue(Bahamas.this);
        StringRequest request = new StringRequest(Request.Method.POST, url, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("TAG", "RESPONSE IS " + response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    // on below line we are displaying a success toast message.
                    Toast.makeText(Bahamas.this,
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
                Toast.makeText(Bahamas.this,
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

                params.put("email", name);
                params.put("serviceName", "Bahamas");



                return params;
            }
        };

        queue.add(request);
    }



}