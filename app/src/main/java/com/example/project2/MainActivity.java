package com.example.project2;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_main);
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
        Intent intent2 = new Intent(this,search.class);
        startActivity(intent2);
    }
}