package com.example.project2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class entertaement extends AppCompatActivity {


    ListView listView;
    String mTitle [] = {"BAHAMAS" , " BLUE NIGHT POOL" , "FISH TANKS"};
    String mDescrption [] = {"GO TO BAHAMAS" ,"GO TO BLUE NIGHT POOL" ,"GO TO FISH TANKS" };
    int images[] = {R.drawable.foure , R.drawable.three , R.drawable.two};



    private Button btnone ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entertaement);

        ImageView iv_background = findViewById(R.id.iv_background);
        AnimationDrawable animationDrawable = (AnimationDrawable) iv_background.getDrawable();
        animationDrawable.start();


        listView = findViewById(R.id.ListView);

        myAdapter adapter = new myAdapter(this,mTitle,mDescrption,images);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0 ){
                    Toast.makeText(entertaement.this,"BAHAMAS" , Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext() , Bahamas.class);

                    startActivity(intent);


                }
                if(position == 1 ){
                    Toast.makeText(entertaement.this,"BLUE NIGHT POOL" , Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(getApplicationContext() , Pool.class);
                    startActivity(intent);
                }
                if(position == 2 ){
                    Toast.makeText(entertaement.this,"FISH TANKS" , Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(getApplicationContext() , Tanks.class);
                    startActivity(intent);
                }
            }
        });


    }


    class myAdapter extends ArrayAdapter<String>{
        Context context;
        String rTitle [];
        String rDescription [];
        int rImgs [];

        myAdapter(Context c , String title[] , String description[] , int imgs[]){
            super(c,R.layout.row,R.id.txt1,title);
            this.context = c;
            this.rTitle = title;
            this.rDescription = description;
            this.rImgs = imgs;

        }
        @NonNull
        @Override
        public View getView(int position , @Nullable View convertView, @Nullable ViewGroup parent){
            LayoutInflater layoutInflater = (LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = layoutInflater.inflate(R.layout.row,parent,false);
            ImageView images = row.findViewById(R.id.Image);
            TextView myTitle = row.findViewById(R.id.txt1);
            TextView myDescription = row.findViewById(R.id.txt2);

            images.setImageResource(rImgs[position]);
            myTitle.setText(rTitle[position]);
            myDescription.setText(rDescription[position]);



            return row;
        }

    }




}