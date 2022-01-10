package com.example.project2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class sportActivity extends AppCompatActivity {
    int counter=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sport);
    }


    public void yoga(View view) {
        counter ++;
        if(counter <=10){
            Toast.makeText(this, "You can come", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "Sorry! its full", Toast.LENGTH_SHORT).show();
        }
    }

    public void football (View view) {
        counter ++;
        if(counter <=10){
            Toast.makeText(this, "You can come", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "Sorry! its full", Toast.LENGTH_SHORT).show();
        }
    }

    public void tennis(View view) {
        counter ++;
        if(counter <=10){
            Toast.makeText(this, "You can come", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "Sorry! its full", Toast.LENGTH_SHORT).show();
        }
    }

    public void Marathon(View view) {
        counter ++;
        if(counter <=10){
            Toast.makeText(this, "You can come", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "Sorry! its full", Toast.LENGTH_SHORT).show();
        }
    }
}
