package com.example.project2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class CheckInOut extends AppCompatActivity {
    TextView date1 ;
    TextView date2;
    EditText cNum;
    EditText cvcCode;
    EditText name;
    Button calendar1;
    Button calendar2;
    DatePickerDialog.OnDateSetListener setListener;
    DatePickerDialog.OnDateSetListener setListener2;
    private Dialog dialog;
    private Button next;
    LinearLayout layout1;
    LinearLayout layout2;
    LinearLayout layout3;
    TextView textView1;
    TextView textView2;
    TextView textView3;
    String email="";



    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_in_out);
        date1 = findViewById(R.id.date1Text);
        date2 = findViewById(R.id.dateText2);
        calendar1 = findViewById(R.id.calendarbtn1);
        calendar2 = findViewById(R.id.calendarbtn2);
        cNum=findViewById(R.id.cNum);
        cvcCode=findViewById(R.id.cvc);
        name=findViewById(R.id.name);
        layout1=findViewById(R.id.cNumLayout);
        layout2=findViewById(R.id.cvcLayout);
        layout3=findViewById(R.id.nameLayout);
        textView1=findViewById(R.id.cNumtext);
        textView2=findViewById(R.id.cvcText);
        textView3=findViewById(R.id.nameText);
        SharedPreferences prefs1 = PreferenceManager.getDefaultSharedPreferences(CheckInOut.this);
        email = prefs1.getString("USERNAME", "");
        if(email.contains("admin"))
        {
            layout1.setVisibility(8);
            layout2.setVisibility(8);
            layout3.setVisibility(0);
            textView1.setVisibility(8);
            textView2.setVisibility(8);
            textView3.setVisibility(0);
        }


        //********************************************
         calendarDialog();
    }
    //***********************************************************************
    public  void calendarDialog()
    {
        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        final int year2 = calendar.get(Calendar.YEAR);
        final int month2 = calendar.get(Calendar.MONTH);
        final int day2 = calendar.get(Calendar.DAY_OF_MONTH);


        calendar1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        CheckInOut.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, setListener
                        , year, month, day);
                datePickerDialog.show();
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));


            }
        });
        setListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year1, int month1, int dayOfMonth1) {
                month1 = month + 1;
                String date = day + "-" + month1 + "-" + year;
                date1.setText(date);
            }

        };
        //**********************************************************************
        calendar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        CheckInOut.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, setListener2
                        , year2, month2, day2);
                datePickerDialog.show();
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));


            }
        });
        setListener2 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String date = day + "-" + month+ "-" + year;
                date2.setText(date);
            }

        };

    }
    //***********************************************************************
    @Override
    protected void onStart( ) {
        super.onStart();
        next = findViewById(R.id.nextbutton);

        //Create the Dialog here
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.custom_dialog_layout);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.custom_dialog_background));
        }
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false); //Optional
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation; //Setting the animations to dialog
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                dialog.show(); // Showing the dialog here
            }
        });
        Button Okay = dialog.findViewById(R.id.btn_okay);
        Button Cancel = dialog.findViewById(R.id.btn_cancel);

        Okay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dateIN=date1.getText().toString();
                String dateOut=date2.getText().toString();
                String cardNum=cNum.getText().toString();
                String cvc = cvcCode.getText().toString();
                String userName="";
                if(email.contains("admin")){
                    userName=name.getText().toString();
                }

                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(CheckInOut.this);
                SharedPreferences.Editor editor = prefs.edit();
                String data = prefs.getString("RoomID", "");
                Gson gson = new Gson();
                String roomId = gson.fromJson(data,String.class);
                addBook(dateIN,dateOut,cardNum,cvc,userName,roomId);
                editor.remove("RoomID");
                editor.apply();
                if(email.contains("admin"))
                {
                    Intent intent = new Intent(CheckInOut.this, roomStatusActivity.class);
                    startActivity(intent);
                }else {
                    Intent intent = new Intent(CheckInOut.this, offersActivity.class);
                    startActivity(intent);
                }
                dialog.dismiss();

            }
        });
//*********************************************************************************************
        Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    //***************************************************************************

    private void addBook(String dateIn, String dateOut, String cardNum,String cvc,String userName,String roomId){
        String url = "http://10.0.2.2:80/Mobile/checkInOut.php";
        RequestQueue queue = Volley.newRequestQueue(CheckInOut.this);
        StringRequest request = new StringRequest(Request.Method.POST, url, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("TAG", "RESPONSE IS " + response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    Toast.makeText(CheckInOut.this,
                            jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // method to handle errors.
                Toast.makeText(CheckInOut.this,
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


                params.put("dateIn", dateIn);
                params.put("dateOut", dateOut);
                params.put("cardnum", cardNum);
                params.put("cvcCode", cvc);
                if(email.contains("admin")){
                    params.put("userName", userName);
                }else {
                    params.put("userName", email);
                }
                params.put("idRoom", roomId);

                return params;
            }
        };
        queue.add(request);
    }


    //***********************************************************************




}