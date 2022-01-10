package com.example.project2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class FoodActivity extends AppCompatActivity {
        int counter=0;
        @Override
        protected void onCreate(Bundle savedInstanceState) {


            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_food);
        }

    public void breakfast(View view) {
        counter ++;
        if(counter <=10){
            Toast.makeText(this, "You can come", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "Sorry! its full", Toast.LENGTH_SHORT).show();
        }
    }

    public void maindish(View view) {
        counter ++;
        if(counter <=10){
            Toast.makeText(this, "You can come", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "Sorry! its full", Toast.LENGTH_SHORT).show();
        }
    }

    public void appetizers(View view) {
        counter ++;
        if(counter <=10){
            Toast.makeText(this, "You can come", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "Sorry! its full", Toast.LENGTH_SHORT).show();
        }
    }

    public void snaks(View view) {
        counter ++;
        if(counter <=10){
            Toast.makeText(this, "You can come", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "Sorry! its full", Toast.LENGTH_SHORT).show();
        }
    }


}
