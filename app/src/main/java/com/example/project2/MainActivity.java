package com.example.project2;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    LinearLayout logINLayOut;
    LinearLayout logOutLayOut;
    LinearLayout offerslayout;
    LinearLayout galleryLayout;
    LinearLayout roomsLayout;
    LinearLayout bookLayout;
    LinearLayout serviceLayout;
    LinearLayout adminServiceLayout;


    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_main);
         logINLayOut=findViewById(R.id.logInLayout);
         logOutLayOut=findViewById(R.id.logOutLayout);
         offerslayout=findViewById(R.id.offers);
         galleryLayout=findViewById(R.id.gallery);
         roomsLayout=findViewById(R.id.rooms);
        bookLayout=findViewById(R.id.book);
        serviceLayout=findViewById(R.id.servic);
        adminServiceLayout=findViewById(R.id.adminService);

        SharedPreferences sharedPreferences =getSharedPreferences(LogInActivity.SHARED_PREF_NAME, Context.MODE_PRIVATE);
            if(!sharedPreferences.equals(null)) {
                String email = sharedPreferences.getString(LogInActivity.FirstName, "");
                email="deema";
                if(!email.equals("")) {
                    logINLayOut.setVisibility(8);
                    logOutLayOut.setVisibility(0);
                    if(email.contains("admin"))
                    {
                        offerslayout.setVisibility(4);
                        galleryLayout.setVisibility(8);
                        roomsLayout.setVisibility(0);
                        bookLayout.setVisibility(8);
                        adminServiceLayout.setVisibility(0);
                        serviceLayout.setVisibility(4);
                    }
                }
            }


    }

    public void getGalleryActivity(View view) {
        Intent intent = new Intent(this,Gallery.class);
        startActivity(intent);
    }

    public void LogInbtn(View view) {
        Intent intent2 = new Intent(this,LogInActivity.class);
        startActivity(intent2);
    }

    public void next(View view) {
        Intent intent2 = new Intent(this,offersActivity.class);
        startActivity(intent2);
    }

    public void getBookingActivity(View view) {
        Intent intent2 = new Intent(this,BookingActivity.class);
        startActivity(intent2);

    }

    @SuppressLint("WrongConstant")
    public void LogOut(View view) {
        SharedPreferences sharedPreferences =getSharedPreferences(LogInActivity.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(LogInActivity.FirstName);
        logINLayOut.setVisibility(0);
        logOutLayOut.setVisibility(8);
        offerslayout.setVisibility(0);
        galleryLayout.setVisibility(0);
        roomsLayout.setVisibility(8);
        bookLayout.setVisibility(0);
        adminServiceLayout.setVisibility(8);
        serviceLayout.setVisibility(0);

    }

    public void getRoomsStatusActivity(View view) {
        Intent intent = new Intent(this,roomStatusActivity.class);
        startActivity(intent);
    }
}