package com.example.project2;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

public class searchDialog extends AppCompatDialogFragment  {
    TextView adultscounter;
    Button adultsplus;
    Button adultsminus;
    int counterads = 0;
    TextView childrencounter;
    Button childrenplus;
    Button childrenminus;
    int counterchildren = 0;
  static  int counter = 0;
    static  int counter2 = 0;


    public final static String SHARED_PREF_NAME="dialog_Count";
    public final static String counterStr ="counter";
    public final static String childrenCounter="childname";

    SharedPreferences sharedPreferences;
    Context context;
    searchDialog(Context context){
        this.context=context;

    }

    @Override

    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.activity_search_dialog,null);
        builder.setView(view);
        sharedPreferences= getActivity().getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

        adultscounter =view.findViewById(R.id.adultscounter);
        adultsplus=view.findViewById(R.id.adultsplus);
        adultsminus=view.findViewById(R.id.adultsminus);


        childrencounter =view.findViewById(R.id.childrenscounter);
        childrenplus=view.findViewById(R.id.childrensplus);
        childrenminus=view.findViewById(R.id.childrensminus);


        builder.setTitle("Search");
        adultsplus.setOnClickListener(v -> {
            counterads++;
            adultscounter.setText(Integer.toString(counterads));
            counter=counterads;


        });
        adultsminus.setOnClickListener(v -> {
            counterads--;

            adultscounter.setText(Integer.toString(counterads));
            if(counterads<0){
                counterads=0;
                adultscounter.setText(Integer.toString(counterads));
                counter=counterads;

            }
            counter=counterads;
        //    SharedPreferences.Editor editor =sharedPreferences.edit();
       //     editor.putString(adultsCounter, String.valueOf(counterads));
        //    editor.apply();
        });

        childrenplus.setOnClickListener(v -> {
            counterchildren++;
            childrencounter.setText(Integer.toString( counterchildren));
            counter2=counterchildren;
        });
        childrenminus.setOnClickListener(v -> {
            counterchildren--;
            childrencounter.setText(Integer.toString(counterchildren));
            if(counterchildren<0){
                counterchildren=0;
                childrencounter.setText(Integer.toString(counterchildren));

            }
            counter2=counterchildren;

        });

        builder.setPositiveButton("search", new DialogInterface.OnClickListener() {
            @Override

            public void onClick(DialogInterface dialogInterface, int i) {

                Intent intent= new Intent(context,SearchRoomActivity.class);
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
                SharedPreferences.Editor editor2 = prefs.edit();
                editor2.putString("COUNTER", Integer.toString(counter+counter2));
                editor2.commit();
                startActivity(intent);

            }

        });

        return builder.create();
    }
}
