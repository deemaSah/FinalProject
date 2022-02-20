package com.example.project2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.smarteist.autoimageslider.SliderViewAdapter;

public class Slider_Adapter1 extends SliderViewAdapter<Slider_Adapter1.Holder> {

    int [] images ;

    public Slider_Adapter1 (int [] images){
        this.images = images ;

    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.slider_item1,parent,false);
        return new Holder(view);


    }

    @Override
    public void onBindViewHolder(Holder viewHolder, int position) {
        viewHolder.imageView.setImageResource(images[position]);
    }

    @Override
    public int getCount() {

        return images.length;
    }

    public class Holder extends SliderViewAdapter.ViewHolder{
        ImageView imageView;
        public Holder(View itemView){
            super(itemView);

            imageView = itemView.findViewById(R.id.image_view);

        }
    }
}
