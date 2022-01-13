package com.example.project2;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
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
    TextView roomcounter;
    Button roomplus;
    Button roomminus;
    int counterroom = 0;
    @Override

    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.activity_search_dialog,null);
        builder.setView(view);

        adultscounter =view.findViewById(R.id.adultscounter);
        adultsplus=view.findViewById(R.id.adultsplus);
        adultsminus=view.findViewById(R.id.adultsminus);


        childrencounter =view.findViewById(R.id.childrenscounter);
        childrenplus=view.findViewById(R.id.childrensplus);
        childrenminus=view.findViewById(R.id.childrensminus);

        roomcounter=view.findViewById(R.id.roomscounter);
        roomplus=view.findViewById(R.id.roomsplus);
        roomminus=view.findViewById(R.id.roomsminus);

        builder.setTitle("Search");
        adultsplus.setOnClickListener(v -> {
            counterads++;
            adultscounter.setText(Integer.toString(counterads));
        });
        adultsminus.setOnClickListener(v -> {
            counterads--;
            adultscounter.setText(Integer.toString(counterads));
            if(counterads<0){
                counterads=0;
                adultscounter.setText(Integer.toString(counterads));
            }
        });

        childrenplus.setOnClickListener(v -> {
            counterchildren++;
            childrencounter.setText(Integer.toString( counterchildren));
        });
        childrenminus.setOnClickListener(v -> {
            counterchildren--;
            childrencounter.setText(Integer.toString(counterchildren));
            if(counterchildren<0){
                counterchildren=0;
                childrencounter.setText(Integer.toString(counterchildren));
            }
        });

        roomplus.setOnClickListener(v -> {
           counterroom++;
            roomcounter.setText(Integer.toString( counterroom));
        });
        roomminus.setOnClickListener(v -> {
            counterroom--;
            roomcounter.setText(Integer.toString(counterroom));
            if(counterroom<0){
                counterroom=0;
                roomcounter.setText(Integer.toString(counterroom));
            }
        });

        builder.setPositiveButton("search", new DialogInterface.OnClickListener() {
            @Override

            public void onClick(DialogInterface dialogInterface, int i) {
            }

        });

        return builder.create();
    }
}
