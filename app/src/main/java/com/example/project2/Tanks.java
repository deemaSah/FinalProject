package com.example.project2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

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


public class Tanks extends AppCompatActivity {
    SliderView sliderView;
    private EditText Regname ;
    int[] images = {R.drawable.t1,
            R.drawable.t2 ,
            R.drawable.t3};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tanks);
        Regname = findViewById(R.id.RegNamet);
        sliderView = findViewById(R.id.image_slider);
        Slider_Adapter1 slider_adapter3 = new Slider_Adapter1(images);

        sliderView.setSliderAdapter(slider_adapter3);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);
        sliderView.startAutoCycle();

    }

    public void tanBtn(View view) {

        if(Regname.getText().equals(null)){
            Toast.makeText(this,"Enter name" , Toast.LENGTH_SHORT).show();
        }
        else{
            String Name = Regname.getText().toString();
            addUser(Name);
        }
    }


    private void addUser(String name) {
        String url = "http://10.0.2.2/Mobile/add_entertaiment.php";
        RequestQueue queue = Volley.newRequestQueue(Tanks.this);
        StringRequest request = new StringRequest(Request.Method.POST, url, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("TAG", "RESPONSE IS " + response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    // on below line we are displaying a success toast message.
                    Toast.makeText(Tanks.this,
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
                Toast.makeText(Tanks.this,
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
                params.put("serviceName", "Tanks");



                return params;
            }
        };

        queue.add(request);
    }

}