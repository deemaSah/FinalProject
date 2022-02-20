package com.example.project2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

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
    LinearLayout weatherLayout;

    //static String email="";


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
        weatherLayout=findViewById(R.id.weatherLayout);


        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        SharedPreferences.Editor editor = prefs.edit();
        String email = prefs.getString("USERNAME", "");
        if(!prefs.equals(null)) {
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
                        weatherLayout.setVisibility(4);


                    }
                }
            }



    }

    public void getGalleryActivity(View view) {
        Thread thread = new Thread(new MyThread(5));
        thread.start();
        Intent intent = new Intent(this,Gallery.class);
        startActivity(intent);
    }

    public void LogInbtn(View view) {
        Thread thread = new Thread(new MyThread(5));
        thread.start();
        Intent intent2 = new Intent(this,LogInActivity.class);
        startActivity(intent2);
    }

    public void next(View view) {
        Thread thread = new Thread(new MyThread(5));
        thread.start();
        Intent intent2 = new Intent(this,offersActivity.class);
        startActivity(intent2);
    }

    public void getBookingActivity(View view) {
        Thread thread = new Thread(new MyThread(5));
        thread.start();
        Intent intent2 = new Intent(this,BookingActivity.class);
        startActivity(intent2);

    }

    @SuppressLint("WrongConstant")
    public void LogOut(View view) {
        Thread thread = new Thread(new MyThread(5));
        thread.start();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        SharedPreferences.Editor editor = prefs.edit();
        String data = prefs.getString("USERNAME", "");
        editor.remove("USERNAME");
        editor.apply();
        editor.commit();
        logINLayOut.setVisibility(0);
        logOutLayOut.setVisibility(8);
        offerslayout.setVisibility(0);
        galleryLayout.setVisibility(0);
        roomsLayout.setVisibility(8);
        bookLayout.setVisibility(0);
        adminServiceLayout.setVisibility(8);
        serviceLayout.setVisibility(0);
        weatherLayout.setVisibility(0);


    }

    public void getRoomsStatusActivity(View view) {
        Thread thread = new Thread(new MyThread(5));
        thread.start();
        Toast.makeText(this, "Done.....", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this,roomStatusActivity.class);
        startActivity(intent);
    }

    public void getServiceActivity(View view) {
        Thread thread = new Thread(new MyThread(5));
        thread.start();
        Intent intent2 = new Intent(this,HotelServeciesActivity.class);
        startActivity(intent2);
    }

    public void gerAdminService(View view) {
        Thread thread = new Thread(new MyThread(5));
        thread.start();
        Intent intent2 = new Intent(this, AdminServicesActivity.class);
        startActivity(intent2);
    }

    public void getWeatherActivity(View view) {
        Thread thread = new Thread(new MyThread(5));
        thread.start();
        Intent intent2 = new Intent(this, weatherActivity.class);
        startActivity(intent2);
    }
    class MyThread implements Runnable{
        int seconds;

        public MyThread(int seconds){
            this.seconds = seconds;
        }
        @Override
        public void run() {

            for(int i = 0; i<seconds; i++){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }



        }
    }
}