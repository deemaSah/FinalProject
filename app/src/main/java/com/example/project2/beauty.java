package com.example.project2;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class beauty extends AppCompatActivity {
    int counter=0;
  ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beauty);

    }

    public void skincare(View view) {
        counter ++;
        if(counter <=10){
            Toast.makeText(this,"Yes you can come" , Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "Sorry! its full", Toast.LENGTH_SHORT).show();
        }
    }

    public void makeup(View view) {
        counter ++;
        if(counter <=10){
            Toast.makeText(this, "You can come", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "Sorry! its full", Toast.LENGTH_SHORT).show();
        }
    }

    public void nails(View view) {
        counter ++;
        if(counter <=10){
            Toast.makeText(this, "You can come", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "Sorry! its full", Toast.LENGTH_SHORT).show();
        }
    }

    public void hair(View view) {
        counter ++;
        if(counter <=10){
            Toast.makeText(this, "You can come", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "Sorry! its full", Toast.LENGTH_SHORT).show();
        }
    }


}