package com.example.project2;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

public class searchDialog extends AppCompatDialogFragment  {
    TextView adultscounter;
    Button adultsplus;
    int counterads = 0;
    @Override

    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.activity_search_dialog,null);
        builder.setView(view);
        adultscounter =view.findViewById(R.id.adultscounter);
        adultsplus=view.findViewById(R.id.adultsplus);
        builder.setTitle("Search");
        builder.setPositiveButton("search", new DialogInterface.OnClickListener() {
            @Override
            //close the diologe
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        return builder.create();
    }
    public void  setAdultscounter (View view){
        Toast.makeText( null,"hiii", Toast.LENGTH_SHORT).show();
    }
}
